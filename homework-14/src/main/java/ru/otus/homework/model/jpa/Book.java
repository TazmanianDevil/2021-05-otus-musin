package ru.otus.homework.model.jpa;

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
    @ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "AUTHOR_ID")
    @Fetch(FetchMode.JOIN)
    private Author author;
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GENRE_ID")
    @Fetch(FetchMode.JOIN)
    private Genre genre;

    public Book(long id) {
        this.id = id;
    }

    public Book(String title, Author author, Genre genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }
}
