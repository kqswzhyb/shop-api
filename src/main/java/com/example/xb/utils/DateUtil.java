package com.example.xb.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class DateUtil {

    public Date localToDate(LocalDateTime time) {
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime localDateTime = time.plusHours(8);
        ZonedDateTime zdt = localDateTime.atZone(zoneId);

        Date date = Date.from(zdt.toInstant());
        return date;
    }
}
