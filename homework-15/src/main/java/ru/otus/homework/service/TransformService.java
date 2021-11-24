package ru.otus.homework.service;

import ru.otus.homework.domain.Butterfly;
import ru.otus.homework.domain.Chrysalis;

public interface TransformService {
    Butterfly transform(Chrysalis chrysalis);
}
