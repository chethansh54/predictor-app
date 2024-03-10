package project.predictor.app.service;

import project.predictor.app.model.JobConfig;

import java.util.List;

public interface JobConfigService {
    JobConfig saveJobConfig(JobConfig jobConfig);

    List<JobConfig> getAllJobConfigs();
}
