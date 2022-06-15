package com.springboot.model;

public class healthExaminations {
    private Integer id;

    private Integer parentid;

    private String height;

    private String length;

    private String width;

    private String username;

    private String temp;

    private String humidity;

    private String airquality;

    private String displaceme;

    private String smallchange;

    private String status;

    private String date;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getAirquality() {
        return airquality;
    }

    public void setAirquality(String airquality) {
        this.airquality = airquality;
    }

    public String getDisplaceme() {
        return displaceme;
    }

    public void setDisplaceme(String displaceme) {
        this.displaceme = displaceme;
    }

    public String getSmallchange() {
        return smallchange;
    }

    public void setSmallchange(String smallchange) {
        this.smallchange = smallchange;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}