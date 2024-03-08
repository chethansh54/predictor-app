package project.app.datainjector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.app.datainjector.service.DataInjectorService;
import project.app.datainjector.service.DataPreProcessorService;

import javax.xml.crypto.Data;
import java.util.logging.Logger;

@SpringBootApplication
public class DatainjectorApplication implements CommandLineRunner {

    @Autowired
    private DataInjectorService dataInjectorService;
    private DataPreProcessorService dataPreProcessorService;

    public static void main(String[] args) {
        SpringApplication.run(DatainjectorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String runMode = args[1];
            Integer dataSetSize = Integer.parseInt(args[2]);

            if (runMode.equalsIgnoreCase("INJECTOR")) {
                dataInjectorService.injectSensorData(dataSetSize);
            } else {
                dataPreProcessorService.preProcessSensorData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
