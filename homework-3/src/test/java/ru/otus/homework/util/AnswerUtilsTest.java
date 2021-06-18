package ru.otus.homework.util;

import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AnswerUtilsTest {

    @Test
    public void whenQuestionIsEmptyThenExceptionIsThrown() {
        assertThatThrownBy(() -> AnswerUtils.isCorrectAnswer(null, null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Checking question cannot be null!");
    }

    @Test
    public void whenUserAnswerIsEmptyThenFalseIsReturned() {
        assertThat(AnswerUtils.isCorrectAnswer(mock(Question.class), null)).isFalse();
        assertThat(AnswerUtils.isCorrectAnswer(mock(Question.class), "")).isFalse();
    }

    @Test
    public void whenUserAnswerIsWrongThenFalseIsReturned() {
        Question question = mock(Question.class);
        when(question.getAnswers()).thenReturn(Collections.singletonList(new Answer("Correct answer", true)));
        assertThat(AnswerUtils.isCorrectAnswer(question, "Wrong answer")).isFalse();
    }

    @Test
    public void whenUserAnswerIsCorrectThenTrueIsReturned() {
        Question question = mock(Question.class);
        when(question.getAnswers()).thenReturn(Collections.singletonList(new Answer("Correct answer", true)));
        assertThat(AnswerUtils.isCorrectAnswer(question, "Correct answer")).isTrue();

    }
}