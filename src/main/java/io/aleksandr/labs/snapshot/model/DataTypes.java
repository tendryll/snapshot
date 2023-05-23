package io.aleksandr.labs.snapshot.model;

import java.util.Map;

public class DataTypes {
  private static final Map<String, String> unitTypes = Map.of(
      "m", "m",
      "degC", "C",
      "degree_(angle)", "∘",
      "km_h-1", "km/h",
      "mm", "mm",
      "percent", "%"
  );

  private DataTypes() {
  }

  public static String getValue(String key) {
    return unitTypes.get(key);
  }
}
