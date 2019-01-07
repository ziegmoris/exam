package com.homecredit.exam.services;

import com.homecredit.exam.dtos.WeatherGroup;
import com.homecredit.exam.models.WeatherLog;
import com.homecredit.exam.repositories.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final Environment env;

    private final RestTemplate restTemplate;

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder, Environment env, WeatherRepository weatherRepository) {
        this.restTemplate = restTemplateBuilder.build();
        this.env = env;
        this.weatherRepository = weatherRepository;
    }

    public WeatherGroup getWeatherGroup() {
        String url = "http://api.openweathermap.org/data/2.5/group?id="
                + env.getProperty("weather.ids") +
                "&units=metric&appid="
                + env.getProperty("weather.key");
        return this.restTemplate.getForObject(url, WeatherGroup.class);
    }

    public void saveWeatherLog(List<WeatherLog> weatherLogList) {
        List<WeatherLog> finalLogList = new ArrayList<>();
        for (int i = weatherLogList.size() - 1; i >= 0; i--) {
            if (finalLogList.size() == 5) {
                break;
            }
            WeatherLog log = weatherRepository.findByResponseId(weatherLogList.get(i).getResponseId());
            if (log == null) {
                finalLogList.add(weatherLogList.get(i));
            }
        }
        weatherRepository.saveAll(finalLogList);
    }

    public Iterable<WeatherLog> getAllWeatherLogs() {
        return weatherRepository.findAll();
    }
}