package project.predictor.app.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.predictor.app.model.SensorData;
import project.predictor.app.model.SensorRawData;
import project.predictor.app.service.DataInjectorService;
import project.predictor.app.service.DataPreProcessorService;
import project.predictor.app.service.SensorDataService;
import project.predictor.app.service.SensorRawDataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/sensordata")
public class SensorDataController {
    private SensorDataService sensorDataService;
    private SensorRawDataService sensorRawDataService;

    @Autowired
    private DataInjectorService dataInjectorService;

    @Autowired
    private DataPreProcessorService dataPreProcessorService;

    private final RabbitTemplate rabbitTemplate;
    static final String topicExchangeName = "predictorapp";


    public SensorDataController(SensorDataService sensorDataService, SensorRawDataService sensorRawDataService, RabbitTemplate rabbitTemplate) {
        super();
        this.sensorDataService = sensorDataService;
        this.sensorRawDataService = sensorRawDataService;
        this.rabbitTemplate = rabbitTemplate;
    }

    // store processed data
    @PostMapping("processed/datastore")
    public ResponseEntity<Map> putSensorData(@RequestBody SensorData sensorData) {
        SensorData createdSensorData = sensorDataService.saveSensorData(sensorData);
        Map<String, String> responseData = new HashMap<>();

        if (createdSensorData != null) {
            long process_ts = System.currentTimeMillis();
            responseData.put("message", "success");
            responseData.put("processed_at", String.valueOf(process_ts));
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("raw/datastore")
    public ResponseEntity<Map> putSensorRawData(@RequestBody SensorRawData sensorRawData) {
        SensorRawData createdSensorRawData = sensorRawDataService.saveSensorRawData(sensorRawData);
        Map<String, String> responseData = new HashMap<>();

        if (createdSensorRawData != null) {
            responseData.put("message", "success");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("processed/dataview")
    public ResponseEntity<List<SensorData>> viewSensorData() {
        List<SensorData> sensorDataList = sensorDataService.getAllSensorData();
        return new ResponseEntity<>(sensorDataList, HttpStatus.OK);
    }

    @GetMapping("raw/dataview")
    public ResponseEntity<List<SensorRawData>> viewSensorRawData() {
        List<SensorRawData> sensorRawDataList = sensorRawDataService.getAllSensorRawData();
        return new ResponseEntity<>(sensorRawDataList, HttpStatus.OK);
    }

    @GetMapping("datainjector/datastore")
    public ResponseEntity<Map> runDataInjector(@RequestParam(name = "dataSetSize") String dataSetSize) {
        boolean isDataGenerated = false;

        try {
            // run data generator and process it
            List<Map<String, String>> mapList = dataInjectorService.injectSensorData(Integer.parseInt(dataSetSize));
            String processedDataMessage = dataPreProcessorService.preProcessSensorData(mapList);

            if (processedDataMessage.length() > 2) {
                isDataGenerated = true;
            }
            System.out.println("Sending processed data ...........");
            rabbitTemplate.convertAndSend(topicExchangeName, "predictor", processedDataMessage);
            System.out.println("Processed Data Send : Complete !");

        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, String> responseData = new HashMap<>();

        if (isDataGenerated) {
            responseData.put("message", "success");
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
