package com.project.alertcheker.Service;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Repository.AlertDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertService {
    @Autowired
    private AlertDataRepository alertDataRepository;
    public AlertData getData(String Url){
        return alertDataRepository.findByAlertUrl(Url);
    }
    public void insertData(AlertData data){
        alertDataRepository.save(data);
    }
    public void deleteData(String Url){
        AlertData data = alertDataRepository.findByAlertUrl(Url);
        alertDataRepository.delete(data);
    }
}