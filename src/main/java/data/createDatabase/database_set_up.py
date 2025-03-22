import sqlite3

# Create (or connect to) a database file
conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()

# Create a sample table
#cursor.execute("""
# CREATE TABLE VirtualPets (
#     petId INT PRIMARY KEY,
#     userId INT,
#     petName VARCHAR(255),
#     petType VARCHAR(50),
#     healthStatus VARCHAR(50),
#     lastUpdated DATETIME,
#     FOREIGN KEY (userId) REFERENCES Users(userId)
# );
# """)

# Insert a sample record
cursor.execute("""
INSERT INTO ActivityData (dataId, userId, activityDate, activityType, activityDuration, activityDistance) VALUES
(9, 1, '2025-03-14', 'Run', 30.0, 5.0),
(10, 2, '2025-03-16', 'Cycle', 60.0, 20.0),
(11, 3, '2025-03-14', 'Swim', 45.0, 1.5),
(12, 1, '2025-03-16', 'Run', 40.0, 6.0),
(13, 4, '2025-03-14', 'Cycle', 70.0, 22.0),
(14,1, '2025-03-16', 'Run', 50.0, 8.0),
(15,2, '2025-03-14', 'Cycle', 80.0, 25.0),
(16,1, '2025-03-16', 'Swim', 30.0, 1.0);
""")

# Save and close
conn.commit()
conn.close()

print("Database and table created successfully!")