package project.predictor.app.service.impl;

import org.springframework.stereotype.Service;
import project.predictor.app.model.JobConfig;
import project.predictor.app.repository.JobConfigRepository;
import project.predictor.app.service.JobConfigService;

import java.util.List;

@Service
public class JobConfigServiceImpl implements JobConfigService {

    private JobConfigRepository jobConfigRepository;

    public JobConfigServiceImpl(JobConfigRepository jobConfigRepository) {
        super();
        this.jobConfigRepository = jobConfigRepository;
    }

    @Override
    public JobConfig saveJobConfig(JobConfig jobConfig) {
        return jobConfigRepository.save(jobConfig);
    }

    @Override
    public List<JobConfig> getAllJobConfigs() {
        return jobConfigRepository.findAll();
    }
}
