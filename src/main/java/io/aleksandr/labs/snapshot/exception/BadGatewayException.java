package io.aleksandr.labs.snapshot.exception;

public class BadGatewayException extends RuntimeException {
  private final int gatewayStatusCode;

  public BadGatewayException(final int gatewayStatusCode) {
    this.gatewayStatusCode = gatewayStatusCode;
  }

  public int getGatewayStatusCode() {
    return gatewayStatusCode;
  }
}
