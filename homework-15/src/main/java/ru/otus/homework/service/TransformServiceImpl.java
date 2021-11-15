package ru.otus.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Butterfly;
import ru.otus.homework.domain.Chrysalis;

@Service
@Slf4j
public class TransformServiceImpl implements TransformService {
    @Override
    public Butterfly transform(Chrysalis chrysalis) {
        log.info("New butterfly {} has been borned", chrysalis.getName());
        return new Butterfly(chrysalis);
    }
}
