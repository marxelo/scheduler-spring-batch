package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobScheduler.class);

  @Autowired
  JobScheduler JobScheduler;

  @GetMapping("/startJob")
  public JobExecutionRequest jer(@RequestParam(value = "jobName", defaultValue = "creditJob") String jobName,
      @RequestParam(value = "fileDate") String fileDate) {
    if ((!jobName.equals("creditJob")) && !jobName.equals("debitJob")) {
      LOGGER.info(jobName);
      return new JobExecutionRequest(jobName, fileDate, "Invalid job name");
    }
    String jobStatus = JobScheduler.run(fileDate);
    LOGGER.info(jobStatus);
    return new JobExecutionRequest(jobName, fileDate, jobStatus);
  }

}
