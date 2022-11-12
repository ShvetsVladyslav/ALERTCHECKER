package com.project.alertcheker.Controller;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Service.AlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AlertController {
    private static final Logger logger = LoggerFactory.getLogger(AlertController.class);
    @Autowired
    AlertService alertService;
    @GetMapping("/getData")
    public AlertData getData(@RequestBody String Url){
        return alertService.getData(Url);
    }
    @PutMapping("/insertData")
    public boolean insertData(@RequestBody AlertData data){
        try{
            logger.info("Data to insert:\n" + "URL: " + data.getAlertUrl() + "\n" + "isCritical: " + data.isCritical());
            alertService.insertData(data);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
    @DeleteMapping("/deleteData")
    public boolean deleteData(@RequestBody String Url){
        try {
            alertService.deleteData(Url);
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
