package io.aleksandr.labs.snapshot.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConversionsTest {

  @Test
  void convert_celsius_to_fahrenheit() {
    assertEquals(86.0, Conversions.cToF(30));
  }

  @Test
  void convert_fahrenheit_celsius() {
    assertEquals(30.0, Conversions.fToC(86.0));
  }

  @Test
  void convert_inch_to_centimeters() {
    assertEquals(76.2, Conversions.iToCm(30));
  }

  @Test
  void convert_inch_to_millimeters() {
    assertEquals(762.0, Conversions.iToMm(30));
  }

  @Test
  void convert_centimeters_to_inches() {
    assertEquals(30.0, Conversions.cmToI(76.2));
  }

  @Test
  void convert_miles_to_kilometers() {
    assertEquals(16.0934, Conversions.milesToKm(10.0));
  }

  @Test
  void convert_kilometers_to_miles() {
    assertEquals(10.0, Conversions.kmToMiles(16.0934));
  }
}