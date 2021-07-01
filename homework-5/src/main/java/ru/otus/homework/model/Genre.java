package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Genre {
    long id;
    String name;

    public Genre(long id) {
        this(id, null);
    }
}
