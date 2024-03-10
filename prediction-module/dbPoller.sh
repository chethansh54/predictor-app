predictionModulePath=$1

cd $predictionModulePath
source env/bin/activate
python3 db-poller.py data_injector "~/datainjector/datainjector-0.0.1-SNAPSHOT.jar" >> ~/datainjector/db-poller-$(date +%Y-%m-%d-%H-%M).log
sleep 2
python3 db-poller.py rmq_data_consumer "~/rmqexecutor/rmq-jobexecutor.sh ${predictionModulePath}" >> ~/rmqexecutor/db-poller-rmq-$(date +%Y-%m-%d-%H-%M).log