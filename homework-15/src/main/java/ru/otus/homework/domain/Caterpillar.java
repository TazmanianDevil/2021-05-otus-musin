package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Caterpillar {
    private String name;
    private boolean toxic;
}
