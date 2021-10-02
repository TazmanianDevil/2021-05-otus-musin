package ru.otus.homework.model.jpa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "AUTHORS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String fullName;

    public Author(long id) {
        this(id, null);
    }

    public Author(String fullName) {
        this.fullName = fullName;
    }
}
