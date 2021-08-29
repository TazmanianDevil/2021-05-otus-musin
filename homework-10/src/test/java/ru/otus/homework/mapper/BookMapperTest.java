package ru.otus.homework.mapper;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.model.Book;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookMapperTest {

    @Test
    public void shouldReturnEmptyListWhenBooksListIsNullOrEmpty() {
        assertThat(new BookMapper().toDto((List) null)).isEmpty();
        assertThat(new BookMapper().toDto(Collections.emptyList())).isEmpty();
    }

    @Test
    public void shouldMapList() {
        final Book book = new EasyRandom().nextObject(Book.class);
        final List<BookDto> dtoList = new BookMapper().toDto(Collections.singletonList(book));
        assertThat(dtoList).hasSize(1);
    }

    @Test
    public void shouldMapBook() {
        final Book book = new EasyRandom().nextObject(Book.class);
        final BookDto bookDto = new BookMapper().toDto(book);
        assertThat(bookDto).isNotNull();
        assertThat(bookDto.getId()).isEqualTo(book.getId());
        assertThat(bookDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookDto.getGenre()).isEqualTo(book.getGenre().getName());
        assertThat(bookDto.getAuthor()).isEqualTo(book.getAuthor().getFullName());
    }
}