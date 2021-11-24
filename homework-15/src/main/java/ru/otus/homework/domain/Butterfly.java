package ru.otus.homework.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Butterfly {
    private String name;
    private boolean toxic;

    public Butterfly(Chrysalis chrysalis) {
        this.name = chrysalis.getName();
        this.toxic = chrysalis.isToxic();
    }
}
