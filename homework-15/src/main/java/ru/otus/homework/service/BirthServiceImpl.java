package ru.otus.homework.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Butterfly;
import ru.otus.homework.domain.Caterpillar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class BirthServiceImpl implements BirthService {
    private static final String[] BUTTERFLY_NAMES = {"hives", "cardinal", "swallowtail", "swallowtail", "hawk maker"};

    @Override
    public List<Caterpillar> birth() {
        List<Caterpillar> caterpillars = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 10); i++) {
            caterpillars.add(new Caterpillar(
                    getCaterpillarName(i), RandomUtils.nextBoolean()));
        }
        log.info("Caterpillars were born: {}", caterpillars);
        return caterpillars;
    }

    private String getCaterpillarName(int i) {
        return (BUTTERFLY_NAMES[RandomUtils.nextInt(1, BUTTERFLY_NAMES.length)]);
    }

}
