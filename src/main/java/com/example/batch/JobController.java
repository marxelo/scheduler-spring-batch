package com.example.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"", "/", "index", "index.html"})
public class JobController {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(JobScheduler.class);

  @Autowired
  JobScheduler JobScheduler;

  @RequestMapping(value = "/startJob/creditJob/{processingDate}", method = RequestMethod.GET)
  public String startBatchJob(@PathVariable String processingDate) {

    String result = JobScheduler.run(processingDate);
    LOGGER.info("processing Date: " + processingDate);
    LOGGER.info(result);

    return result;
  }

  @RequestMapping({"", "/", "index", "index.html"})
  public String index(){

      return "index";
  }  
}
