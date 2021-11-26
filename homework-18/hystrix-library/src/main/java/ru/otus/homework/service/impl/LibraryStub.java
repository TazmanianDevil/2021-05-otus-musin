package ru.otus.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework.dto.BookDto;

import java.util.Collections;
import java.util.List;

@Service
public class LibraryStub {

    public List<BookDto> defaultBooks() {
        return Collections.singletonList(defaultBook());
    }

    public BookDto defaultBook() {
        return new BookDto(0, "Mock book", "Mock author", "Mock genre");
    }

}
