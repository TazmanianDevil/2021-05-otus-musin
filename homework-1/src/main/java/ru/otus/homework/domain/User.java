package ru.otus.homework.domain;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class User {
    String name;
    String surname;

    public String getFullName() {
        return surname + " " + name;
    }
}
