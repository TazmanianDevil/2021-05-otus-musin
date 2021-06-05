package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.exception.FileParseException;
import ru.otus.homework.service.QuestionParser;
import ru.otus.homework.service.QuestionsFileParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CsvFileQuestionsParser implements QuestionsFileParser {

    private final Resource resourceFile;
    private final QuestionParser questionParser;

    public Collection<Question> parseQuestions() {
        if (resourceFile == null) {
            throw new FileParseException("Can't parse empty file or file doesn't exist!");
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
            return reader
                    .lines()
                    .filter(StringUtils::isNotEmpty)
                    .map(questionParser::parseQuestion)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new FileParseException();
        }
    }
}
