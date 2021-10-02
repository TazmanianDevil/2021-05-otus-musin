package ru.otus.homework.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "authors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    String id;
    String fullName;

    public Author(String id) {
        this.id = id;
    }
}