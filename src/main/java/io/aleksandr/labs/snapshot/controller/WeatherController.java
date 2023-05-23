package io.aleksandr.labs.snapshot.controller;

import io.aleksandr.labs.snapshot.model.stations.observations.response.WeatherInfo;
import io.aleksandr.labs.snapshot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/weather")
class WeatherController {

  private final WeatherService weatherService;

  @Autowired
  public WeatherController(final WeatherService weatherService) {
    this.weatherService = weatherService;
  }

  @GetMapping(value = "/{stationId}", produces = {"application/json"})
  ResponseEntity<WeatherInfo> get(@PathVariable String stationId) {
    final HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.set("aleksandr.io", "sean@aleksandr.cc");

    return ResponseEntity.ok().headers(responseHeaders).body(weatherService.get(stationId));
  }
}
