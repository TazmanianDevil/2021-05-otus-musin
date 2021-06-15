package ru.otus.homework.service.impl;

import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.exception.QuestionParseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuestionParserImplTest {

    @Test
    public void whenQuestionStringIsNullThenExceptionIsThrown() {
        QuestionParseException exception = assertThrows(QuestionParseException.class,
                () -> new QuestionParserImpl().parse(null));
        assertThat(exception).hasMessage("Can't parse question");
    }

    @Test
    public void whenStringWithoutSemicolonThenQuestionWithFreeAnswerIsReturned() {
        Question question = new QuestionParserImpl().parse("Test");
        assertThat(question).isNotNull();
        assertThat(question.isFreeAnswer()).isTrue();
        assertThat(question.getQuestion()).isEqualTo("Test");
        assertThat(question.getAnswers()).isEmpty();
    }

    @Test
    public void whenThereIsNoCorrectAnswerThenExceptionIsThrown() {
        assertThatThrownBy(() -> new QuestionParserImpl().parse("question;answer;answer1;answer2"))
                .isInstanceOf(QuestionParseException.class)
                .hasMessage("There is now correct answer");
    }

    @Test
    public void whenCorrectQuestionStringThenCorrectQuestionIsReturned() {
        Question question = new QuestionParserImpl().parse("question;answer1;answer2;answer1;answer3");
        assertThat(question).isNotNull();
        assertThat(question.isFreeAnswer()).isFalse();
        assertThat(question.getQuestion()).isEqualTo("question");
        assertThat(question.getAnswers()).isNotEmpty().hasSize(3);
        assertThat(question.getAnswers()).extracting(Answer::getAnswer).contains("answer2", "answer1", "answer3");
        for (Answer answer : question.getAnswers()) {
            if (answer.getAnswer().equals("answer1")) {
                assertThat(answer.isCorrect()).isTrue();
            }
        }

    }
}