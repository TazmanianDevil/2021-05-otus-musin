package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.TestingResult;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.*;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TestingSystemImpl implements TestingSystem {

    private final QuestionsFileParser fileParser;
    private final UserInputService inputService;
    private final TestingEngine testingEngine;
    private final OutputService outputService;

    public void test() {
        User user = inputService.getUserData();
        Collection<Question> questions = fileParser.parseQuestions();
        TestingResult testingResult = testingEngine.test(questions);
        outputService.printResults(user.getFullName(), testingResult);
    }
}
