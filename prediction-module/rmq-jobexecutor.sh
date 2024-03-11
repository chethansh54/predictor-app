#! /bin/bash
predictorModulePath=$1
readingsChartPath=$2
predictionChartPath=$3

cd $predictorModulePath

source env/bin/activate
python3 rabbitMQConsumer.py $readingsChartPath $predictionChartPath >> ~/rmqexecutor/rabbitMQConsumer-$(date +%Y-%m-%d).log

sleep 2

deactivate
echo "Completed"
