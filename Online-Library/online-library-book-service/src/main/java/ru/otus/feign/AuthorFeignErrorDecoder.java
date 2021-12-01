package ru.otus.feign;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import ru.otus.exception.AuthorNotFoundException;
import ru.otus.exception.OtherAuthorApiException;
import ru.otus.exception.ServiceIsNotAvailableException;

public class AuthorFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is4xxClientError()) {
            throw new AuthorNotFoundException();
        }
        if (responseStatus.is5xxServerError()) {
            throw new ServiceIsNotAvailableException("online-author-service");
        }
        return new OtherAuthorApiException("Unknown author service api exception!");
    }
}
