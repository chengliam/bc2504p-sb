package com.bootcamp.demo.demo_api.lib;

import lombok.Getter;

@Getter
public enum SysCode {
  OK(0, "success."), //
  FAIL(999999, "fail."), //
  ;

  private int code;
  private String message;

  private SysCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}