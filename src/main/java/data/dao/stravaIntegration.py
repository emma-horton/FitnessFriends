import sqlite3
import time
import requests
from datetime import datetime, timedelta
import sys
import json
import os

DB_PATH = "fitness-friends.db"
CLIENT_ID = "153559"
CLIENT_SECRET = "4af20622bdca9abe169f54226bdb9327c3a32a91"
REDIRECT_URI = "http://localhost:3000/"

# Strava API endpoints
AUTHORIZE_URL = "https://www.strava.com/oauth/authorize"
TOKEN_URL = "https://www.strava.com/oauth/token"
API_BASE_URL = "https://www.strava.com/api/v3/"

def authorize_strava_account(user_id, authorization_code=None):
    """
    Handles the Strava authorization process for a user.
    """
    print(f"Authorizing Strava account for user_id: {user_id}")
    if not authorization_code:
        print("No authorization code provided. Please provide it as a command-line argument.")
        return

    try:
        # Exchange the authorization code for tokens
        access_token, refresh_token, expires_at, strava_user_id = get_initial_tokens(authorization_code)
        print(f"Tokens received: Access Token: {access_token}, Refresh Token: {refresh_token}, Expires At: {expires_at}")
        store_tokens(access_token, refresh_token, expires_at, user_id, strava_user_id)
        print("Strava account authorized and tokens stored successfully!")
    except Exception as e:
        print(f"Error during Strava authorization: {e}")
def authorize():
    """
    Redirect the user to the Strava authorization page.
    """
    params = {
        "client_id": CLIENT_ID,
        "redirect_uri": REDIRECT_URI,
        "response_type": "code",
        "scope": "read_all,activity:read_all"
    }
    auth_url = f"{AUTHORIZE_URL}?{'&'.join([f'{k}={v}' for k, v in params.items()])}"
    print(f"Open this URL in your browser to authorize: {auth_url}")

def get_initial_tokens(authorization_code):
    """
    Exchange the authorization code for access and refresh tokens.
    """
    payload = {
        "client_id": CLIENT_ID,
        "client_secret": CLIENT_SECRET,
        "code": authorization_code,
        "grant_type": "authorization_code"
    }
    response = requests.post(TOKEN_URL, data=payload)
    response.raise_for_status()  # Raise an exception for HTTP errors
    tokens = response.json()

    # Extract the Strava user ID from the response
    strava_user_id = tokens.get("athlete", {}).get("id")  # Ensure the "athlete" object exists
    if not strava_user_id:
        raise ValueError("Failed to retrieve Strava user ID from the API response.")

    return tokens["access_token"], tokens["refresh_token"], tokens["expires_at"], strava_user_id

def refresh_access_token(refresh_token):
    """
    Refresh the access token using the refresh token.
    """
    payload = {
        "client_id": CLIENT_ID,
        "client_secret": CLIENT_SECRET,
        "refresh_token": refresh_token,
        "grant_type": "refresh_token"
    }
    response = requests.post(TOKEN_URL, data=payload)
    response.raise_for_status()
    tokens = response.json()
    return tokens["access_token"], tokens["refresh_token"], tokens["expires_at"]

def get_stored_tokens(user_id):
    """
    Retrieve stored tokens for a user from the database.
    """
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()
    cursor.execute("""
        SELECT access_token, refresh_token, expires_at
        FROM stravaAccounts
        WHERE userId = ?
    """, (user_id,))
    row = cursor.fetchone()
    conn.close()
    if row:
        return row[0], row[1], row[2]
    return None, None, None

