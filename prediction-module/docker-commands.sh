docker run -d --name rmqtask1 -p 5672:5672 -p 15672:15672 rabbitmq:management
sleep 10
docker build -t ml-predictor .

docker run -d --name ml-predictor-app ml-predictor:latest