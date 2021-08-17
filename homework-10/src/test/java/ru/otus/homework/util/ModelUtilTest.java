package ru.otus.homework.util;

import org.junit.jupiter.api.Test;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.exception.WrongBookException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ModelUtilTest {

    @Test
    public void shouldThrowExceptionWhenEntityOptionalIsEmpty() {
        assertThatThrownBy(() -> ModelUtil.checkEntity(Optional.empty(), WrongBookException.class))
                .isInstanceOf(WrongBookException.class)
                .hasMessage("Book must exist in library");
    }

    @Test
    public void shouldNotThrowExceptionWhenOptionalIsNotEmpty() {
        ModelUtil.checkEntity(Optional.of(new Book()), WrongBookException.class);
    }
}