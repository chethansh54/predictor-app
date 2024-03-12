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

    public int getProcessing_id() {
        return processing_id;
    }

    public void setProcessing_id(int processing_id) {
        this.processing_id = processing_id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public int getReadingMgDl() {
        return readingMgDl;
    }

    public void setReadingMgDl(int readingMgDl) {
        this.readingMgDl = readingMgDl;
    }

    public long getReceivedTimestamp() {
        return receivedTimestamp;
    }

    public void setReceivedTimestamp(long receivedTimestamp) {
        this.receivedTimestamp = receivedTimestamp;
    }
}
