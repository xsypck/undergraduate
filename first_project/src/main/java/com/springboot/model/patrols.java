package com.springboot.model;

public class patrols {
    private Integer id;

    private Integer parentid;

    private String wwprotection;

    private String environmentprotection;

    private String protectionproject;

    private String date;

    private String username;

    private String suggestion;

    private String illegal;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) { this.suggestion = suggestion; }

    public String getIllegal() {
        return illegal;
    }

    public void setIllegal(String illegal) {
        this.illegal = illegal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getWwprotection() {
        return wwprotection;
    }

    public void setWwprotection(String wwprotection) {
        this.wwprotection = wwprotection;
    }

    public String getEnvironmentprotection() {
        return environmentprotection;
    }

    public void setEnvironmentprotection(String environmentprotection) {
        this.environmentprotection = environmentprotection;
    }

    public String getProtectionproject() {
        return protectionproject;
    }

    public void setProtectionproject(String protectionproject) {
        this.protectionproject = protectionproject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}