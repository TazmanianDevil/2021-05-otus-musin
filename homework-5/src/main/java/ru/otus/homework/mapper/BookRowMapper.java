package ru.otus.homework.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.otus.homework.dao.impl.JdbcBookRepository.*;

@Service
public class BookRowMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        long authorId = resultSet.getLong(AUTHOR_ID);
        String fullName = resultSet.getString(FULL_NAME);

        long genreId = resultSet.getLong(GENRE_ID);
        String genreName = resultSet.getString(GENRE_NAME);

        return new Book(resultSet.getLong(ID),
                resultSet.getString(TITLE),
                new Author(authorId, fullName),
                new Genre(genreId, genreName));
    }
}
