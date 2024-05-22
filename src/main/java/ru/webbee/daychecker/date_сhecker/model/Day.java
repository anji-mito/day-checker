package ru.webbee.daychecker.date_сhecker.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Day {
    private boolean isHoliday;
    private int number;
    private Month month;
}
