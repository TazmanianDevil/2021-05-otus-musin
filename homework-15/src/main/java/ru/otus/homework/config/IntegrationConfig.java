package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.homework.service.BirthService;
import ru.otus.homework.service.GrowService;
import ru.otus.homework.service.TransformService;

@Configuration
@EnableIntegration

public class IntegrationConfig {

    @Bean
    public QueueChannel birthChannel() {
        return MessageChannels.queue().get();
    }

    @Bean
    public PublishSubscribeChannel wildLifeChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow butterflyLifeFlow(GrowService growService, TransformService transformService) {
        return IntegrationFlows.from(birthChannel())
                .log()
                .split()
                .handle(growService, "eatAndGrow")
                .handle(transformService, "transform")
                .channel(wildLifeChannel())
                .get();
    }
}
