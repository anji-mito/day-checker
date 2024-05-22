package ru.webbee.daychecker.calendar_parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.webbee.daychecker.date_сhecker.model.Day;
import ru.webbee.daychecker.date_сhecker.model.Month;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalendarParser {
    public static List<Day> getCalendarData() {
        Document doc;
        try {
            doc = Jsoup
                    .connect("https://www.consultant.ru/law/ref/calendar/proizvodstvennye/2024")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Select all table elements with class "cal"
        Elements tables = doc.select("table.cal");
        List<Day> days = new ArrayList<>();
        // Iterate over each table
        for (Element table : tables) {
            // Get the month name from the table header
            Month monthName = Month.valueOf(table.select("th.month").text());
            // Select all table rows in the tbody
            Elements rows = table.select("tbody tr");
            // Iterate over each row
            for (Element row : rows) {
                // Select all table data cells in the row
                Elements cells = row.select("td");
                for (Element cell : cells) {
                    // Get the day number and remove non-numeric characters
                    String dayNumber = cell.text().replaceAll("[^0-9]", "");
                    if (!dayNumber.isEmpty()) {
                        int dayNum = Integer.parseInt(dayNumber);
                        boolean isHoliday = cell.hasClass("weekend") || cell.hasClass("holiday weekend");
                        Day day = new Day(isHoliday, dayNum, monthName);
                        days.add(day);
                    }
                }
            }
        }
        return days;
    }
}
