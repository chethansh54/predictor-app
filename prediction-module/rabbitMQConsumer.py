import json
import os
import sys

import matplotlib.pyplot as plt
import pandas as pd
import pika

from ml_engine import MLPredictor

if __name__ == '__main__':
    rabbitmq_ip = "localhost"
    rabbitmq_port = 5672
    readings_chart_file = sys.argv[1]
    prediction_chart_file = sys.argv[2]
    # Queue name
    rabbitmq_queque = "predictorappqueue"

    print(f"Loaded RabbitMQ Configs..")
    if os.path.exists(readings_chart_file):
        os.remove(readings_chart_file)
        print(f"cleared old output files : {readings_chart_file}")

    if os.path.exists(prediction_chart_file):
        os.remove(prediction_chart_file)
        print(f"cleared old output files : {prediction_chart_file}")


    def callback(ch, method, properties, body):
        bodyStr = body.decode('utf-8').replace("'", '"')
        print(f"Got message from producer msg: {bodyStr}")

        data = json.loads(bodyStr)
        data_source = {
            'Timestamp': data.keys(),
            'Value': data.values()
        }

        data_df = pd.DataFrame(data_source)
        data_df.sort_values(by=['Timestamp'], inplace=True)

        # Initialize a canvas
        plt.figure(figsize=(4, 4), dpi=100)

        # Plot data into canvas
        plt.plot(data_df["Timestamp"], data_df["Value"], color="#FF3B1D", marker='.', linestyle="-")
        plt.title("Chart 1 : Blood Sugar Levels")
        plt.xlabel("DateTime")
        plt.xticks(rotation=90)
        plt.ylabel("Value")

        # Save as file
        plt.savefig(readings_chart_file)
        print(f"Saved Chart1 at : {readings_chart_file}")
        # Directly display
        plt.show()

        # Create ML engine predictor object
        predictor = MLPredictor(data_df)

        # Train ML model
        predictor.train()

        # Do prediction
        predictForecast = predictor.predict()

        # predict chart
        fig = predictor.plot_result(predictForecast)
        fig.savefig(prediction_chart_file)
        print(f"Saved Chart2 at : {prediction_chart_file}")
        fig.show()


    try:
        # Connect to RabbitMQ service with timeout 1min
        print(f"Listening to RabbitMQ message queue....")

        connection = pika.BlockingConnection(
            pika.ConnectionParameters(host=rabbitmq_ip, port=rabbitmq_port, socket_timeout=60))
        channel = connection.channel()
        # Declare a queue
        channel.queue_declare(queue=rabbitmq_queque)

        channel.basic_consume(queue=rabbitmq_queque,
                              auto_ack=True,
                              on_message_callback=callback)

        channel.start_consuming()

    except Exception as e:
        print(e.__str__())
