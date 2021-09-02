package ru.otus.homework.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.homework.dto.GenreDto;
import ru.otus.homework.service.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final LibraryService libraryService;

    @GetMapping("/api/genres")
    public List<GenreDto> getAllGenres() {
        return libraryService.getAllGenres();
    }
}
