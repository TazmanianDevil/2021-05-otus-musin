package ru.otus.homework.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;

import java.util.Optional;

@UtilityClass
public class AnswerUtils {

    public boolean isCorrectAnswer(Question question, String userAnswer) {
        if (question == null) {
            throw new IllegalArgumentException("Checking question cannot be null!");
        }
        if (StringUtils.isEmpty(userAnswer)) {
            return false;
        }
        Optional<Answer> correctAnswer = question.getAnswers().stream()
                .filter(answer -> StringUtils.contains(answer.getAnswer(), userAnswer))
                .findFirst();
        return correctAnswer.map(Answer::isCorrect).orElse(false);
    }
}
