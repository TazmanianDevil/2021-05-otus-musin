package ru.otus.homework.model;

import lombok.Value;

@Value
public class Author {
    long id;
    String fullName;

    public Author(long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Author(long id) {
        this(id, null);
    }
}
