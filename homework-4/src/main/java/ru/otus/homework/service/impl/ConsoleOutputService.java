package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.domain.TestingResult;
import ru.otus.homework.service.LocalizationService;
import ru.otus.homework.service.OutputService;

@Service
@RequiredArgsConstructor
public class ConsoleOutputService implements OutputService {

    private final LocalizationService localizationService;

    @Override
    public void printResults(String fullName, TestingResult result) {
        if (result.isSuccessTesting()) {
            System.out.println(localizationService.getLocalizedMessage("output.result.success",
                    new Object[]{fullName, result.getUserCorrectAnswers(), result.getTotalQuestions()}));
        } else {
            System.out.println(localizationService.getLocalizedMessage("output.result.failure",
                    new Object[]{fullName, result.getUserCorrectAnswers(), result.getTotalQuestions()}));
        }
    }
}
