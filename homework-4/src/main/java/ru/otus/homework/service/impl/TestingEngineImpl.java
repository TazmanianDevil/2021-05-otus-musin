package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.homework.config.TestingSystemConfiguration;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.TestingResult;
import ru.otus.homework.service.TestingEngine;
import ru.otus.homework.service.UserInputService;
import ru.otus.homework.util.AnswerUtils;

import java.util.Collection;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class TestingEngineImpl implements TestingEngine {

    private final TestingSystemConfiguration configuration;
    private final UserInputService inputService;

    @Override
    public TestingResult test(Collection<Question> questions) {
        int userCorrectAnswers = 0;
        Iterator<Question> questionIterator = questions.iterator();
        for (int i = 0; i < configuration.getAnswers().getTotal() && questionIterator.hasNext(); i++) {
            Question question = questionIterator.next();
            String userAnswer = inputService.getUserAnswerForQuestion(question);
            if (AnswerUtils.isCorrectAnswer(question, userAnswer)) {
                userCorrectAnswers++;
            }
        }
        return new TestingResult(configuration.getAnswers().getTotal(), configuration.getAnswers().getCorrect(),
                userCorrectAnswers);
    }
}
