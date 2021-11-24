package ru.otus.homework.config;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.homework.domain.Butterfly;
import ru.otus.homework.domain.Caterpillar;

import java.util.Collection;

@MessagingGateway
public interface NatureLife {

    @Gateway(requestChannel = "birthChannel", replyChannel = "wildLifeChannel")
    Collection<Butterfly> process(Collection<Caterpillar> caterpillars);
}
