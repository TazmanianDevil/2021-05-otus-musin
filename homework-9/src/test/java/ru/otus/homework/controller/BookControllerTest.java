package ru.otus.homework.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.LibraryService;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
    private static final long BOOK_ID = 42;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LibraryService libraryService;

    @Test
    public void shouldReturnAllBooks() throws Exception {
        when(libraryService.getAllBooks())
                .thenReturn(Collections.singletonList(new BookDto(BOOK_ID, "Title", "Author", "Genre")));

        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(content().string(containsString("42")));
    }
}