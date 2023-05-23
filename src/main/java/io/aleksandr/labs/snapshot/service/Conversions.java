package io.aleksandr.labs.snapshot.service;

public interface Conversions {
  static double cToF(double celsius) {
    return (celsius * 1.8) + 32;
  }

  static double fToC(double fahrenheit) {
    return (fahrenheit - 32) / 1.8;
  }

  static double iToCm(double inches) {
    return inches * 2.54;
  }

  static double iToMm(double inches) {
    return iToCm(inches) * 10;
  }

  static double cmToI(double centimeters) {
    return centimeters / 2.54;
  }

  static double milesToKm(double miles) {
    return miles * 1.60934;
  }

  static double kmToMiles(double kilometers) {
    return kilometers / 1.60934;
  }
}
