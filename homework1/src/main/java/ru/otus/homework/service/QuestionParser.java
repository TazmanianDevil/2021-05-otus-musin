package ru.otus.homework.service;

import ru.otus.homework.domain.Question;

public interface QuestionParser {
    
    Question parseQuestion(String questionString);
}
