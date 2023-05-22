package io.aleksandr.labs.snapshot.service;

import org.springframework.stereotype.Service;

@Service
public class WeatherService {
  private final NoaaWeatherService noaaWeatherService;

  public WeatherService(final NoaaWeatherService noaaWeatherService) {
    this.noaaWeatherService = noaaWeatherService;
  }

  public String get(String stationId) {
    return noaaWeatherService.latestWeather(stationId);
  }
}
