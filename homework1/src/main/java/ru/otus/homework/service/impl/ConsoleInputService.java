package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.QuestionsPrinter;
import ru.otus.homework.service.UserInputService;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleInputService implements UserInputService {

    private final QuestionsPrinter questionsPrinter;
    private Scanner scanner = new Scanner(System.in);

    @Override
    public User getUserData() {
        String name = getText("name");
        String surname = getText("surname");
        return new User(name, surname);
    }

    @Override
    public String getUserAnswerForQuestion(Question question) {
        questionsPrinter.printQuestion(question);
        return getText("answer");
    }

    private String getText(String fieldName) {
        String text = null;
        while (StringUtils.isEmpty(text)) {
            System.out.print("Enter your " + fieldName + ": ");
            text = scanner.nextLine();
        }
        return text;
    }
}
