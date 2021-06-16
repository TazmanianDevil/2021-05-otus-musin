package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.otus.homework.config.TestingSystemConfiguration;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.TestingResult;
import ru.otus.homework.service.TestingEngine;
import ru.otus.homework.service.UserInputService;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TestingEngineImplTest {
    @Mock
    private TestingSystemConfiguration configuration;

    @Mock
    private UserInputService inputService;

    @InjectMocks
    private TestingEngineImpl engine;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void whenQuestionsIsEmptyThenZeroCorrectAnswers() {
        when(configuration.getAnswers()).thenReturn(mock(TestingSystemConfiguration.Answers.class));

        TestingResult result = engine.test(Collections.emptyList());

        assertThat(result).isNotNull();
        assertThat(result.getCorrectAnswers()).isEqualTo(0);
    }

    @Test
    public void whenConfigurationTotalQuestionsEqualsZeroThenZeroCorrectAnswers() {
        TestingSystemConfiguration.Answers answers = mock(TestingSystemConfiguration.Answers.class);
        when(answers.getTotal()).thenReturn(0);
        when(configuration.getAnswers()).thenReturn(answers);

        TestingResult result = engine.test(Collections.emptyList());

        assertThat(result).isNotNull();
        assertThat(result.getUserCorrectAnswers()).isEqualTo(0);
    }

    @Test
    public void whenWrongUserAnswerThenTestingIsNotSuccessful() {
        TestingSystemConfiguration.Answers answers = mock(TestingSystemConfiguration.Answers.class);
        when(answers.getTotal()).thenReturn(1);
        when(answers.getCorrect()).thenReturn(1);
        when(configuration.getAnswers()).thenReturn(answers);
        when(inputService.getUserAnswerForQuestion(any(Question.class)))
                .thenReturn("Wrong answer");

        Question question = new Question("Question", Collections.singletonList(new Answer("Correct answer", true)), false);
        TestingResult result = engine.test(Collections.singletonList(question));

        assertThat(result).isNotNull();
        assertThat(result.getUserCorrectAnswers()).isEqualTo(0);
        assertThat(result.isSuccessTesting()).isFalse();
    }

    @Test
    public void whenCorrectUserAnswerThenTestingIsSuccessful() {
        TestingSystemConfiguration.Answers answers = mock(TestingSystemConfiguration.Answers.class);
        when(answers.getTotal()).thenReturn(1);
        when(answers.getCorrect()).thenReturn(1);
        when(configuration.getAnswers()).thenReturn(answers);
        when(inputService.getUserAnswerForQuestion(any(Question.class)))
                .thenReturn("Correct answer");

        Question question = new Question("Question", Collections.singletonList(new Answer("Correct answer", true)), false);
        TestingResult result = engine.test(Collections.singletonList(question));

        assertThat(result).isNotNull();
        assertThat(result.getUserCorrectAnswers()).isEqualTo(1);
        assertThat(result.isSuccessTesting()).isTrue();
    }
}