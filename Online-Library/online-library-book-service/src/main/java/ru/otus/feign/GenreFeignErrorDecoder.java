package ru.otus.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import ru.otus.exception.GenreNotFoundException;
import ru.otus.exception.OtherGenreApiException;
import ru.otus.exception.ServiceIsNotAvailableException;

public class GenreFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is4xxClientError()) {
            throw new GenreNotFoundException();
        }
        if (responseStatus.is5xxServerError()) {
            throw new ServiceIsNotAvailableException("online-library-genre-service");
        }
        return new OtherGenreApiException("Unknown genre service api error!");
    }
}
