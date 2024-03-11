#! /bin/bash
predictorModulePath=$1
readingsChartPath=$2
predictionChartPath=$3

cd $predictorModulePath

source env/bin/activate

echo "Starting the listener..."

#python3 rabbitMQConsumer.py $readingsChartPath $predictionChartPath >> ~/rmqexecutor/rabbitMQConsumer-$(date +%Y-%m-%d).log

python3 rabbitMQConsumer.py $readingsChartPath $predictionChartPath

sleep 2

deactivate
echo "Completed"
