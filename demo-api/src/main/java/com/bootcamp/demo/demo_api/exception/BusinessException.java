package com.bootcamp.demo.demo_api.exception;

import lombok.Getter;

// ! For spring boot, usually we use "unchecked" exception
// because we have Global Exception Handling (RestControllerAdvice)
@Getter
public class BusinessException extends RuntimeException {
  private int code;

  public BusinessException(SysError sysError) {
    super(sysError.getMessage());
    this.code = sysError.getCode();
  }

  // private UserNotFoundException(int code, String message) {
  //   super(message);
  //   this.code = code;
  // }
}