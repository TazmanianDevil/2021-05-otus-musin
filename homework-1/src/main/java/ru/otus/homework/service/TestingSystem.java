package ru.otus.homework.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Answer;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.User;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TestingSystem {

    private final QuestionsFileParser fileParser;
    private final UserInputService inputService;
    @Value("${testing.answers.total.count}")
    private int totalAnswersCount;
    @Value("${testing.answers.correct.count}")
    private int correctAnswersCount;

    public void test() {
        User user = inputService.getUserData();
        Collection<Question> questions = fileParser.parseQuestions();
        int userCorrectAnswers = testUser(questions);
        showResults(user.getFullName(), userCorrectAnswers);
    }

    private void showResults(final String fullName, int userCorrectAnswersCount) {
        if (correctAnswersCount <= userCorrectAnswersCount) {
            System.out.println(fullName + ", You answered " + userCorrectAnswersCount + " of " + totalAnswersCount + ". Congratulations!");
        } else {
            System.out.println(fullName + ", You answered just " + userCorrectAnswersCount + " of " + totalAnswersCount + ". Try again!");
        }
    }

    private int testUser(Collection<Question> questions) {
        int userCorrectAnswers = 0;
        Iterator<Question> questionIterator = questions.iterator();
        for (int i = 0; i < totalAnswersCount && questionIterator.hasNext(); i++) {
            Question question = questionIterator.next();
            String userAnswer = inputService.getUserAnswerForQuestion(question);
            if (isCorrectAnswer(question, userAnswer)) {
                userCorrectAnswers++;
            }
        }
        return userCorrectAnswers;
    }
    private boolean isCorrectAnswer(Question question, String userAnswer) {
        Optional<Answer> correctAnswer = question.getAnswers().stream()
                .filter(answer -> StringUtils.equals(answer.getAnswer(), userAnswer))
                .findFirst();
        return correctAnswer.map(Answer::isCorrect).orElse(false);
    }
}
