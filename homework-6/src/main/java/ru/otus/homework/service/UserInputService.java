package ru.otus.homework.service;

import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

public interface UserInputService {

    Book getBookForCreate();

    Book getBookForUpdate();

    Comment getCommentForCreate();

    Comment getCommentForUpdate();
}
