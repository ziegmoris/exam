package com.homecredit.exam.utils;

import com.homecredit.exam.dtos.SimplifiedWeatherInfo;
import com.homecredit.exam.dtos.WeatherInfoResult;
import com.homecredit.exam.models.WeatherLog;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WeatherListUtil {

    private static final String INFO_LIST = "InfoList";

    private WeatherListUtil() {

    }

    public static void addToWeatherList(HttpSession session, List<SimplifiedWeatherInfo> infoList) {
        infoList.forEach(info -> {
            WeatherInfoResult result = new WeatherInfoResult();
            result.setWeatherInfo(info);
            result.setResponseId(UUID.randomUUID().toString());
            List<WeatherInfoResult> resultList = new ArrayList<>();
            if (session.getAttribute(INFO_LIST) != null) {
                resultList = (List<WeatherInfoResult>) session.getAttribute("InfoList");

            }
            resultList.add(result);
            session.setAttribute("InfoList", resultList);
        });
    }

    public static List<WeatherLog> getWeatherLogs(HttpSession session) {
        List<WeatherInfoResult> infoList = (List<WeatherInfoResult>) session.getAttribute("InfoList");
        List<WeatherLog> saveList = new ArrayList<>();
        infoList.forEach(info -> {
            WeatherLog weatherLog = new WeatherLog(info.getResponseId(), info.getWeatherInfo().getLocationName(), info.getWeatherInfo().getWeather(), info.getWeatherInfo().getTemperature(), new Date());
            saveList.add(weatherLog);
        });
        return saveList;
    }
}
