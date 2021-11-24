package ru.otus.homework.service;

import ru.otus.homework.domain.Caterpillar;
import ru.otus.homework.domain.Chrysalis;

public interface GrowService {

    Chrysalis eatAndGrow(Caterpillar caterpillar);
}
