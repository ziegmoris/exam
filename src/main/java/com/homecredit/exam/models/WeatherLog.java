package com.homecredit.exam.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class WeatherLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String responseId;
    private String location;
    private String actualWeather;
    private String temperature;
    private Date dtimeInserted;

    public WeatherLog() {
    }


    public Long getId() {
        return id;
    }

    public String getResponseId() {
        return responseId;
    }

    public String getLocation() {
        return location;
    }

    public String getActualWeather() {
        return actualWeather;
    }

    public String getTemperature() {
        return temperature;
    }

    public Date getDtimeInserted() {
        return dtimeInserted;
    }


    public WeatherLog(String responseId, String location, String actualWeather, String temperature, Date dtimeInserted) {
        this.responseId = responseId;
        this.location = location;
        this.actualWeather = actualWeather;
        this.temperature = temperature;
        this.dtimeInserted = dtimeInserted;
    }

}