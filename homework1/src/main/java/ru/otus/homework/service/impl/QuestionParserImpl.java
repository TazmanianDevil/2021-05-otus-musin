package ru.otus.homework.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.exception.FileParseException;
import ru.otus.homework.service.QuestionParser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class QuestionParserImpl implements QuestionParser {
    @Override
    public Question parseQuestion(String questionString) {
        final String[] strings = questionString.split(";");
        final String question = strings[0];
        final boolean freeAnswer = strings.length == 1;

        final Collection<Answer> answers = parseAnswers(strings);

        return new Question(question, answers, freeAnswer);
    }

    private Collection<Answer> parseAnswers(String[] strings) {
        if (strings == null || strings.length <= 1) {
            return Collections.emptyList();
        }
        final String correctAnswer = strings[1];
        boolean booleanAnswer = false;

        final Collection<Answer> answers = new ArrayList<>();
        for (int i = 2; i < strings.length; i++) {
            boolean isCorrectAnswer = StringUtils.equals(strings[i], correctAnswer);
            booleanAnswer |= isCorrectAnswer;
            answers.add(new Answer(strings[i], isCorrectAnswer));
        }
        if (!booleanAnswer) {
            throw new FileParseException("There is now correct answer");
        }
        return answers;
    }
}
