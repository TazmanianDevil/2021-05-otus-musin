package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.service.QuestionsFileParser;
import ru.otus.homework.service.QuestionsPrinter;

import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
@Service
public class ConsoleQuestionsPrinter implements QuestionsPrinter {

    private final QuestionsFileParser parser;

    @Override
    public void printQuestion() {
        Collection<Question> questions = parser.parseQuestions();
        questions.forEach(this::printQuestionWithAnswers);
    }

    private void printQuestionWithAnswers(Question question) {
        System.out.println(question.getQuestion());
        if (question.isFreeAnswer()) {
            System.out.println("Free answer:");
        } else {
            printAnswers(question.getAnswers());
        }
    }

    private void printAnswers(Collection<Answer> answers) {
        answers.forEach(answer -> System.out.println("\t" + answer.getAnswer()));
    }
}
