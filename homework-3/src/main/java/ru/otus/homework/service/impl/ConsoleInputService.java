package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.Question;
import ru.otus.homework.domain.User;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.QuestionsPrinter;
import ru.otus.homework.service.UserInputService;

import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class ConsoleInputService implements UserInputService {

    private final Scanner scanner = new Scanner(System.in);

    private final QuestionsPrinter questionsPrinter;
    private final LocalizationService localizationService;

    @Override
    public User getUserData() {
        String name = getText(localizationService.getLocalizedMessage("input.name"));
        String surname = getText(localizationService.getLocalizedMessage("input.surname"));
        return new User(name, surname);
    }

    @Override
    public String getUserAnswerForQuestion(Question question) {
        questionsPrinter.printQuestion(question);
        return getText(localizationService.getLocalizedMessage("input.answer"));
    }

    private String getText(String message) {
        String text = null;
        while (StringUtils.isEmpty(text)) {
            System.out.print(message);
            text = scanner.nextLine();
        }
        return text;
    }
}
