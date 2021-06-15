package ru.otus.homework.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TestingResultTest {

    @Test
    public void whenUserAnswersGreaterThenSuccessAnswersThenTestingIsSuccessful() {
        assertThat(new TestingResult(10, 5, 6).isSuccessTesting()).isTrue();
    }

    @Test
    public void whenUserAnswersEqualsToSuccessAnswersThenTestingIsSuccessful() {
        assertThat(new TestingResult(10, 5, 5).isSuccessTesting()).isTrue();
    }

    @Test
    public void whenUserAnswersLessThanSuccessAnswersThenTestingIsSuccessful() {
        assertThat(new TestingResult(10, 5, 3).isSuccessTesting()).isFalse();
    }

}