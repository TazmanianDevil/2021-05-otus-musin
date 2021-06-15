package ru.otus.homework.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.TestEngine;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.TestingResult;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.impl.TestingSystemImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class TestingSystemTest {

    @Mock
    private UserInputService inputService;

    @Mock
    private QuestionsFileParser fileParser;

    @Mock
    TestingEngine testingEngine;

    @Mock
    private OutputService outputService;

    @InjectMocks
    private TestingSystemImpl testingSystem;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void whenTestIsCalledThenAllStepsAreInvoked() throws IllegalAccessException {
        User user = new User("name", "surname");
        when(inputService.getUserData()).thenReturn(user);
        when(fileParser.parseQuestions()).thenReturn(createQuestions(2));
        TestingResult testingResult = new TestingResult(2, 1, 1);
        when(testingEngine.test(any(Collection.class))).thenReturn(testingResult);
        testingSystem.test();
        verify(inputService, times(1)).getUserData();
        verify(fileParser, times(1)).parseQuestions();
        verify(testingEngine, times(1)).test(anyCollection());
        verify(outputService, times(1)).printResults(eq(user.getFullName()), eq(testingResult));
    }

    private Collection<Question> createQuestions(int totalAnswerCount) {
        Collection<Question> questions = new ArrayList<>();
        for (int i = 0; i < totalAnswerCount; i++) {
            questions.add(new Question("question" + i, Collections.emptyList(), true));
        }
        return questions;
    }
}