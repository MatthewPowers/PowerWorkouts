package com.PowerWorkouts.powerworkouts.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class userSession {

    @Id
    @GeneratedValue
    private int id;

    private int sessionId;
    private String userNameSession;
    private String userLogTime;

    private boolean validSession = false;

    public userSession(int sessionId, String userNameSession, boolean validSession, String userLogTime) {
        this.sessionId = sessionId;
        this.userNameSession = userNameSession;
        this.validSession = validSession;
    }

    public userSession()
    {
    }

    public String getUserLogTime() {
        return userLogTime;
    }

    public void setUserLogTime(String userLogTime) {
        this.userLogTime = userLogTime;
    }

    public boolean isValidSession() {
        return validSession;
    }

    public void setValidSession(boolean validSession) {
        this.validSession = validSession;
    }

    public int getId() {
        return id;
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getuserNameSession() {
        return userNameSession;
    }

    public void setuserNameSession(String userNameSession) {
        this.userNameSession = userNameSession;
    }
}
