package io.aleksandr.labs.snapshot.model;

public class DataPoint {
  private final String unitType;
  private final String value;

  public DataPoint(final String unitType, final String value) {
    this.unitType = unitType;
    this.value = value;
  }

  public String getUnitType() {
    return unitType;
  }

  public String getValue() {
    return value;
  }
}
