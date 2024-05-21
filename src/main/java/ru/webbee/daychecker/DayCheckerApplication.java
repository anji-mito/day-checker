package ru.webbee.daychecker;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.webbee.daychecker.dateсhecker.model.Day;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DayCheckerApplication {

	public static void main(String[] args) {
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

			// Create a list to store Day objects
			List<Day> days = new ArrayList<>();

			// Iterate over each table
			for (Element table : tables) {
				// Get the month name from the table header
				String monthName = table.select("th.month").text();

				// Select all table rows in the tbody
				Elements rows = table.select("tbody tr");

				// Iterate over each row
				for (Element row : rows) {
					// Select all table data cells in the row
					Elements cells = row.select("td");

					// Iterate over each cell
					// Iterate over each cell
					// Iterate over each cell
					for (Element cell : cells) {
						// Get the day number and remove non-numeric characters
						String dayNumber = cell.text().replaceAll("[^0-9]", "");

						// Check if the day number is not empty after removing non-numeric characters
						if (!dayNumber.isEmpty()) {
							// Parse the day number as an integer
							int dayNum = Integer.parseInt(dayNumber);

							// Check if it's a weekend (Saturday or Sunday)
							boolean isWeekend = cell.hasClass("weekend");

							// Create a Day object and add it to the list
							Day day = new Day(isWeekend, dayNum, monthName);
							days.add(day);
						}
					}
				}
			}

			// Print the parsed days
			for (Day day : days) {
				System.out.println(day);
			}
	}

}
