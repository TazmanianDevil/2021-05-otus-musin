package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long authorId;
    private long genreId;

    public Book(long id) {
        this.id = id;
    }

    public Book(String title, long authorId, long genreId) {
        this.title = title;
        this.authorId = authorId;
        this.genreId = genreId;
    }

    public Book(long id, String title) {
        this.id = id;
        this.title = title;
    }
}
