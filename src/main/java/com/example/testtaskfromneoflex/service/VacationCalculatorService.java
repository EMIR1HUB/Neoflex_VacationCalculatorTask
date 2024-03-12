package com.example.testtaskfromneoflex.service;

import com.example.testtaskfromneoflex.model.VacationRequest;
import com.example.testtaskfromneoflex.model.VacationResponse;
import com.example.testtaskfromneoflex.util.HolidayManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VacationCalculatorService {
  private static final int AMOUNT_DAYS = 30;
  private final HolidayManager holidays;

  @Autowired
  public VacationCalculatorService(HolidayManager holidays) {
    this.holidays = holidays;
  }

  public VacationResponse calculateVacation(VacationRequest request) {
    if (request.getAverageSalary() < 1 || request.getVacationDays() < 1){
      return new VacationResponse();
    }

    int vacationDays = 0;

    if (request.getStartDate() != null && request.getEndDate() != null) {
      vacationDays = calculateWorkdays(request.getStartDate(), request.getEndDate());
    } else {
      vacationDays = request.getVacationDays();
    }

    double totalVacationPay = request.getAverageSalary() / AMOUNT_DAYS * vacationDays;

    VacationResponse response = new VacationResponse();
    response.setTotalVacationPay(totalVacationPay);
    return response;
  }

  private int calculateWorkdays(LocalDate start, LocalDate end) {
    int workdays = 0;
    LocalDate current = start;

    while (!current.isAfter(end)) {
      if (current.getDayOfWeek().getValue() < 6 && !holidays.isHoliday(current)) {
        workdays++;
      }
      current = current.plusDays(1);
    }
    return workdays;
  }
}
