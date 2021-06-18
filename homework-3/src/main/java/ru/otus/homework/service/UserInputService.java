package ru.otus.homework.service;

import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.User;

public interface UserInputService {

    User getUserData();

    String getUserAnswerForQuestion(Question question);
}
