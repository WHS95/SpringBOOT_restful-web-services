package com.Alex.rest.webservices.restfulwebservices.mongo;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

@Component
@WritingConverter
public class ZonedDateTimeWriteConverter implements Converter<ZonedDateTime, Date> {

    @Override
    public Date convert(ZonedDateTime zonedDateTime) {
        System.out.println("Writing Converter called");
        return Date.from(zonedDateTime.toInstant());
    }

}