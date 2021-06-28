package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.TestingSystemConfiguration;
import ru.otus.homework.service.LocalizationService;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageSourceLocalizationService implements LocalizationService {

    private final MessageSource messageSource;
    private final TestingSystemConfiguration configuration;

    @Override
    public String getLocalizedMessage(String source) {
        return messageSource.getMessage(source, new Object[]{}, Locale.forLanguageTag(configuration.getLocale()));
    }

    @Override
    public String getLocalizedMessage(String source, Object[] parameters) {
        return messageSource.getMessage(source, parameters, Locale.forLanguageTag(configuration.getLocale()));
    }

}
