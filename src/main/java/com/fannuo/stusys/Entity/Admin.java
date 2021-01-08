package com.fannuo.stusys.Entity;

import java.math.BigInteger;

public class Admin {

    private BigInteger admin_id;
    private String admin_username;
    private String admin_password;
    private boolean admin_authority_student;
    private boolean admin_authority_project;
    private boolean admin_authority_team;
    private boolean admin_authority_root;
    private String admin_note;

    public BigInteger getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(BigInteger admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_username() {
        return admin_username;
    }

    public void setAdmin_username(String admin_username) {
        this.admin_username = admin_username;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public boolean isAdmin_authority_student() {
        return admin_authority_student;
    }

    public void setAdmin_authority_student(boolean admin_authority_student) {
        this.admin_authority_student = admin_authority_student;
    }

    public boolean isAdmin_authority_project() {
        return admin_authority_project;
    }

    public void setAdmin_authority_project(boolean admin_authority_project) {
        this.admin_authority_project = admin_authority_project;
    }

    public boolean isAdmin_authority_team() {
        return admin_authority_team;
    }

    public void setAdmin_authority_team(boolean admin_authority_team) {
        this.admin_authority_team = admin_authority_team;
    }

    public boolean isAdmin_authority_root() {
        return admin_authority_root;
    }

    public void setAdmin_authority_root(boolean admin_authority_root) {
        this.admin_authority_root = admin_authority_root;
    }

    public String getAdmin_note() {
        return admin_note;
    }

    public void setAdmin_note(String admin_note) {
        this.admin_note = admin_note;
    }
}
