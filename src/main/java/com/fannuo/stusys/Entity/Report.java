package com.fannuo.stusys.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

public class Report {

    private BigInteger report_id;
    private String report_title;
    private String report_project_name;
    private BigInteger report_team_id;
    private BigInteger report_student_id;
    private String report_student_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date report_date;
    private String report_content;
    private String report_type;
    private String report_state;

    public String getReport_state() {
        return report_state;
    }

    public void setReport_state(String report_state) {
        this.report_state = report_state;
    }

    public BigInteger getReport_id() {
        return report_id;
    }

    public void setReport_id(BigInteger report_id) {
        this.report_id = report_id;
    }

    public String getReport_title() {
        return report_title;
    }

    public void setReport_title(String report_title) {
        this.report_title = report_title;
    }

    public String getReport_project_name() {
        return report_project_name;
    }

    public void setReport_project_name(String report_project_name) {
        this.report_project_name = report_project_name;
    }

    public BigInteger getReport_team_id() {
        return report_team_id;
    }

    public void setReport_team_id(BigInteger report_team_id) {
        this.report_team_id = report_team_id;
    }

    public BigInteger getReport_student_id() {
        return report_student_id;
    }

    public void setReport_student_id(BigInteger report_student_id) {
        this.report_student_id = report_student_id;
    }

    public String getReport_student_name() {
        return report_student_name;
    }

    public void setReport_student_name(String report_student_name) {
        this.report_student_name = report_student_name;
    }

    public Date getReport_date() {
        return report_date;
    }

    public void setReport_date(Date report_date) {
        this.report_date = report_date;
    }

    public String getReport_content() {
        return report_content;
    }

    public void setReport_content(String report_content) {
        this.report_content = report_content;
    }

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
        this.report_type = report_type;
    }
}
