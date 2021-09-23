package ru.otus.homework.controller.rest;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.homework.dto.BookDto;
import ru.otus.homework.service.LibraryService;

import java.util.stream.Collectors;

import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        when(libraryService.getAllBooks())
                .thenReturn(new EasyRandom().objects(BookDto.class, 2).collect(Collectors.toList()));
    }

    @Test
    public void shouldRedirectToLoginPageWhenGetBooksWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @WithMockUser(
            username = "user",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @WithMockUser(
            username = "user",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksWithEditor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }

    @WithMockUser(
            username = "user",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksWithAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id").isNotEmpty());
    }
}