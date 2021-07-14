package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(JpaCommentRepository.class)
class JpaCommentRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private JpaCommentRepository commentRepository;

    @Test
    public void shouldReturnNullWhenNoCommentWithCurrentId() {
        final Comment comment = commentRepository.getById(Integer.MAX_VALUE);

        assertThat(comment).isNull();
    }

    @Test
    public void shouldUpdateComment() {
        final Comment comment = em.find(Comment.class, 1L);
        em.detach(comment);
        comment.setText("ANOTHER_COMMENT");

        commentRepository.update(comment);

        final Comment updatedComment = em.find(Comment.class, 1L);

        assertThat(updatedComment).isNotNull();
        assertThat(updatedComment.getText()).isEqualTo("ANOTHER_COMMENT");
    }

    @Test
    public void shouldCreateComment() {
        final Book book = em.find(Book.class, 1L);

        final Comment createdComment = commentRepository.create(
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