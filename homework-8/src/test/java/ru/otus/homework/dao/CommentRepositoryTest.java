package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.homework.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CommentRepositoryTest {

    private CommentRepository commentRepository;

    @Test
    public void shouldReturnAllComments() {
        final List<Comment> comments = commentRepository.findAllByBookId("1");
        assertThat(comments).hasSize(2);
    }

    @Test
    public void shouldReturnNoComments() {
        final List<Comment> comments = commentRepository.findAllByBookId("23978a239852");
        assertThat(comments).hasSize(0);
    }
}