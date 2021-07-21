package ru.otus.homework.service;

import ru.otus.homework.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    Comment save(Comment comment);

    Optional<Comment> findById(String id);

    List<Comment> findAllByBookId(String bookId);

    void deleteById(String id);

    void deleteByBookId(String bookId);
}
