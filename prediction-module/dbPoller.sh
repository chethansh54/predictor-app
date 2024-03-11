predictionModulePath=$1

cd $predictionModulePath

path1=~/projects/predictor-app/predictor-web-app/assets/images/bs-readings-graph.png
path2=~/projects/predictor-app/predictor-web-app/assets/images/prediction-graph.png

source env/bin/activate

echo "activate environment"
echo "running data_injector...."
python3 db-poller.py data_injector "~/datainjector/datainjector-0.0.1-SNAPSHOT.jar 10" >> ~/datainjector/db-poller-$(date +%Y-%m-%d).log
echo "running data_injector : completed"

sleep 2

echo "running rmq_data_consumer...."
python3 db-poller.py rmq_data_consumer "~/rmqexecutor/rmq-jobexecutor.sh $predictionModulePath $path1 $path2" >> ~/rmqexecutor/db-poller-rmq-$(date +%Y-%m-%d).log
echo "running rmq_data_consumer : completed"
