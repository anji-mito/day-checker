package ru.webbee.daychecker.dateсhecker.controller;

import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.jsoup.*;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDate;

@RestController
@RequestMapping(path = "/isWorkday")
public class DateCheckerController {
    public boolean checkIsWorkday(@RequestParam("date") LocalDate date) throws IOException {
        Document doc = Jsoup
                .connect("https://www.consultant.ru/law/ref/calendar/proizvodstvennye/2024")
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .get();
        Elements quotes = doc.getElementsByClass("quote");
        return true;
    }

    public boolean checkIsWorkTune(@RequestParam("date") LocalDate date) {
        return true;
    }
}
