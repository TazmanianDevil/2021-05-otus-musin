package ru.otus.homework.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface CommentService {

    Comment getById(long id);

    void deleteById(long id);

    Comment create(Comment book);

    Comment update(Comment book);

}
