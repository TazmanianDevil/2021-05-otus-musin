package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookRepository;
import ru.otus.homework.dao.impl.JpaCommentRepository;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final JpaCommentRepository commentRepository;

    @Override
    public Comment getById(long id) {
        return commentRepository.getById(id);
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<Comment> getAllByBookId(long id) {
        return commentRepository.getAllByBookId(id);
    }

    @Override
    public Comment create(Comment comment) {
        return commentRepository.create(comment);
    }

    @Override
    public Comment update(Comment comment) {
        return commentRepository.update(comment);
    }
}
