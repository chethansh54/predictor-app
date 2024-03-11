package project.predictor.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.predictor.app.model.JobConfig;
import project.predictor.app.service.JobConfigService;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/v1/jobconfig")
public class JobConfigController {
    private JobConfigService jobConfigService;

    public JobConfigController(JobConfigService jobConfigService) {
        super();
        this.jobConfigService = jobConfigService;
    }

    // store processed data
    @GetMapping("run/datainjector")
    public ResponseEntity<Map> runDataInjector() {
        JobConfig jobConfig = new JobConfig();

        jobConfig.setServicename("data_injector");
        jobConfig.setJobstatus("RUN");
        jobConfig.setLastupdatedts(System.currentTimeMillis() / 1000);

        JobConfig createdJobConfig = jobConfigService.saveJobConfig(jobConfig);
        Map<String, String> responseData = new HashMap<>();

        if (createdJobConfig != null) {
            long process_ts = System.currentTimeMillis();
            responseData.put("message", "success");
            responseData.put("processed_at", String.valueOf(process_ts));
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("run/rmqexecutor")
    public ResponseEntity<Map> runRabbitMQExecutor() {
        JobConfig jobConfig = new JobConfig();

        jobConfig.setServicename("rmq_data_consumer");
        jobConfig.setJobstatus("RUN");
        jobConfig.setLastupdatedts(System.currentTimeMillis() / 1000);

        JobConfig createdJobConfig = jobConfigService.saveJobConfig(jobConfig);
        Map<String, String> responseData = new HashMap<>();

        if (createdJobConfig != null) {
            long process_ts = System.currentTimeMillis();
            responseData.put("message", "success");
            responseData.put("processed_at", String.valueOf(process_ts));
            return new ResponseEntity<>(responseData, HttpStatus.CREATED);
        } else {
            responseData.put("message", "failed");
            return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
