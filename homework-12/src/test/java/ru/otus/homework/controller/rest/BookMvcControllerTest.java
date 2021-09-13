package ru.otus.homework.controller.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.otus.homework.controller.BookMvcController;
import ru.otus.homework.dto.SaveBookRequest;
import ru.otus.homework.service.LibraryService;

@WebMvcTest(BookMvcController.class)
class BookMvcControllerTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Test
    public void shouldRedirectToLoginPageWhenGetBooksPageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldReRedirectToLoginPageWhenGetCreateBookPageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/create"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldReRedirectToLoginPageWhenSaveBookWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/save"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldReRedirectToLoginPageWhenGetEditBookPageWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    public void shouldReRedirectToLoginPageWhenDeleteBookWithoutUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/delete"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @WithMockUser(
            username = "test",
            authorities = {"reader"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksPageWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"reader"}
    )
    @Test
    public void shouldReturnBooksWhenCreateBookWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"reader"}
    )

    @Test
    public void shouldReturnBooksWhenSaveBookWithUser() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "save")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void shouldReturnBooksWhenCancelSavingWithUser() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "cancel")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void shouldReturnSaveBookPageWhenGetEditBookPageWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit")
                .param("id", "ID"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test
    public void shouldReturnBooksPageWhenDeletedBookWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/delete")
                .param("id", "ID"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}