package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.CommentRepository;
import ru.otus.homework.model.Comment;
import ru.otus.homework.service.CommentService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    @Override
    public Optional<Comment> getById(long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Comment> getAllByBookId(long id) {
        return commentRepository.findByBook_Id(id);
    }

    @Transactional
    @Override
    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment update(Comment comment) {
        return commentRepository.save(comment);
    }
}
