import sqlite3
import requests
from datetime import datetime

# Strava API credentials
ACCESS_TOKEN = ""  # Replace with the user's Strava access token

# SQLite database connection
DB_PATH = "fitness-friends.db"

def fetch_strava_activities():
    """
    Fetch activities from the Strava API.
    """
    url = "https://www.strava.com/api/v3/athlete/activities"
    headers = {
        "Authorization": f"Bearer {ACCESS_TOKEN}"
    }
    params = {
        "per_page": 50,  # Number of activities to fetch per page
        "page": 1        # Start with the first page
    }

    activities = []
    while True:
        response = requests.get(url, headers=headers, params=params)
        if response.status_code != 200:
            print(f"Error fetching activities: {response.status_code} - {response.text}")
            break

        data = response.json()
        if not data:
            break  # No more activities to fetch

        activities.extend(data)
        params["page"] += 1  # Fetch the next page

    return activities

def insert_activities_into_db(activities):
    """
    Insert Strava activities into the ActivityData table in the SQLite database.
    """
    conn = sqlite3.connect(DB_PATH)
    cursor = conn.cursor()

    for activity in activities:
        try:
            # Extract relevant fields from the Strava activity
            user_id = 1  # Replace with the actual user ID (e.g., from your Users table)
            activity_date = datetime.strptime(activity["start_date"], "%Y-%m-%dT%H:%M:%SZ").strftime("%Y-%m-%d")
            activity_type = activity["type"].upper()  # Convert to uppercase to match your SportType enum
            activity_duration = activity["elapsed_time"] / 60.0  # Convert seconds to minutes
            activity_distance = activity["distance"] / 1000.0    # Convert meters to kilometers

            # Insert the activity into the ActivityData table
            cursor.execute("""
                INSERT INTO ActivityData (userId, activityDate, activityType, activityDuration, activityDistance)
                VALUES (?, ?, ?, ?, ?)
            """, (user_id, activity_date, activity_type, activity_duration, activity_distance))

        except KeyError as e:
            print(f"Missing key in activity data: {e}")
        except sqlite3.Error as e:
            print(f"Database error: {e}")

    conn.commit()
    conn.close()
    print("Activities inserted into the database successfully!")

if __name__ == "__main__":
    # Fetch activities from Strava API
    activities = fetch_strava_activities()

    # Insert activities into the database
    if activities:
        insert_activities_into_db(activities)
    else:
        print("No activities fetched from Strava.")