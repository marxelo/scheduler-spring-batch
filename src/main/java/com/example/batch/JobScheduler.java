package com.example.batch;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class JobScheduler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(JobScheduler.class);

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job creditJob;

  private JobExecution execution;

  String msg = null;

  public String run(String processingDate){     
    
    LOGGER.info("Processing date> " + processingDate);
    try {
        execution = jobLauncher.run(creditJob, new JobParametersBuilder()
        .addDate("date", new Date()).toJobParameters());
        LOGGER.info("Job Started");
        System.out.println("Execution status: "+ execution.getStatus());
        msg = "JOB STARTED";
    } catch (JobExecutionAlreadyRunningException e) {
        e.printStackTrace();
    } catch (JobRestartException e) {           
        e.printStackTrace();
    } catch (JobInstanceAlreadyCompleteException e) {           
        e.printStackTrace();
    } catch (JobParametersInvalidException e) {         
        e.printStackTrace();
    } finally {
      if (msg == null) {
        msg = "Job Error";

      }
    }
    return msg;
}

}
