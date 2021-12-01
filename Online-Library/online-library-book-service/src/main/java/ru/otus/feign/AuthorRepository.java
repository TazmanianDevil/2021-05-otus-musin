package ru.otus.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.otus.model.Author;

@FeignClient(name = "online-library-author-service", configuration = AuthorFeignConfiguration.class)
@RequestMapping(value = "/api/authors")
public interface AuthorRepository {

    @GetMapping(value = "/{id}")
    Author getById(@PathVariable long id);

}
