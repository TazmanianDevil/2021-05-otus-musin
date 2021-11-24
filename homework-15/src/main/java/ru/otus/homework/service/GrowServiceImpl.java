package ru.otus.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Caterpillar;
import ru.otus.homework.domain.Chrysalis;

@Service
@Slf4j
public class GrowServiceImpl implements GrowService {
    @Override
    public Chrysalis eatAndGrow(Caterpillar caterpillar) {
        log.info("Caterpillar {} has growth to chrysalis", caterpillar.getName());
        return new Chrysalis(caterpillar);
    }
}
