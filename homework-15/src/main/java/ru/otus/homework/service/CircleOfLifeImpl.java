package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.NatureLife;
import ru.otus.homework.domain.Butterfly;
import ru.otus.homework.domain.Caterpillar;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
public class CircleOfLifeImpl implements CircleOfLife {
    private static final int ORDERS_DELAY_MILLS = 10000;
    private final NatureLife natureLife;
    private final BirthService birthService;

    @Override
    @SneakyThrows
    public void startButterflyLifeCycle() {
        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            Thread.sleep(ORDERS_DELAY_MILLS);
            pool.execute(() -> {
                Collection<Caterpillar> caterpillars = generateCaterpillars();
//                System.out.println("New orderItems: " +
//                        caterpillars.stream().map(OrderItem::getItemName)
//                                .collect(Collectors.joining(",")));
                Collection<Butterfly> butterflies = natureLife.process(caterpillars);
//                System.out.println("Ready butterflies: " + butterflies.stream()
//                        .map(Food::getName)
//                        .collect(Collectors.joining(",")));
            });
        }
    }

    private List<Caterpillar> generateCaterpillars() {
        return birthService.birth();
    }
}
