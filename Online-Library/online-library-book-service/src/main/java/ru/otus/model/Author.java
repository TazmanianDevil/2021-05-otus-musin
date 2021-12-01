package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    long id;
    String fullName;

    public Author(long id) {
        this(id, null);
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }
}
