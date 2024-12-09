package com.foo.bar.batch.data_batch_processor.infrastructure;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJobRunner {

    private final JobLauncher jobLauncher;
    private final Job transformJob;

    public BatchJobRunner(JobLauncher jobLauncher, Job transformJob) {
        this.jobLauncher = jobLauncher;
        this.transformJob = transformJob;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    public void runJob() throws Exception {
        jobLauncher.run(transformJob, new JobParameters());
    }
}