import sqlite3
import requests
import webbrowser
import time
from datetime import datetime, timedelta
import json

# Your application credentials (replace with your actual values)
CLIENT_ID = "153559"
CLIENT_SECRET = "4af20622bdca9abe169f54226bdb9327c3a32a91"
REDIRECT_URI = "http://localhost:3000/"  # Or your actual callback URL
DB_PATH = "fitness-friends.db"  # Path to your SQLite database

# Strava API endpoints
AUTHORIZE_URL = "https://www.strava.com/oauth/authorize"
TOKEN_URL = "https://www.strava.com/oauth/token"
API_BASE_URL = "https://www.strava.com/api/v3/"

# Step 1: Redirect the user to the authorization URL
def authorize():
    params = {
        "client_id": CLIENT_ID,
        "redirect_uri": REDIRECT_URI,
        "response_type": "code",
        "scope": "read_all,activity:read_all,profile:read_all"  # Request the necessary permissions
    }
    auth_url = f"{AUTHORIZE_URL}?{'&'.join([f'{k}={v}' for k, v in params.items()])}"
    print(f"Open this URL in your browser to authorize: {auth_url}")
    webbrowser.open(auth_url)

# Step 2: Handle the callback and exchange the authorization code for tokens
def get_access_token(authorization_code):
    payload = {
        "client_id": CLIENT_ID,
        "client_secret": CLIENT_SECRET,
        "code": authorization_code,
        "grant_type": "authorization_code",
        "redirect_uri": REDIRECT_URI
    }
    response = requests.post(TOKEN_URL, data=payload)
    response.raise_for_status()  # Raise an exception for bad status codes
    tokens = response.json()
    return tokens['access_token'], tokens['refresh_token'], tokens['expires_at']

# Step 3: Use the access token to make API requests
def get_athlete_activities(access_token, after=None, before=None):
    headers = {"Authorization": f"Bearer {access_token}"}
    params = {}
    if after:
        params['after'] = after
    if before:
        params['before'] = before
    response = requests.get(f"{API_BASE_URL}athlete/activities", headers=headers, params=params)
    response.raise_for_status()
    return response.json()

# Step 4: Insert activities into the ActivityData table
def insert_activities_into_db(activities):
    """
    Insert Strava activities into the ActivityData table in the SQLite database.
    """
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()

    for activity in activities:
        try:
            # Extract relevant fields from the Strava activity
            data_id = activity["id"]  # Use Strava's activity ID as the unique identifier
            user_id = 1  # Replace with the actual user ID (e.g., from your Users table)
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

if __name__ == "__main__":
    authorize()
    authorization_code = input("Enter the authorization code from the URL: ")

    if authorization_code:
        access_token, refresh_token, expires_at = get_access_token(authorization_code)
        print(f"Access Token: {access_token}")
        print(f"Refresh Token: {refresh_token}")
        print(f"Expires At: {expires_at}")

        try:
            # Calculate the timestamp for two weeks ago
            two_weeks_ago = datetime.now() - timedelta(weeks=2)
            after_timestamp = int(two_weeks_ago.timestamp())

            # Get activities from the past two weeks
            recent_activities = get_athlete_activities(access_token, after=after_timestamp)
            print("\nActivities from the past two weeks:")
            print(json.dumps(recent_activities, indent=4))

            # Insert activities into the database
            if recent_activities:
                insert_activities_into_db(recent_activities)
            else:
                print("No activities found for the past two weeks.")

        except requests.exceptions.RequestException as e:
            print(f"Error fetching data: {e}")