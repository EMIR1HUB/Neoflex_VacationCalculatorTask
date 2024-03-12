package com.example.testtaskfromneoflex.controller;

import com.example.testtaskfromneoflex.model.VacationRequest;
import com.example.testtaskfromneoflex.model.VacationResponse;
import com.example.testtaskfromneoflex.service.VacationCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/calculate")
public class VacationCalculatorController {

  @Autowired
  private VacationCalculatorService calculatorService;

  @GetMapping()
  public VacationResponse getCalculatedVacation(@RequestParam double averageSalary,
                                                @RequestParam int vacationDays,
                                                @RequestParam(required = false) LocalDate startDate,
                                                @RequestParam(required = false) LocalDate endDate) {
    VacationRequest vacationRequest = new VacationRequest(averageSalary, vacationDays, startDate, endDate);
    return calculatorService.calculateVacation(vacationRequest);
  }
}
