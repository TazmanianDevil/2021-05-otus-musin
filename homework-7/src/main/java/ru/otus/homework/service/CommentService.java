package ru.otus.homework.service;

import ru.otus.homework.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Optional<Comment> getById(long id);

    void deleteById(long id);

    List<Comment> getAllByBookId(long id);

    Comment create(Comment book);

    Comment update(Comment book);

}
