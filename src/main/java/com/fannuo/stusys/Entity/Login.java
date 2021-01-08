package com.fannuo.stusys.Entity;

import java.math.BigInteger;
import java.util.Date;

public class Login {
    private BigInteger login_timestamp;
    private String login_type;
    private BigInteger login_userid;
    private String login_datetime;

    public BigInteger getLogin_timestamp() {
        return login_timestamp;
    }

    public void setLogin_timestamp(BigInteger login_timestamp) {
        this.login_timestamp = login_timestamp;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public BigInteger getLogin_userid() {
        return login_userid;
    }

    public void setLogin_userid(BigInteger login_userid) {
        this.login_userid = login_userid;
    }

    public String getLogin_datetime() {
        return login_datetime;
    }

    public void setLogin_datetime(String login_datetime) {
        this.login_datetime = login_datetime;
    }
}
