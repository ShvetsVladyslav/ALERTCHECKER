package com.project.alertcheker.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;


//@Table(name = "t_alerts")
@Document(collection = "AlertData")
public class AlertData {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id", nullable = false)
    @MongoId
    @Indexed(unique = true)
    private String id;
//    @Column(name = "alertUrl", nullable = false)
    @Field(name = "alertUrl")
    private String alertUrl;
//    @Column(name = "isCritical", nullable = false)
    @JsonProperty
    @Field(name = "isCritical")
    private boolean isCritical;
    @Field(name = "comment")
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public AlertData(String alertUrl, boolean isCritical, String comment) {
        this.alertUrl = alertUrl;
        this.isCritical = isCritical;
        this.comment = comment;
    }

    public AlertData() {
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Alert Data:\n");
        result.append("URL: ").append(alertUrl).append(";\n");
        if(isCritical){
            result.append("Critical: ").append("\u01C3").append("\n");
        }
        else {
            result.append("Critical: ").append("\u2705").append("\n");
        }
        result.append("Comment: ").append(comment);
        return result.toString();
    }
}