package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    String id;
    String name;

    public Genre(String id) {
        this.id = id;
    }
}