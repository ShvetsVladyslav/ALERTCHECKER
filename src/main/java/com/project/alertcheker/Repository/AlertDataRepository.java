package com.project.alertcheker.Repository;

import com.project.alertcheker.Entity.AlertData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertDataRepository extends JpaRepository<AlertData, Long> {
    AlertData findByAlertUrl(String Url);
}
