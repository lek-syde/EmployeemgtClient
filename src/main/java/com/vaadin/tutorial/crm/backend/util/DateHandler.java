package com.vaadin.tutorial.crm.backend.util;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateHandler extends StdDeserializer<Date> {


    /**
     *
     */
    private static final long serialVersionUID = 1270030655853959787L;

    public DateHandler(Class<?> clazz) {
        super(clazz);



    }

    public DateHandler() {
        this(null);



    }

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // TODO Auto-generated method stub

        String date= p.getText();

        try {
            SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
            return sdf.parse(date);
        } catch (Exception e) {
            return null;
        }

    }


}

