package com.project.alertcheker.Repository;

import com.project.alertcheker.Entity.AlertData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AlertDataRepoMongo extends MongoRepository<AlertData, String> {
    AlertData findByAlertUrl(String alertUrl);
    List<AlertData> findByAlertUrlContaining(String alertUrl);
}
