package io.aleksandr.labs.snapshot.service;

import com.apicatalog.jsonld.JsonLdError;
import com.apicatalog.jsonld.document.Document;
import com.apicatalog.jsonld.document.JsonDocument;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;

@Component
public class NoaaWeatherService {

  private static final String STATIONS_OBSERVATIONS_LATEST_ENDPOINT = "/stations/{stationId}/observations/latest";
  private final RestTemplate restTemplate;

  public NoaaWeatherService(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Document latestWeather(String stationId) throws JsonLdError {
    final String content = restTemplate.getForObject(STATIONS_OBSERVATIONS_LATEST_ENDPOINT, String.class, stationId);

    return JsonDocument.of(new ByteArrayInputStream(content.getBytes()));
  }
}
