package com.project.alertcheker.Service;

import com.project.alertcheker.Entity.AlertData;
import com.project.alertcheker.Repository.AlertDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    @Autowired
    private AlertDataRepository alertDataRepository;
    public AlertData getData(String Url){
        return alertDataRepository.findByAlertUrl(Url);
    }
    public List<AlertData> getAlertData(String Url){
        return alertDataRepository.findByAlertUrlContaining(Url);
    }
    public void insertData(AlertData data){
        alertDataRepository.save(data);
    }
    public void deleteData(String Url){
        AlertData data = alertDataRepository.findByAlertUrl(Url);
        alertDataRepository.deleteById(data.getId());
    }
    public void updateData(String Url, AlertData alertData){
        AlertData data = alertDataRepository.findByAlertUrl(Url);
        data.setAlertUrl(alertData.getAlertUrl());
        data.setCritical(alertData.isCritical());
        alertDataRepository.save(data);
    }
}