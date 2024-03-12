package com.example.testtaskfromneoflex.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class HolidayManager {
  private final Set<LocalDate> holidays;

  public HolidayManager(){
    holidays = new HashSet<>();
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 1)); // 1 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 2)); // 2 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 3)); // 3 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 4)); // 4 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 5)); // 5 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 6)); // 6 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 7)); // 7 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 1, 8)); // 8 января
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 2, 23)); // 23 февраля
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 3, 8)); // 8 марта
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 5, 1)); // 1 мая
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 5, 2)); // 2 мая
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 5, 3)); // 3 мая
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 6, 12)); // 12 июня
    holidays.add(LocalDate.of(LocalDate.now().getYear(), 11, 4)); // 4 ноября
  }

  public boolean isHoliday(LocalDate date){
    return holidays.contains(date);
  }
}
