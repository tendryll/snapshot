package io.aleksandr.labs.snapshot;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.stereotype.Service;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class MockServer implements AutoCloseable {
  private final WireMockServer server;

  public MockServer() {
    server = new WireMockServer(
        options()
            .port(8181)
            .usingFilesUnderClasspath("wiremock/"));
  }

  @Override
  public void close() {
    server.stop();
  }
}