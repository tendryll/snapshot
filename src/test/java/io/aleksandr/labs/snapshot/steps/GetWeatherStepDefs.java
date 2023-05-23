package io.aleksandr.labs.snapshot.steps;

import io.aleksandr.labs.snapshot.SpringIntegrationTestSetup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetWeatherStepDefs extends SpringIntegrationTestSetup {
  @Value(value = "${local.server.port}")
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  private String endpoint;
  private String stationId;
  private ResponseEntity<String> result;

  @Given("the endpoint is {string}")
  public void theEndpointIs(String endpoint) {
    this.endpoint = endpoint;
  }

  @Given("a station ID = {string}")
  public void aStationID(String stationId) {
    this.stationId = stationId;
  }

  @When("the endpoint is executed")
  public void theEndpointIsExecuted() {
    this.result =
        this.restTemplate.getForEntity("http://localhost:" + port + endpoint + "/" + stationId,
            String.class);
  }

  @Then("the status code is {int}")
  public void theStatusCodeIs(int statusCode) {
    assertEquals(statusCode, result.getStatusCode().value());
  }

  @And("the response is {string}")
  public void theResponseIs(String fileName) throws IOException, JSONException {
    final ClassLoader classLoader = getClass().getClassLoader();
    final InputStream inputStream = classLoader.getResourceAsStream("responses/" + fileName);
    final String data = readFromInputStream(inputStream);

    JSONAssert.assertEquals(data, result.getBody(), false);
  }

  private String readFromInputStream(InputStream inputStream)
      throws IOException {
    StringBuilder resultStringBuilder = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
      String line;
      while ((line = br.readLine()) != null) {
        resultStringBuilder.append(line);
      }
    }
    return resultStringBuilder.toString();
  }
}
