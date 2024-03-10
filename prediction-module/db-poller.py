import mysql.connector

mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="rootp@123"
)

print(mydb)
