import sqlite3

conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()
# cursor.execute("""
#     SELECT * FROM activitydata WHERE activityDate BETWEEN DATE('now', '-14 days') AND DATE('now', '-7 days') AND  userId = 2 AND activityType = "SWIMMING";
#     """)
cursor.execute("""
    SELECT * FROM activityData WHERE activityDate >= DATE('now', '-7 days') AND userId = 1 AND activityType = "RIDE";
    """)
# cursor.execute("DROP TABLE HabitGoals;")
print(cursor.fetchall())  

conn.close()