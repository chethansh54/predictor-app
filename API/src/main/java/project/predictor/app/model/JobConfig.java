package project.predictor.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jobconfigmanager")
public class JobConfig {
    @Id
    private String servicename;
    private String jobstatus;
    private long lastupdatedts;

    public String getServicename() {
        return servicename;
    }

    public void setServicename(String servicename) {
        this.servicename = servicename;
    }

    public String getJobstatus() {
        return jobstatus;
    }

    public void setJobstatus(String jobstatus) {
        this.jobstatus = jobstatus;
    }

    public long getLastupdatedts() {
        return lastupdatedts;
    }

    public void setLastupdatedts(long lastupdatedts) {
        this.lastupdatedts = lastupdatedts;
    }
}
