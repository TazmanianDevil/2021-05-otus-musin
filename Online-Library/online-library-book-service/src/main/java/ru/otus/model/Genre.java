package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    long id;
    String name;

    public Genre(long id) {
        this(id, null);
    }

    public Genre(String name) {
        this.name = name;
    }
}
