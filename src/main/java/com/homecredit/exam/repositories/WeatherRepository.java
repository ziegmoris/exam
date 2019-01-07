package com.homecredit.exam.repositories;

import com.homecredit.exam.models.WeatherLog;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherLog, Long> {
    WeatherLog findByResponseId(String responseId);
}
