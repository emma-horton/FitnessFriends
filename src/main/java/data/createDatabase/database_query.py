import sqlite3

conn = sqlite3.connect("fitness-friends.db")  
cursor = conn.cursor()

cursor.execute("SELECT * FROM activitydata;")
#cursor.execute("DROP TABLE Users;")
print(cursor.fetchall())  

conn.close()