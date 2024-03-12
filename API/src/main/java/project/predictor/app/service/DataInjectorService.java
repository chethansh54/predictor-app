package project.predictor.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.predictor.app.model.SensorData;
import project.predictor.app.model.SensorRawData;

import java.util.*;

@Component
public class DataInjectorService {
    @Autowired
    private SensorRawDataService sensorRawDataService;
    @Autowired
    private SensorDataService sensorDataService;

    public List<Map<String, String>> prepareAndSendSensorData(int dataSetSize) {
        String sensorType = "GlucoMeter";
        String sensorName = "AccuCheck-Active";
        String manufacturer = "AccuCheck";

        Random random = new Random();
        List<Map<String, String>> sensorDataList = new ArrayList<>();

        long startEpoch = 1704072600L;

        for (int i = 0; i < dataSetSize; i++) {
            SensorData sensorData = new SensorData();
            SensorRawData sensorRawData = new SensorRawData();

            int sensorReading = random.nextInt(600 - 70) + 70;
            long sensorRcvTs = startEpoch;

            startEpoch += 86400;// increment to move for next day

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

            // prepare list for processing
            Map<String, String> sensorDataMap = new HashMap<>();
            sensorDataMap.put("received_ts", String.valueOf(sensorData.getReceivedTimestamp()));
            sensorDataMap.put("reading_mgdl", String.valueOf(sensorData.getReadingMgDl()));
            sensorDataList.add(sensorDataMap);
        }

        return sensorDataList;
    }

    public List<Map<String, String>> injectSensorData(int dataSetSize) {

        return prepareAndSendSensorData(dataSetSize);

    }

}
