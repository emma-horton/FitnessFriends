import sqlite3

# Create (or connect to) a database file
conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()

# Create a sample table
# cursor.execute("""
# CREATE TABLE ActivityData (
#     dataId INT PRIMARY KEY,
#     userId INT,
#     activityDate VARCHAR(50),
#     activityType VARCHAR(50),
#     activityDuration FLOAT,
#     activityDistance FLOAT,
#     activityFrequency INT,
#     FOREIGN KEY (userId) REFERENCES Users(userId)
# );
# """)

# Insert a sample record
cursor.execute("""
INSERT INTO ActivityData (dataId, userId, activityDate, activityType, activityDuration, activityDistance) VALUES
(22, 2, '2025-03-19', 'SWIMMING', 60.0, 100.0);
""")

# Save and close
conn.commit()
conn.close()

print("Database and table created successfully!")