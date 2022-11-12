package com.project.alertcheker.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "t_alerts")
public class AlertData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "alertUrl", nullable = false)
    private String alertUrl;
    @Column(name = "isCritical", nullable = false)
    @JsonProperty
    private boolean isCritical;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlertUrl() {
        return alertUrl;
    }

    public void setAlertUrl(String alertUrl) {
        this.alertUrl = alertUrl;
    }

    public boolean isCritical() {
        return isCritical;
    }

    public void setCritical(boolean critical) {
        isCritical = critical;
    }

    public AlertData(String alertUrl, boolean isCritical) {
        this.alertUrl = alertUrl;
        this.isCritical = isCritical;
    }

    public AlertData() {
    }

    @Override
    public String toString() {
        return "AlertData{\n" +
                "id=" + id + ",\n" +
                "alertUrl='" + alertUrl + '\'' + ",\n" +
                "isCritical=" + isCritical +
                "\n}";
    }
}