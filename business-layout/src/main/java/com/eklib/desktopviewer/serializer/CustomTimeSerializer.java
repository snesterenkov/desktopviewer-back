package com.eklib.desktopviewer.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alex on 3/19/2015.
 */
public class CustomTimeSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws
            IOException {

        SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
        String formattedTime = formatter.format(value);

        gen.writeString(formattedTime);

    }
}
