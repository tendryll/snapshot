package io.aleksandr.labs.snapshot.service;

import com.apicatalog.jsonld.JsonLdError;
import com.apicatalog.jsonld.StringUtils;
import com.apicatalog.jsonld.document.Document;
import com.apicatalog.jsonld.document.JsonDocument;
import io.aleksandr.labs.snapshot.exception.BadGatewayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.util.Optional;

@Component
public class NoaaWeatherService {

  private static final String STATIONS_OBSERVATIONS_LATEST_ENDPOINT = "/stations/{stationId}/observations/latest";
  private final RestTemplate restTemplate;

  public NoaaWeatherService(final RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Optional<Document> latestWeather(String stationId) {
    try {
      final ResponseEntity<String> response = restTemplate.getForEntity(STATIONS_OBSERVATIONS_LATEST_ENDPOINT,
          String.class, stationId);
      final String content = response.getBody();

      if (content == null) {
        return Optional.empty();
      }

      final Document document = JsonDocument.of(new ByteArrayInputStream(content.getBytes()));

      return Optional.of(document);
    } catch (HttpStatusCodeException ex) {
      throw new BadGatewayException(ex.getStatusCode().value());
    } catch (JsonLdError e) {
      throw new BadGatewayException(HttpStatus.BAD_GATEWAY.value());
    }
  }
}
