package com.bottle.userWall.home.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
private String status;
private Object data;

  public ResponseDto(String status, Object data) {
  this.status = status;
  this.data = data;
 }
 public String getStatus() {
  return status;
 }

 public void setStatus(String status) {
  this.status = status;
 }

 public Object getData() {
  return data;
 }

 public void setData(Object data) {
  this.data = data;
 }

 public ResponseDto() {


 }

}



