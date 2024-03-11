import sys
import time

import mysql.connector
from subprocess import call

predictordb = mysql.connector.connect(
    host="localhost",
    user="root",
    password="rootp@123",
    database="predictordb"
)

MYSQL_TABLE_NAME = "jobconfigmanager"
JOB_NAME = sys.argv[1]
APP_EXECUTABLE_FILE_PATH = sys.argv[2]
LOG_FILE_PATH = "~/logs/predictor-app"

# make cursor to db with connection
db_cursor = predictordb.cursor()
db_cursor.execute(f"SELECT jobstatus FROM {MYSQL_TABLE_NAME} where servicename='{JOB_NAME}'")
job_data_result_set = db_cursor.fetchall()

print(f"JOB_NAME={JOB_NAME}")
print(f"APP_EXECUTABLE_FILE_PATH={APP_EXECUTABLE_FILE_PATH}")
print(f"LOG_FILE_PATH={LOG_FILE_PATH}")

for result_value in job_data_result_set:
    try:
        job_status = result_value[0]

        if job_status.upper() == "RUN":
            # set jobstate to RUNNING
            sql = f"UPDATE jobconfigmanager SET jobstatus ='RUNNING', lastupdatedts={int(time.time())} WHERE servicename='{JOB_NAME}'"
            db_cursor.execute(sql)
            predictordb.commit()
            resp = 127

            if JOB_NAME.lower() == "data_injector":
                call(
                    f"java -Xmx1024m -jar {APP_EXECUTABLE_FILE_PATH} >> {LOG_FILE_PATH}/{JOB_NAME}_$(date +%Y_%m_%d_%H_%M).log",
                    shell=True)
                resp = call("echo $?", shell=True)

            if JOB_NAME.lower() == "rmq_data_consumer":
                call(
                    f"{APP_EXECUTABLE_FILE_PATH} >> {LOG_FILE_PATH}/{JOB_NAME}_$(date +%Y_%m_%d_%H_%M).log",
                    shell=True)
                resp = call("echo $?", shell=True)

            time.sleep(5)

            if resp == 0:
                # set jobstate to IDLE
                sql = f"UPDATE jobconfigmanager SET jobstatus ='IDLE', lastupdatedts={int(time.time())} WHERE servicename='{JOB_NAME}'"
                db_cursor.execute(sql)
                predictordb.commit()

                print("JobStatus RESET to IDLE")
        else:
            print("Nothing to RUN")
    except Exception as e:
        print(e.__str__())
