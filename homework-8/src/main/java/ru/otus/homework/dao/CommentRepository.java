package ru.otus.homework.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.homework.model.Comment;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findAllByBookId(String bookId);

    void deleteAllByBookId(String bookId);
}
