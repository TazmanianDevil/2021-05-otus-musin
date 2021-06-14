package ru.otus.homework.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.exception.FileParseException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvFileQuestionsParserTest {

    @Test
    public void whenParseNullableResourceExceptionIsThrown() {
        assertThrows(FileParseException.class, () -> new CsvFileQuestionsParser(null, new QuestionParserImpl()).parseQuestions());
    }

    @Test
    public void whenParseEmptyFileThenEmptyQuestionsListIsReturned() {
        Collection<Question> questions = new CsvFileQuestionsParser(
                new DefaultResourceLoader().getResource("data/empty-file.csv"), new QuestionParserImpl())
                .parseQuestions();

        assertNotNull(questions);
        assertTrue(questions.isEmpty());
    }

    @Test
    public void whenTextWithoutSemicolonFreeAnswerQuestionIsReturned() {
        Collection<Question> questions = new CsvFileQuestionsParser(
                new DefaultResourceLoader().getResource("data/free-answer.csv"), new QuestionParserImpl())
                .parseQuestions();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(questions.size(), 1);
        assertTrue(questions instanceof List);
        Question question = ((List<Question>) questions).get(0);
        assertNotNull(question);
        assertTrue(question.isFreeAnswer());
    }

    @Test
    public void whenTextWithSemicolonQuestionWithAnswersIsReturned() {
        Collection<Question> questions = new CsvFileQuestionsParser(
                new DefaultResourceLoader().getResource("data/question-with-answers.csv"), new QuestionParserImpl())
                .parseQuestions();

        assertNotNull(questions);
        assertFalse(questions.isEmpty());
        assertEquals(questions.size(), 1);
        assertTrue(questions instanceof List);
        Question question = ((List<Question>) questions).get(0);
        assertNotNull(question);
        assertFalse(question.isFreeAnswer());
        assertNotNull(question.getAnswers());
        assertFalse(question.getAnswers().isEmpty());
        assertEquals(question.getAnswers().size(), 3);
    }

    @Test
    public void whenNoCorrectAnswerExceptionIsThrown() {
        assertThrows(FileParseException.class, () -> new CsvFileQuestionsParser(
                new DefaultResourceLoader().getResource("data/question-without-correct-answer.csv.csv"), new QuestionParserImpl())
                .parseQuestions());

    }
}