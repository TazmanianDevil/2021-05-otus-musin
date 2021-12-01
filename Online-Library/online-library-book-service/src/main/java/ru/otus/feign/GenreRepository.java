package ru.otus.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.model.Genre;

@FeignClient(name = "online-library-genre-service", configuration = GenreFeignConfiguration.class)
@RequestMapping(value = "/api/genres")
public interface GenreRepository {
    @GetMapping(value = "/{id}")
    Genre getById(@PathVariable long id);
}
