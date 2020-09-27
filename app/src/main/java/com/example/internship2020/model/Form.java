package com.example.internship2020.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Form {

    @Expose
    @SerializedName("ID") private int id;

    @Expose
    @SerializedName("StdID") private String stdId;

    @Expose
    @SerializedName("StdName") private String stdName;

    @Expose
    @SerializedName("StdSem") private int stdSem;

    @Expose
    @SerializedName("EventType") private String eventType;

    @Expose
    @SerializedName("EventName") private String eventName;

    @Expose
    @SerializedName("FromDate") private String fromDate;

    @Expose
    @SerializedName("ToDate") private String toDate;

    @Expose
    @SerializedName("ClgScholarship") private int clgSch;

    @Expose
    @SerializedName("ExtScholarship") private int extSch;

    @Expose
    @SerializedName("DriveLink") private String driveLink;

    @Expose
    @SerializedName("Accepted") private int accepted;

    @Expose
    @SerializedName("Date") private String date;

    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStdId() {
        return stdId;
    }

    public void setStdId(String stdId) {
        this.stdId = stdId;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public int getStdSem() {
        return stdSem;
    }

    public void setStdSem(int stdSem) {
        this.stdSem = stdSem;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getClgSch() {
        return clgSch;
    }

    public void setClgSch(int clgSch) {
        this.clgSch = clgSch;
    }

    public int getExtSch() {
        return extSch;
    }

    public void setExtSch(int extSch) {
        this.extSch = extSch;
    }

    public String getDriveLink() {
        return driveLink;
    }

    public void setDriveLink(String driveLink) {
        this.driveLink = driveLink;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
