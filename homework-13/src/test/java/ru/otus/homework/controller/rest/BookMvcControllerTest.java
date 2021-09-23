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
            authorities = {"READER"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksPageWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksPageWithEditor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksWhenGetBooksPageWithAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturn403WhenCreateBookWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/create"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnBooksWhenCreateBookWithEditor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksWhenCreateBookWithAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/create"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturn403WhenSaveBookWithUser() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "save")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }
    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnBooksWhenSaveBookWithEditor() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "save")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksWhenSaveBookWithAdmin() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "save")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @WithMockUser(
            username = "test",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturn403sWhenCancelSavingWithUser() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "cancel")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnBooksWhenCancelSavingWithEditor() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "cancel")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksWhenCancelSavingWithAdmin() throws Exception {
        SaveBookRequest request = new SaveBookRequest(13, "TITLE", "AUTHOR", 42);
        mockMvc.perform(MockMvcRequestBuilders.post("/books/save")
                .param("action", "cancel")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @WithMockUser(
            username = "test",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturn403WhenGetEditBookPageWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit")
                .param("id", "ID"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturnEditBookPageWhenGetEditBookPageWithEditor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit")
                .queryParam("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnEditBookPageWhenGetEditBookPageWithAdmin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/edit")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithMockUser(
            username = "test",
            authorities = {"READER"}
    )
    @Test
    public void shouldReturn403WhenDeletedBookWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/delete")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(
            username = "test",
            authorities = {"EDITOR"}
    )
    @Test
    public void shouldReturn403WhenDeletedBookWithEditor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/delete")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @WithMockUser(
            username = "test",
            authorities = {"ADMIN"}
    )
    @Test
    public void shouldReturnBooksPageWhenDeletedBookWithUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/books/delete")
                .param("id", "1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
}