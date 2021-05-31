package ru.otus.homework.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.exception.FileParseException;
import ru.otus.homework.service.QuestionsFileParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
public class CsvFileQuestionsParser implements QuestionsFileParser {

    private Resource resourceFile;

    public Collection<Question> parseQuestions() {
        if (resourceFile == null) {
            throw new FileParseException("Can't parse empty file or file doesn't exist!");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
            return reader
                    .lines()
                    .filter(StringUtils::isNotEmpty)
                    .map(this::parseQuestion)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileParseException();
        }
    }

    private Question parseQuestion(String questionString) {
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
