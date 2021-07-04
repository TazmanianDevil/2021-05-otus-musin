package ru.otus.homework.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.service.UserInputService;

import java.util.Scanner;

@Service
public class ConsoleUserInputService implements UserInputService {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public Book getBookForCreate() {
        String name = getText("Enter book's name, please: ");
        long authorId = getLongValue("Enter author's id, please:  ");
        long genreId = getLongValue("Enter genre's id, please: ");
        return new Book(0, name, new Author(authorId), new Genre(genreId));
    }

    @Override
    public Book getBookForUpdate() {
        long bookId = getLongValue("Enter book's id, please:  ");
        String name = getText("Enter book's name, please: ");
        long authorId = getLongValue("Enter author's id, please:  ");
        long genreId = getLongValue("Enter genre's id, please: ");
        return new Book(bookId, name, new Author(authorId), new Genre(genreId));
    }

    private String getText(String message) {
        String text = null;
        while (StringUtils.isEmpty(text)) {
            System.out.print(message);
            text = scanner.next();
        }
        return text;
    }

    private long getLongValue(String message) {
        long text = 0;
        while (text <= 0) {
            System.out.print(message);
            text = scanner.nextLong();
        }
        return text;
    }

}
