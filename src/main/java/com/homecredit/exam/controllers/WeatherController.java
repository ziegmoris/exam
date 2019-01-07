package com.homecredit.exam.controllers;

import com.homecredit.exam.dtos.SimplifiedWeatherInfo;
import com.homecredit.exam.dtos.WeatherGroup;
import com.homecredit.exam.mappers.WeatherInfoRequestToUiMapper;
import com.homecredit.exam.models.WeatherLog;
import com.homecredit.exam.services.WeatherService;
import com.homecredit.exam.utils.WeatherListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherController {

    private final WeatherService service;

    @Autowired
    public WeatherController(WeatherService service) {
        this.service = service;
    }

    @RequestMapping(value = "/getWeatherInfo")
    public List<SimplifiedWeatherInfo> getWeatherInfo(HttpSession session) {
        List<SimplifiedWeatherInfo> infoList = new ArrayList<>();
        WeatherGroup weatherGroup = service.getWeatherGroup();
        weatherGroup.getList().forEach(weatherInfo -> infoList.add(WeatherInfoRequestToUiMapper.mapToUi(weatherInfo)));

        WeatherListUtil.addToWeatherList(session, infoList);
        return infoList;
    }

    @RequestMapping(value = "/saveWeatherInfo")
    public String saveWeatherInfo(HttpSession session) {

        if (session.getAttribute("InfoList") == null) {
            return "No weather info requests made yet";
        } else {
            List<WeatherLog> saveList = WeatherListUtil.getWeatherLogs(session);
            try {
                service.saveWeatherLog(saveList);
                return "Success";
            } catch (Exception e) {
                return "Failed";
            }
        }
    }

    @RequestMapping(value = "/getAllSaved")
    public Iterable<WeatherLog> getAllSaved(){
        return service.getAllWeatherLogs();
    }

}
