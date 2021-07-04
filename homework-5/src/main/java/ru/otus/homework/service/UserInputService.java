package ru.otus.homework.service;

import ru.otus.homework.model.Book;

public interface UserInputService {

    Book getBookForCreate();

    Book getBookForUpdate();
}
