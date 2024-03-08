package project.predictor.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "processed_data")
public class SensorData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int processing_id;
    @Column(name = "sensor_name")
    private String sensorName;
    @Column(name = "reading_mgdl")
    private int readingMgDl;
    @Column(name = "received_ts")
    private long receivedTimestamp;

}
