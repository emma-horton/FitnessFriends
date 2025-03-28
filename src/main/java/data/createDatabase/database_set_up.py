import sqlite3

# Create (or connect to) a database file
conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()

# Create a sample table
# cursor.execute("""
# CREATE TABLE HabitGoals (
#     goalId INT PRIMARY KEY,
#     userId INT,
#     goalType VARCHAR(50),
#     sport VARCHAR(50),
#     targetValue FLOAT,
#     FOREIGN KEY (userId) REFERENCES Users(userId)
# );
# """)

# Insert a sample record
# cursor.execute("""
# INSERT INTO ActivityData (dataId, userId, activityDate, activityType, activityDuration, activityDistance) VALUES
# (1, 2, '2025-03-27', 'CYCLING', 60.0, 100.0);
# """)
# cursor.execute("""
# INSERT INTO VirtualPets (petId, userId, petName, petType, healthStatus, lastUpdated) VALUES
# (1, 1, 'Fluffy', 'PARROT', 'HEALTHY', '2025-03-27 10:00:00'),
# (2, 2, 'Spot', 'TURTLE', 'SICK', '2025-03-27 11:00:00');
# """)

cursor.execute("""
INSERT INTO HabitGoals (goalId, userId, goalType, sport, targetValue) VALUES
(8, 1, 'Distance', 'CYCLING', 60);
""")

# Save and close
conn.commit()
conn.close()

print("Database and table created successfully!")