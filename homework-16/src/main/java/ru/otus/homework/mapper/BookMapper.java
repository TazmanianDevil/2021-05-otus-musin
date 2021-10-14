package ru.otus.homework.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.model.Book;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.collections4.ListUtils.emptyIfNull;

@Component
public class BookMapper {
    public BookDto toDto(Book book) {
        String authorName = book.getAuthor() != null ? book.getAuthor().getFullName() : "";
        String genreName = book.getGenre() != null ? book.getGenre().getName() : "";

        return new BookDto(book.getId(), book.getTitle(), authorName, genreName);
    }

    public List<BookDto> toDto(List<Book> books) {
        return emptyIfNull(books).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
