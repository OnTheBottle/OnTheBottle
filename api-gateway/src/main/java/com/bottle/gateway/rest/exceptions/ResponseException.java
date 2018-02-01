package com.bottle.gateway.rest.exceptions;

public class ResponseException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = -3165952015658781376L;

  private String message;
  private int bottleErrorCode;
  private int errorCode;

  public ResponseException(String message, int bottleErrorCode, int errorCode) {
    this.errorCode = errorCode;
    this.errorCode = bottleErrorCode;
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getBottleErrorCode() {
    return bottleErrorCode;
  }

  public void setBottleErrorCode(int bottleErrorCode) {
    this.bottleErrorCode = bottleErrorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(int errorCode) {
    this.errorCode = errorCode;
  }


}
