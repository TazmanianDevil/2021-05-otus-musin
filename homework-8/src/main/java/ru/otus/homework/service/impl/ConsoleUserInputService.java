package ru.otus.homework.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.*;
import ru.otus.homework.service.UserInputService;

import java.util.Scanner;

@Service
public class ConsoleUserInputService implements UserInputService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Book getBookForCreate() {
        String title = getText("Enter book's title, please: ");
        String authorId = getText("Enter author's id, please:  ");
        String genreId = getText("Enter genre's id, please: ");
        return new Book(null, title, new Author(authorId), new Genre(genreId));
    }

    @Override
    public Book getBookForUpdate() {
        String bookId = getText("Enter book's id, please:  ");
        String name = getText("Enter book's name, please: ");
        String authorId = getText("Enter author's id, please:  ");
        String genreId = getText("Enter genre's id, please: ");
        return new Book(bookId, name, new Author(authorId), new Genre(genreId));
    }

    @Override
    public SaveCommentRequest getCommentForCreate() {
        String bookId = getText("Enter book's id, please: ");
        String text = getText("Enter your comment, please: ");
        return new SaveCommentRequest(text, bookId);
    }

    @Override
    public UpdateCommentRequest getCommentForUpdate() {
        String bookId = getText("Enter book's id, please: ");
        String oldText = getText("Enter comment's old text, please:");
        String newText = getText("Enter comment's new text, please: ");
        return new UpdateCommentRequest(bookId, oldText, newText);
    }

    private String getText(String message) {
        String text = null;
        while (StringUtils.isEmpty(text)) {
            System.out.print(message);
            text = scanner.next();
        }
        return text;
    }

}
