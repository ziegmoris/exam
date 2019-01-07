package com.homecredit.exam.dtos;

public class WeatherInfoResult {
    private String responseId;
    private SimplifiedWeatherInfo weatherInfo;


    public SimplifiedWeatherInfo getWeatherInfo() {
        return weatherInfo;
    }

    public void setWeatherInfo(SimplifiedWeatherInfo weatherInfo) {
        this.weatherInfo = weatherInfo;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }
}
