package com.example.testtaskfromneoflex;

import com.example.testtaskfromneoflex.model.VacationRequest;
import com.example.testtaskfromneoflex.model.VacationResponse;
import com.example.testtaskfromneoflex.service.VacationCalculatorService;
import com.example.testtaskfromneoflex.util.HolidayManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

@SpringBootTest
class TestTaskFromNeoflexApplicationTests {

  @Autowired
  private VacationCalculatorService vacationCalculatorService;

  @MockBean
  private HolidayManager holidayManager;

  @Test
  void testCalculateVacation(){
    when(holidayManager.isHoliday(LocalDate.of(2024, 3, 8))).thenReturn(true);

    // Простой вариант
    VacationRequest request = new VacationRequest(60000, 20, null, null);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(40000, response.getTotalVacationPay()); // 60000 / 30 * 20 = 40000

    // Введены даты
    LocalDate startDate = LocalDate.of(2024, 3, 1); // Март 1, 2024
    LocalDate endDate = LocalDate.of(2024, 3, 29); // Март 29, 2024 (20 рабочих дней)
    request = new VacationRequest(60000, 0, startDate, endDate);
    response = vacationCalculatorService.calculateVacation(request);
    assertEquals(40000, response.getTotalVacationPay()); // 60000 / 30 * 20 = 40000
  }

  @Test
  void testCalculateVacationWithHolidays() {
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 1))).thenReturn(true); // Январь 1, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 2))).thenReturn(true); // Январь 2, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 3))).thenReturn(true); // Январь 3, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 4))).thenReturn(true); // Январь 4, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 5))).thenReturn(true); // Январь 5, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 6))).thenReturn(true); // Январь 6, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 7))).thenReturn(true); // Январь 7, 2024 праздник
    when(holidayManager.isHoliday(LocalDate.of(2024, 1, 8))).thenReturn(true); // Январь 8, 2024 праздник

    LocalDate startDate = LocalDate.of(2024, 1, 1); // Январь 1, 2024
    LocalDate endDate = LocalDate.of(2024, 1, 10); // Январь 10, 2024
    VacationRequest request = new VacationRequest(60000, 0, startDate, endDate);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(4000, response.getTotalVacationPay()); // 60000 / 30 * 2 = 4000 (excluding 8 праздничных)
  }

  @Test
  void testCalculateVacationWithStartEndDate() {
    LocalDate startDate = LocalDate.of(2024, 1, 1); // Апрель 1, 2024
    LocalDate endDate = LocalDate.of(2024, 1, 5); // Апрель 5, 2024
    VacationRequest request = new VacationRequest(60000, 0, startDate, endDate);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(10000, response.getTotalVacationPay()); // 60000 / 30 * 5 = 10000
  }

  @Test
  void testCalculateVacationWithNegativeSalary() {
    VacationRequest request = new VacationRequest(-1000, 10, null, null);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(0, response.getTotalVacationPay()); // Salary cannot be negative
  }

  @Test
  void testCalculateVacationWithNegativeVacationDays() {
    VacationRequest request = new VacationRequest(3000, -10, null, null);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(0, response.getTotalVacationPay()); // Vacation days cannot be negative
  }

  @Test
  void testCalculateVacationWithZeroSalary() {
    VacationRequest request = new VacationRequest(0, 10, null, null);
    VacationResponse response = vacationCalculatorService.calculateVacation(request);
    assertEquals(0, response.getTotalVacationPay()); // Salary cannot be zero
  }
}
