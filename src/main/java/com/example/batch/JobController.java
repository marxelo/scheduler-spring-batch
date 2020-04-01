package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/startJob")
public class JobController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(JobScheduler.class);

  @Autowired
  JobScheduler JobScheduler;

  @RequestMapping(value = "/creditJob/{processingDate}", method = RequestMethod.GET)
  public String toggleBatchJob(@PathVariable String processingDate) {
    // boolean toggleEnabled =
    //     !JobScheduler.isEnabled();
    // JobScheduler.setEnabled(toggleEnabled);

    String result = JobScheduler.run();
    LOGGER.info("processing Date: " + processingDate);
    LOGGER.info(result);

    return result;
  }
}
