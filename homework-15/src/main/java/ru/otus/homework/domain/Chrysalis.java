package ru.otus.homework.domain;

import lombok.Data;

@Data
public class Chrysalis {
    private String name;
    private boolean toxic;
    public Chrysalis(Caterpillar caterpillar) {
        this.name = caterpillar.getName();
        this.toxic = caterpillar.isToxic();
    }
}
