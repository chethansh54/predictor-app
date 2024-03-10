import json
import pika
import matplotlib.pyplot as plt
import pandas as pd
from ml_engine import MLPredictor

if __name__ == '__main__':
    rabbitmq_ip = "localhost"
    rabbitmq_port = 5672

    # Queue name
    rabbitmq_queque = "predictorappqueue"

    def callback(ch, method, properties, body):
        bodyStr = body.decode('utf-8').replace("'", '"')
        print(f"Got message from producer msg: {bodyStr}")

        data = json.loads(bodyStr)
        data_source = {
            'Timestamp': data.keys(),
            'Value': data.values()
        }

        data_df = pd.DataFrame(data_source)

        # Initialize a canvas
        plt.figure(figsize=(4, 4), dpi=100)

        # Plot data into canvas
        plt.plot(data_df["Timestamp"], data_df["Value"], color="#FF3B1D", marker='.', linestyle="-")
        plt.title("Prediction Data")
        plt.xlabel("DateTime")
        plt.xticks(rotation=90)
        plt.ylabel("Value")

        # Save as file
        plt.savefig("bs-readings-graph.png")

        # Directly display
        # plt.show()

        # Create ML engine predictor object
        predictor = MLPredictor(data_df)

        # Train ML model
        predictor.train()

        # Do prediction
        predictForecast = predictor.predict()

        # predict chart
        fig = predictor.plot_result(predictForecast)
        fig.savefig("prediction-graph.png")
        # fig.show()


    # Connect to RabbitMQ service with timeout 1min
    connection = pika.BlockingConnection(
        pika.ConnectionParameters(host=rabbitmq_ip, port=rabbitmq_port, socket_timeout=60))
    channel = connection.channel()
    # Declare a queue
    channel.queue_declare(queue=rabbitmq_queque)

    channel.basic_consume(queue=rabbitmq_queque,
                          auto_ack=True,
                          on_message_callback=callback)

    channel.start_consuming()
