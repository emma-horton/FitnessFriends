import sqlite3

conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()
cursor.execute("""
    SELECT * FROM activitydata WHERE activityDate >= DATE('now', '-7 days') AND userId = 2 AND activityType = "Running";
    """)
# cursor.execute("DROP TABLE ActivityData;")
print(cursor.fetchall())  

conn.close()