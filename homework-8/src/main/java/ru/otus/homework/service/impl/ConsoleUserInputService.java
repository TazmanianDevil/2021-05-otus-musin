package ru.otus.homework.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.Genre;
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
    public Comment getCommentForCreate() {
        String bookId = getText("Enter book's id, please: ");
        String text = getText("Enter your comment, please: ");
        return new Comment(null, text, bookId);
    }

    @Override
    public Comment getCommentForUpdate() {
        String commentId = getText("Enter comment's id, please:");
        String text = getText("Enter comment's text, please: ");
        String bookId = getText("Enter book's id, please: ");
        return new Comment(commentId, text, bookId);
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
