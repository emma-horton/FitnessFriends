import sqlite3

conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()
# cursor.execute("""
#     SELECT * FROM activitydata WHERE activityDate BETWEEN DATE('now', '-14 days') AND DATE('now', '-7 days') AND  userId = 2 AND activityType = "SWIMMING";
#     """)
cursor.execute("""
    SELECT * FROM habitgoals WHERE userId = 1;
    """)
# cursor.execute("DROP TABLE habitgoals;")
print(cursor.fetchall())  

conn.close()