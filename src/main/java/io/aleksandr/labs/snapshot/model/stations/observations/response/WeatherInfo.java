package io.aleksandr.labs.snapshot.model.stations.observations.response;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.aleksandr.labs.snapshot.model.DataPoint;

import java.util.HashMap;
import java.util.Map;

public class WeatherInfo {
  // make this immutable
  private final Map<String, DataPoint> data = new HashMap<>();

  public void addDataElement(final String key, final String unit, final String value) {
    data.put(key, new DataPoint(unit, value));
  }

  @JsonAnyGetter // unwraps the map in the JSON response
  public Map<String, DataPoint> getData() {
    return data;
  }
}
