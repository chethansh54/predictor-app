package project.app.datainjector.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.app.datainjector.model.SensorData;
import project.app.datainjector.model.SensorRawData;

import java.util.Random;

@Component
public class DataInjectorService {
    @Autowired
    private SensorRawDataService sensorRawDataService;
    @Autowired
    private SensorDataService sensorDataService;

    public void prepareAndSendSensorData(int dataSetSize) {
        String sensorType = "GlucoMeter";
        String sensorName = "AccuCheck-Active";
        String manufacturer = "AccuCheck";

        Random random = new Random();

        for (int i = 0; i < dataSetSize; i++) {
            SensorData sensorData = new SensorData();
            SensorRawData sensorRawData = new SensorRawData();

            int sensorReading = random.nextInt(600);
            long sensorRcvTs = System.currentTimeMillis();

            // get sensor raw data
            sensorRawData.setSensor_name(sensorName);
            sensorRawData.setSensor_type(sensorType);
            sensorRawData.setManufacturer(manufacturer);
            sensorRawData.setDelay_seconds(random.nextInt(60));
            sensorRawData.setMrp_price(random.nextFloat(3000.0f));
            sensorRawData.setReading_mgdl(sensorReading);
            sensorRawData.setReceived_ts(sensorRcvTs);

            // set sensor processed data
            sensorData.setSensorName(sensorName);
            sensorData.setReadingMgDl(sensorReading);
            sensorData.setReceivedTimestamp(sensorRcvTs);

            // store data
            sensorDataService.saveSensorData(sensorData);
            sensorRawDataService.saveSensorRawData(sensorRawData);

            // send MQTT to Subscriber

        }
    }

    public void injectSensorData(int dataSetSize) {

        prepareAndSendSensorData(dataSetSize);

    }

}
