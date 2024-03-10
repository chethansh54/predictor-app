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
DATA_INJECTOR_JAR_PATH = sys.argv[2]
JAR_LOG_FILE_PATH = "~/logs/predictor-app"

# make cursor to db with connection
db_cursor = predictordb.cursor()
db_cursor.execute(f"SELECT jobstatus FROM {MYSQL_TABLE_NAME} where servicename='{JOB_NAME}'")
job_data_result_set = db_cursor.fetchall()

for result_value in job_data_result_set:
    try:
        job_status = result_value[0]

        if job_status.upper() == "IDLE":
            # set jobstate to RUNNING
            sql = f"UPDATE jobconfigmanager SET jobstatus ='RUNNING', lastupdatedts={int(time.time())} WHERE servicename='{JOB_NAME}'"
            db_cursor.execute(sql)
            predictordb.commit()

            call(f"java -Xmx1024m -jar {DATA_INJECTOR_JAR_PATH} >> {JAR_LOG_FILE_PATH}/log_$(date +%Y_%m_%d_%H_%M).log",
                 shell=True)
            resp = call("echo $?", shell=True)
            if resp == 0:
                # set jobstate to RUNNING
                sql = f"UPDATE jobconfigmanager SET jobstatus ='IDLE', lastupdatedts={int(time.time())} WHERE servicename='{JOB_NAME}'"
                db_cursor.execute(sql)
                predictordb.commit()

    except Exception as e:
        print(e.__str__())
