package io.aleksandr.labs.snapshot.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NoaaWeatherService {

  private static final String STATIONS_OBSERVATIONS_LATEST_ENDPOINT = "/stations/{stationId}/observations/latest";
  private final RestTemplate restTemplate;

  public NoaaWeatherService(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String latestWeather(String stationId) {
    return restTemplate.getForObject(STATIONS_OBSERVATIONS_LATEST_ENDPOINT, String.class, stationId);
  }
}
