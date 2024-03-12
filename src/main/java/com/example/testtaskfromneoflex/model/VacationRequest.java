package com.example.testtaskfromneoflex.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationRequest {
  private double averageSalary;
  private int vacationDays;
  private LocalDate startDate;
  private LocalDate endDate;
}
