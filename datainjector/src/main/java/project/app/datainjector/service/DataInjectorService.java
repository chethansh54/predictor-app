package project.app.datainjector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.app.datainjector.model.SensorData;
import project.app.datainjector.model.SensorRawData;

import java.util.Random;

@Component
public class DataInjectorService {

    @Autowired
    private SensorDataService sensorDataService;
    @Autowired
    private SensorRawDataService sensorRawDataService;

    public void prepareAndSendSensorData(int dataSetSize) {
        String sensorType = "GlucoMeter";
        String sensorName = "AccuCheck-Active";
        String manufacturer = "AccuCheck";

        SensorData sensorData = new SensorData();
        SensorRawData sensorRawData = new SensorRawData();
        SensorData createdSensorData = null;
        SensorRawData createdSensorRawData = null;
        Random random = new Random();

        for (int i = 0; i < dataSetSize; i++) {

            sensorRawData.setSensor_name(sensorName);
            sensorRawData.setSensor_type(sensorType);
            sensorRawData.setManufacturer(manufacturer);
            sensorRawData.setDelay_seconds(random.nextInt(60));
            sensorRawData.setMrp_price(random.nextFloat(3000.0f));
            sensorRawData.setReading_mgdl(random.nextInt(600));
            sensorRawData.setReceived_ts(System.currentTimeMillis());

            sensorRawDataService.saveSensorRawData(sensorRawData);

        }
    }

    public void injectSensorData() {

        prepareAndSendSensorData(10);

    }

}
