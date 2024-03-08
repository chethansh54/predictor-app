package project.app.datainjector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.app.datainjector.service.DataInjectorService;

import javax.xml.crypto.Data;
import java.util.logging.Logger;

@SpringBootApplication
public class DatainjectorApplication implements CommandLineRunner {

    @Autowired
    private DataInjectorService dataInjectorService;

    public static void main(String[] args) {
        SpringApplication.run(DatainjectorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {

            dataInjectorService.injectSensorData();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
