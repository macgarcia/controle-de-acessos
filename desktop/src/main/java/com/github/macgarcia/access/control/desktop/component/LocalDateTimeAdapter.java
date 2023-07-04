package com.github.macgarcia.access.control.desktop.component;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author macgarcia
 */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime>{

    @Override
    public void write(JsonWriter writer, LocalDateTime t) throws IOException {
        if (t == null) {
            writer.nullValue();
        } else {
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            writer.value(dtf.format(t));
        }
    }

    @Override
    public LocalDateTime read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        } else {
            final String data = reader.nextString();
            return LocalDateTime.parse(data);
        }
    }
    
}
