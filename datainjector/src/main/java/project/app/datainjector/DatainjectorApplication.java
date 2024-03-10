package project.app.datainjector;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import project.app.datainjector.service.DataInjectorService;
import project.app.datainjector.service.DataPreProcessorService;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DatainjectorApplication implements CommandLineRunner {

    @Autowired
    private DataInjectorService dataInjectorService;

    @Autowired
    private DataPreProcessorService dataPreProcessorService;

    private final RabbitTemplate rabbitTemplate;
    static final String topicExchangeName = "predictorapp";

    public DatainjectorApplication(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(DatainjectorApplication.class, args).close();
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer dataSetSize = Integer.parseInt(args[0]);

            // run data generator and process it
            List<Map<String, String>> mapList = dataInjectorService.injectSensorData(dataSetSize);
            String processedDataMessage = dataPreProcessorService.preProcessSensorData(mapList);

            System.out.println("Sending processed data ...........");
            rabbitTemplate.convertAndSend(topicExchangeName, "predictor", processedDataMessage);
            System.out.println("Processed Data Send : Complete !");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
