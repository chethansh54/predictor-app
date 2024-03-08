package project.app.datainjector.service.impl;

import org.springframework.stereotype.Service;
import project.app.datainjector.model.SensorRawData;
import project.app.datainjector.repository.SensorRawDataRepository;
import project.app.datainjector.service.SensorRawDataService;

import java.util.List;

@Service
public class SensorRawDataServiceImpl implements SensorRawDataService {

    private SensorRawDataRepository sensorRawDataRepository;

    public SensorRawDataServiceImpl(SensorRawDataRepository sensorRawDataRepository) {
        super();
        this.sensorRawDataRepository = sensorRawDataRepository;
    }

    @Override
    public SensorRawData saveSensorRawData(SensorRawData sensorRawData) {
        return sensorRawDataRepository.save(sensorRawData);
    }

    @Override
    public List<SensorRawData> getAllSensorRawData() {
        return sensorRawDataRepository.findAll();
    }
}
