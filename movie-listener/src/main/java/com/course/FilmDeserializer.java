package com.course;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;

public class FilmDeserializer extends ObjectMapperDeserializer<Film> {
    public FilmDeserializer() {
        super(Film.class);
    }
}
