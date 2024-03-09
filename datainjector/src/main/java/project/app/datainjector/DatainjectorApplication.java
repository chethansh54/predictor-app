package project.app.datainjector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.app.datainjector.service.DataInjectorService;
import project.app.datainjector.service.DataPreProcessorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DatainjectorApplication implements CommandLineRunner {

    @Autowired
    private DataInjectorService dataInjectorService;

    @Autowired
    private DataPreProcessorService dataPreProcessorService;

    public static void main(String[] args) {
        SpringApplication.run(DatainjectorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String runMode = args[0];
            Integer dataSetSize = Integer.parseInt(args[1]);

            if (runMode.equalsIgnoreCase("INJECTOR")) {
                dataInjectorService.injectSensorData(dataSetSize);
            } else {
                List<Map<String, Integer>> mapList = new ArrayList<>();
                dataPreProcessorService.preProcessSensorData(mapList);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
