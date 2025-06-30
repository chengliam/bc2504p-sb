package com.bootcamp.demo.demo_calculator.controller.impl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bootcamp.demo.demo_calculator.controller.CalculatorOperation;

@Controller
@ResponseBody
public class CalculatorContoraller implements CalculatorOperation{
  @Override
  public Integer sum(Integer x, Integer y) {
    return x + y;
  }


  
}
