FROM python:3.8.12

USER root
RUN mkdir -p /var/predictorapp

COPY Matplotlib.py /var/predictorapp
COPY ml_engine.py /var/predictorapp
COPY MLPredictor.py /var/predictorapp
COPY rabbitMQConsumer.py /var/predictorapp
COPY requirements.txt /var/predictorapp

WORKDIR /var/predictorapp

RUN pip3 install pika
RUN pip3 install matplotlib
RUN pip3 install pandas
RUN pip3 install prophet

CMD python3 rabbitMQConsumer.py