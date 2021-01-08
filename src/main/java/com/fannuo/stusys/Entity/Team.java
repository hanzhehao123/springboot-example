package com.fannuo.stusys.Entity;

import java.math.BigInteger;

public class Team {

    private BigInteger team_id;
    private String team_name;
    private String team_project;
    private String team_leader;
    private String team_mate;
    private String team_intro;
    private Integer team_progress;
    private Integer team_grade1;
    private Integer team_grade2;
    private Integer team_grade3;
    private Integer team_grade;
    private String team_member_id;

    public BigInteger getTeam_id() {
        return team_id;
    }

    public void setTeam_id(BigInteger team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_project() {
        return team_project;
    }

    public void setTeam_project(String team_project) {
        this.team_project = team_project;
    }

    public String getTeam_leader() {
        return team_leader;
    }

    public void setTeam_leader(String team_leader) {
        this.team_leader = team_leader;
    }

    public String getTeam_mate() {
        return team_mate;
    }

    public void setTeam_mate(String team_mate) {
        this.team_mate = team_mate;
    }

    public String getTeam_intro() {
        return team_intro;
    }

    public void setTeam_intro(String team_intro) {
        this.team_intro = team_intro;
    }

    public Integer getTeam_progress() {
        return team_progress;
    }

    public void setTeam_progress(Integer team_progress) {
        this.team_progress = team_progress;
    }

    public Integer getTeam_grade1() {
        return team_grade1;
    }

    public void setTeam_grade1(Integer team_grade1) {
        this.team_grade1 = team_grade1;
    }

    public Integer getTeam_grade2() {
        return team_grade2;
    }

    public void setTeam_grade2(Integer team_grade2) {
        this.team_grade2 = team_grade2;
    }

    public Integer getTeam_grade3() {
        return team_grade3;
    }

    public void setTeam_grade3(Integer team_grade3) {
        this.team_grade3 = team_grade3;
    }

    public Integer getTeam_grade() {
        return team_grade;
    }

    public void setTeam_grade(Integer team_grade) {
        this.team_grade = team_grade;
    }

    public String getTeam_member_id() {
        return team_member_id;
    }

    public void setTeam_member_id(String team_member_id) {
        this.team_member_id = team_member_id;
    }
}
