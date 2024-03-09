package project.predictor.app.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "raw_data")
public class SensorRawData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int processing_id;
    private String sensor_name;
    private String sensor_type;
    private String manufacturer;
    private float mrp_price;
    private int delay_seconds;
    private int reading_mgdl;
    private long received_ts;


}
