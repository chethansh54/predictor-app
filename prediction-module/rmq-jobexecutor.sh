#! /bin/bash
predictorModulePath=$1

cd $predictorModulePath

source env/bin/activate
python3 rabbitMQConsumer.py

sleep 2

deactivate
echo "Completed"
