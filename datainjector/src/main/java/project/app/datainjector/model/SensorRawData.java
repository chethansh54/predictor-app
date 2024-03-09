package project.app.datainjector.model;

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

    public int getProcessing_id() {
        return processing_id;
    }

    public void setProcessing_id(int processing_id) {
        this.processing_id = processing_id;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public String getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public float getMrp_price() {
        return mrp_price;
    }

    public void setMrp_price(float mrp_price) {
        this.mrp_price = mrp_price;
    }

    public int getDelay_seconds() {
        return delay_seconds;
    }

    public void setDelay_seconds(int delay_seconds) {
        this.delay_seconds = delay_seconds;
    }

    public int getReading_mgdl() {
        return reading_mgdl;
    }

    public void setReading_mgdl(int reading_mgdl) {
        this.reading_mgdl = reading_mgdl;
    }

    public long getReceived_ts() {
        return received_ts;
    }

    public void setReceived_ts(long received_ts) {
        this.received_ts = received_ts;
    }
}
