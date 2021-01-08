package com.fannuo.stusys.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class Project {

    private BigInteger project_id;
    private String project_type;
    private String project_name;
    private String project_intro;
    private String project_state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date project_startdate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date project_enddate;

    public Date getProject_startdate() {
        return project_startdate;
    }

    public void setProject_startdate(Date project_startdate) {
        this.project_startdate = project_startdate;
    }

    public Date getProject_enddate() {
        return project_enddate;
    }

    public void setProject_enddate(Date project_enddate) {
        this.project_enddate = project_enddate;
    }

    public BigInteger getProject_id() {
        return project_id;
    }

    public void setProject_id(BigInteger project_id) {
        this.project_id = project_id;
    }

    public String getProject_type() {
        return project_type;
    }

    public void setProject_type(String project_type) {
        this.project_type = project_type;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getProject_intro() {
        return project_intro;
    }

    public void setProject_intro(String project_intro) {
        this.project_intro = project_intro;
    }

    public String getProject_state() {
        return project_state;
    }

    public void setProject_state(String project_state) {
        this.project_state = project_state;
    }
}
