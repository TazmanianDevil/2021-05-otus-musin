package ru.otus.homework.domain;

import lombok.Value;

import java.util.Collection;

@Value
public class Question {
    String question;
    Collection<Answer> answers;
    boolean freeAnswer;
}
