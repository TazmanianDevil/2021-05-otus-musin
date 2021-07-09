package ru.otus.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface CommentService {

    @Transactional
    Comment getById(long id);

    @Transactional
    void deleteById(long id);

    @Transactional
    List<Comment> getAllByBookId(long id);

    @Transactional
    Comment create(Comment book);

    @Transactional
    Comment update(Comment book);

}