def store_tokens(access_token, refresh_token, expires_at, user_id, strava_user_id):
    """
    Store or update tokens for a user in the database.
    """
    print(f"Storing tokens for user_id: {user_id}, strava_user_id: {strava_user_id}")
    print(f"Access Token: {access_token}, Refresh Token: {refresh_token}, Expires At: {expires_at}")
    try:
        conn = sqlite3.connect(DB_PATH)
        cursor = conn.cursor()
        cursor.execute("""
            INSERT OR REPLACE INTO StravaAccounts (userId, stravaUserId, access_token, refresh_token, expires_at)
            VALUES (?, ?, ?, ?, ?)
        """, (user_id, strava_user_id, access_token, refresh_token, expires_at))
        conn.commit()
        print("Tokens stored successfully!")
    except sqlite3.Error as e:
        print(f"Database error while storing tokens: {e}")
    finally:
        conn.close()

def get_athlete_activities(access_token, after=None):
    """
    Fetch activities for the authenticated user from the Strava API.
    """
    headers = {"Authorization": f"Bearer {access_token}"}
    params = {"per_page": 50}
    if after:
        params["after"] = after
    response = requests.get(f"{API_BASE_URL}athlete/activities", headers=headers, params=params)
    response.raise_for_status()
    return response.json()

def insert_activities_into_db(activities, user_id):
    """
    Insert Strava activities into the ActivityData table in the database.
    """
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()

    for activity in activities:
        try:
            # Extract relevant fields from the Strava activity
            data_id = activity["id"]  # Use Strava's activity ID as the unique identifier
            activity_date = datetime.strptime(activity["start_date"], "%Y-%m-%dT%H:%M:%SZ").strftime("%Y-%m-%d")
            activity_type = activity["type"].upper()  # Convert to uppercase to match your SportType enum
            activity_duration = activity["elapsed_time"] / 60.0  # Convert seconds to minutes
            activity_distance = activity["distance"] / 1000.0    # Convert meters to kilometers

            # Insert the activity into the ActivityData table
            cursor.execute("""
                INSERT OR IGNORE INTO ActivityData (dataId, userId, activityDate, activityType, activityDuration, activityDistance)
                VALUES (?, ?, ?, ?, ?, ?)
            """, (data_id, user_id, activity_date, activity_type, activity_duration, activity_distance))
        except KeyError as e:
            print(f"Missing key in activity data: {e}")
        except sqlite3.Error as e:
            print(f"Database error: {e}")
    conn.commit()
    conn.close()
    print("Activities inserted into the database successfully!")

def pull_activity_data(user_id):
    print(f"Pulling activity data for user_id: {user_id}")
    access_token, refresh_token, expires_at = get_stored_tokens(user_id)

    if not access_token or not refresh_token or not expires_at or int(expires_at) < time.time():
        print("Access token expired or not found. Refreshing...")
        access_token, refresh_token, expires_at = refresh_access_token(refresh_token)
        store_tokens(access_token, refresh_token, expires_at, user_id)
        print("Access token refreshed.")

    if access_token:
        two_weeks_ago = datetime.now() - timedelta(weeks=2)
        after_timestamp = int(two_weeks_ago.timestamp())
        print(f"Fetching activities after timestamp: {after_timestamp}")

        recent_activities = get_athlete_activities(access_token, after=after_timestamp)

        if recent_activities:
            insert_activities_into_db(recent_activities, user_id)
        else:
            print("No activities found for the past two weeks.")
         
def main():
    """
    Main function to handle command-line arguments and execute the appropriate functionality.
    """
    if len(sys.argv) < 2:
        print("Usage: python3 stravaIntegration.py <user_id> [action] [authorization_code]")
        print("Actions:")
        print("  authorize          - Authorize the user's Strava account")
        print("  pull_activity_data - Pull activity data for the user")
        return

    user_id = int(sys.argv[1])  
    action = sys.argv[2] if len(sys.argv) > 2 else "authorize"  
    authorization_code = sys.argv[3] if len(sys.argv) > 3 else None  

    if action == "authorize":
        authorize_strava_account(user_id, authorization_code)
    elif action == "pull_activity_data":
        pull_activity_data(user_id)
    else:
        print(f"Unknown action: {action}")
        print("Valid actions are: authorize, pull_activity_data")

if __name__ == "__main__":
    main()