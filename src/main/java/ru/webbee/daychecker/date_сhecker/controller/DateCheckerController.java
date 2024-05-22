package ru.webbee.daychecker.date_сhecker.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.webbee.daychecker.calendar_parser.CalendarParser;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class DateCheckerController {
    @GetMapping
    @RequestMapping(path = "/isHoliday")
    public boolean checkIsHoliday(
            @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate date) {
        var days = CalendarParser.getCalendarData();
        var day = days.stream()
                .filter(d -> d.getMonth().ordinal() == date.getMonth().ordinal() && date.getDayOfMonth() == d.getNumber())
                .findFirst()
                .orElseThrow();
        return day.isHoliday();
    }

    @GetMapping
    @RequestMapping(path = "/isNonWorkingTime")
    public boolean checkIsNonWorkingTime(
            @RequestParam("datetime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        if (checkIsHoliday(date)) {
            return true;
        }
        int hour = dateTime.getHour();
        return hour < 9 || hour > 18;
    }
}
