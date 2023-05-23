package io.aleksandr.labs.snapshot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

  @Value("${weather.service.url}")
  private String weatherServiceUrl;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplateBuilder().rootUri(weatherServiceUrl).build();
  }
}
