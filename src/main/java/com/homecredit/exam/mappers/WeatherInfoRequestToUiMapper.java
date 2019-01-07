package com.homecredit.exam.mappers;

import com.homecredit.exam.dtos.SimplifiedWeatherInfo;
import com.homecredit.exam.dtos.Weather;
import com.homecredit.exam.dtos.WeatherInfo;

import java.util.stream.Collectors;

public class WeatherInfoRequestToUiMapper {
    private WeatherInfoRequestToUiMapper() {
        throw new IllegalStateException("Utility Class");
    }

    public static SimplifiedWeatherInfo mapToUi(WeatherInfo weatherInfo) {
        SimplifiedWeatherInfo uiInfo = new SimplifiedWeatherInfo();
        uiInfo.setLocationName(weatherInfo.getName());
        uiInfo.setTemperature(String.valueOf(weatherInfo.getMain().getTemp()));
        uiInfo.setWeather(weatherInfo.getWeather().stream().map(Weather::getMain).collect(Collectors.joining("|")));
        return uiInfo;
    }
}
