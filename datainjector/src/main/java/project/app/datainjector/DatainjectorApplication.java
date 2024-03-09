package project.app.datainjector;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import project.app.datainjector.service.DataInjectorService;
import project.app.datainjector.service.DataPreProcessorService;
import project.app.datainjector.utils.RabbitMQMessageSubscriber;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class DatainjectorApplication implements CommandLineRunner {

    @Autowired
    private DataInjectorService dataInjectorService;

    @Autowired
    private DataPreProcessorService dataPreProcessorService;

    private final RabbitTemplate rabbitTemplate;
    private final RabbitMQMessageSubscriber receiver;

    public DatainjectorApplication(RabbitMQMessageSubscriber receiver, RabbitTemplate rabbitTemplate) {
        this.receiver = receiver;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer dataSetSize = Integer.parseInt(args[0]);

            // run data generator and process it
            List<Map<String, String>> mapList = dataInjectorService.injectSensorData(dataSetSize);
            dataPreProcessorService.preProcessSensorData(mapList);

            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(RabbitMQMessageApplication.topicExchangeName, "predictor.app", "Hello from RabbitMQ!");
            receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
