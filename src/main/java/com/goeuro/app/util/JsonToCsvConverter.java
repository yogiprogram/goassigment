package com.goeuro.app.util;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.goeuro.app.model.CSVRow;
import com.goeuro.app.model.Position;


import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;


@Component
public class JsonToCsvConverter implements Converter<Position,String> {

    org.slf4j.Logger log =  LoggerFactory.getLogger(getClass());

    ObjectWriter writer;

    @PostConstruct
    void createWriter() {
        CsvMapper mapper = new CsvMapper();
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.addMixIn(Position.class, CSVRow.class);
        writer = mapper.writerWithSchemaFor(com.goeuro.app.model.Position.class);
    }


    @Override
    public String convert(Position source) {
        try {
            return writer.writeValueAsString(source);
        } catch (IOException e) {
            return null;
        }
    }

}
