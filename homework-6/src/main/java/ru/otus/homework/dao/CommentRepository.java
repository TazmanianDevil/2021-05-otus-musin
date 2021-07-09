package ru.otus.homework.dao;

import ru.otus.homework.model.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> getAllByBookId(long id);

    Comment getById(long id);

    Comment update(Comment comment);

    Comment create(Comment comment);

    void deleteById(long id);
}
