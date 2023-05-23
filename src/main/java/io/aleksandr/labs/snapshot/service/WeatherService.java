package io.aleksandr.labs.snapshot.service;

import com.apicatalog.jsonld.JsonLdError;
import com.apicatalog.jsonld.document.Document;
import io.aleksandr.labs.snapshot.exception.BadGatewayException;
import io.aleksandr.labs.snapshot.model.DataPoint;
import io.aleksandr.labs.snapshot.model.DataTypes;
import io.aleksandr.labs.snapshot.model.stations.observations.response.WeatherInfo;
import jakarta.json.JsonObject;
import jakarta.json.JsonStructure;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class WeatherService {
  private final NoaaWeatherService noaaWeatherService;

  public WeatherService(final NoaaWeatherService noaaWeatherService) {
    this.noaaWeatherService = noaaWeatherService;
  }

  public WeatherInfo get(String stationId) {
    final WeatherInfo weatherInfo = new WeatherInfo();
    final Optional<Document> document = noaaWeatherService.latestWeather(stationId);

    if (document.isPresent()) {
      final Optional<JsonStructure> jsonContent = document.get().getJsonContent();

      if (jsonContent.isPresent()) {
        addData(weatherInfo.getData(), "temperature", jsonContent.get().getValue("/properties/temperature")
            .asJsonObject());
        addData(weatherInfo.getData(), "dewpoint", jsonContent.get().getValue("/properties/dewpoint").asJsonObject());
        addData(weatherInfo.getData(), "windDirection", jsonContent.get().getValue("/properties/windDirection")
            .asJsonObject());
        addData(weatherInfo.getData(), "windSpeed", jsonContent.get().getValue("/properties/windSpeed").asJsonObject());
        addData(weatherInfo.getData(), "windGust", jsonContent.get().getValue("/properties/windGust").asJsonObject());
        addData(weatherInfo.getData(), "barometricPressure", jsonContent.get()
            .getValue("/properties/barometricPressure")
            .asJsonObject());
        addData(weatherInfo.getData(), "seaLevelPressure", jsonContent.get().getValue("/properties/seaLevelPressure")
            .asJsonObject());
        addData(weatherInfo.getData(), "visibility", jsonContent.get().getValue("/properties/visibility")
            .asJsonObject());
        addData(weatherInfo.getData(), "maxTemperatureLast24Hours", jsonContent.get()
            .getValue("/properties/maxTemperatureLast24Hours").asJsonObject());
        addData(weatherInfo.getData(), "minTemperatureLast24Hours", jsonContent.get()
            .getValue("/properties/minTemperatureLast24Hours").asJsonObject());
        addData(weatherInfo.getData(), "precipitationLast3Hours", jsonContent.get()
            .getValue("/properties/precipitationLast3Hours").asJsonObject());
        addData(weatherInfo.getData(), "relativeHumidity", jsonContent.get().getValue("/properties/relativeHumidity")
            .asJsonObject());
        addData(weatherInfo.getData(), "windChill", jsonContent.get().getValue("/properties/windChill").asJsonObject());
        addData(weatherInfo.getData(), "heatIndex", jsonContent.get().getValue("/properties/heatIndex").asJsonObject());
      }
    }

    return weatherInfo;
  }

  private void addData(Map<String, DataPoint> data, String name, JsonObject element) {
    final String unitCode = element.getString("unitCode").replace("wmoUnit:", "");
    final String unit = DataTypes.getValue(unitCode);
    final String value = element.get("value").toString();

    if (!"null".equalsIgnoreCase(value)) {
      data.put(name, new DataPoint(unit, value));
    }
  }
}
