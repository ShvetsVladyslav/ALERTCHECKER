package com.project.alertcheker.Controller;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlertController {
    private static final Logger logger = LoggerFactory.getLogger(AlertController.class);
    @Autowired
    AlertService alertService;
    @GetMapping("/getListData")
    public List<AlertData> getListData(@RequestParam String alertUrl){
        logger.info("Finded data:\n" + alertService.getAlertData(alertUrl).toString());
        return alertService.getAlertData(alertUrl);
    }
    @PutMapping("/insertData")
    public boolean insertData(@RequestBody AlertData data){
        try{
            logger.info("Data to insert:\n" + "URL: " + data.getAlertUrl() + "\n" + "isCritical: " + data.isCritical());
            alertService.insertData(data);
            return true;
        }catch (Exception exception){
            logger.info(exception.getMessage());
            return false;
        }
    }
    @DeleteMapping("/deleteData")
    public boolean deleteData(@RequestBody AlertData alertData){
        try {
            alertService.deleteData(alertData.getAlertUrl());
            return true;
        }catch (Exception exception){
            logger.info(exception.getMessage());
            return false;
        }
    }
    @PatchMapping("/updateData")
    public boolean updateData(@RequestParam String currentUrl,@RequestBody AlertData alertData){
        try{
            alertService.updateData(currentUrl, alertData);
            return true;
        }catch (Exception exception){
            logger.info(exception.getMessage());
            return false;
        }
    }
}