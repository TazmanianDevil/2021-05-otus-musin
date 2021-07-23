package ru.otus.homework.service;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.SaveCommentRequest;
import ru.otus.homework.model.UpdateCommentRequest;

public interface UserInputService {
    Book getBookForCreate();

    Book getBookForUpdate();

    SaveCommentRequest getCommentForCreate();

    UpdateCommentRequest getCommentForUpdate();
}
