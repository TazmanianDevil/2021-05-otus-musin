package ru.otus.homework.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ModelUtil {

    @SneakyThrows
    public <T, E extends RuntimeException> void checkEntity(Optional<T> optional, Class<E> exceptionClass) {
        if (optional.isEmpty()) {
            throw exceptionClass.getDeclaredConstructor().newInstance();
        }
    }
}
