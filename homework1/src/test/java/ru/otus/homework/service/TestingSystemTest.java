package ru.otus.homework.service;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.User;

import javax.swing.plaf.FontUIResource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TestingSystemTest {

    @Mock
    private UserInputService inputService;

    @Mock
    private QuestionsFileParser fileParser;

    @InjectMocks
    private TestingSystem testingSystem;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void whenTestIsCalledThenAllStepsAreInvoked() throws IllegalAccessException {
        int totalAnswerCount = 3;
        FieldUtils.writeDeclaredField(testingSystem, "totalAnswersCount", totalAnswerCount, true);
        FieldUtils.writeDeclaredField(testingSystem, "correctAnswersCount", 2, true);
        when(inputService.getUserData()).thenReturn(new User("name", "surname"));
        when(fileParser.parseQuestions()).thenReturn(createQuestions(totalAnswerCount));
        when(inputService.getUserAnswerForQuestion(any(Question.class))).thenReturn("answer");
        testingSystem.test();
        verify(inputService, times(1)).getUserData();
        verify(fileParser, times(1)).parseQuestions();
        verify(inputService, times(totalAnswerCount)).getUserAnswerForQuestion(any(Question.class));

    }

    private Collection<Question> createQuestions(int totalAnswerCount) {
        Collection<Question> questions = new ArrayList<>();
        for (int i = 0; i < totalAnswerCount; i++) {
            questions.add(new Question("question" + i, Collections.emptyList(), true));
        }
        return questions;
    }
}