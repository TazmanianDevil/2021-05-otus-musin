package ru.otus.homework.dao;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.dao.CommentRepository;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void shouldGetAllCommentsByBookId() {
        final List<Comment> books = commentRepository.findByBook_Id(1L);

        assertThat(books).isNotEmpty().hasSize(1);
    }

    @Test
    public void shouldThrowExceptionWhenNoCommentWithCurrentId() {
        final Optional<Comment> comment = commentRepository.findById(Long.MAX_VALUE);

        assertThat(comment.isEmpty()).isTrue();
    }

    @Test
    public void shouldUpdateComment() {
        final Comment comment = em.find(Comment.class, 1L);
        em.detach(comment);
        comment.setText("ANOTHER_COMMENT");

        commentRepository.save(comment);

        final Comment updatedComment = em.find(Comment.class, 1L);

        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getText()).isEqualTo("ANOTHER_COMMENT");
    }

    @Test
    public void shouldCreateComment() {
        final Book book = em.find(Book.class, 1L);

        final Comment createdComment = commentRepository.save(
                new Comment(0, "CREATED_COMMENT_TEXT", book));

        assertThat(createdComment.getId()).isGreaterThan(0);
        final Comment actualComment = em.find(Comment.class, createdComment.getId());
        assertThat(actualComment).isNotNull()
                .matches(b -> b.getBook().getId() > 0)
                .matches(b -> StringUtils.isNotBlank(b.getText()));
    }

    @Test
    public void shouldDeleteById() {
        commentRepository.deleteById(1L);

        final Comment comment = em.find(Comment.class, 1L);
        assertThat(comment).isNull();
    }

    @Test
    public void shouldReturnById() {
        final Comment expectedComment = em.find(Comment.class, 1L);

        final Comment actualComment = commentRepository.getById(1L);

        assertThat(actualComment)
                .usingRecursiveComparison()
                .isEqualTo(expectedComment);
    }
}