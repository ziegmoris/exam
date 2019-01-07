package com.homecredit.exam.dtos;

import java.util.List;

public class WeatherGroup {
    private int cnt;
    private List<WeatherInfo> list;

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeatherInfo> getList() {
        return list;
    }

    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }
}
