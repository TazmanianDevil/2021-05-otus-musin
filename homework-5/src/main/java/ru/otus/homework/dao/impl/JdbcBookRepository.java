package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookRepository;
import ru.otus.homework.exception.NoBookFoundException;
import ru.otus.homework.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class JdbcBookRepository implements BookRepository {
    public static final String ID = "ID";
    public static final String AUTHOR_ID = "AUTHOR_ID";
    public static final String FULL_NAME = "FULL_NAME";
    public static final String GENRE_ID = "GENRE_ID";
    public static final String GENRE_NAME = "GENRE_NAME";
    public static final String TITLE = "TITLE";

    private final NamedParameterJdbcOperations jdbc;
    private final RowMapper<Book> bookRowMapper;

    @Override
    public List<Book> getAll() {
        return jdbc.query("SELECT B.ID, B.TITLE, B.AUTHOR_ID, A.FULL_NAME, B.GENRE_ID, G.NAME GENRE_NAME FROM BOOKS B \n" +
                "INNER JOIN AUTHORS A ON B.AUTHOR_ID = A.ID\n" +
                "INNER JOIN GENRES G ON B.GENRE_ID = G.ID", bookRowMapper);
    }

    @Override
    public long insert(Book book) {
        Map<String, Object> params = getInsertBookParamsMap(book);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update("INSERT INTO BOOKS (TITLE, AUTHOR_ID, GENRE_ID) VALUES " +
                "(:TITLE, :AUTHOR_ID, :GENRE_ID)", new MapSqlParameterSource(params), keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public boolean update(Book book) {
        Map<String, Object> params = getInsertBookParamsMap(book);

        int updated = jdbc.update("UPDATE BOOKS SET AUTHOR_ID = :AUTHOR_ID, GENRE_ID = :GENRE_ID, TITLE=:TITLE WHERE ID = :ID", params);
        return updated > 0;
    }

    private Map<String, Object> getInsertBookParamsMap(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put(ID, book.getId());
        params.put(TITLE, book.getTitle());
        params.put(AUTHOR_ID, book.getAuthor().getId());
        params.put(GENRE_ID, book.getGenre().getId());
        return params;
    }

    @Override
    public Book getById(long id) {
        Map<String, Long> params = getIdParams(id);
        try {
            return jdbc.queryForObject("SELECT B.ID, B.TITLE, B.AUTHOR_ID, A.FULL_NAME, B.GENRE_ID, G.NAME GENRE_NAME FROM BOOKS B \n" +
                            "INNER JOIN AUTHORS A ON B.AUTHOR_ID = A.ID\n" +
                            "INNER JOIN GENRES G ON B.GENRE_ID = G.ID\n" +
                            "WHERE B.ID = :ID",
                    params, bookRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new NoBookFoundException(id, e);
        }
    }

    @Override
    public boolean deleteById(long id) {
        Map<String, Long> params = getIdParams(id);
        int deleted = jdbc.update("DELETE FROM BOOKS WHERE ID = :ID", params);
        return deleted > 0;
    }

    private Map<String, Long> getIdParams(long id) {
        return Map.of(
                ID, id
        );
    }
}
