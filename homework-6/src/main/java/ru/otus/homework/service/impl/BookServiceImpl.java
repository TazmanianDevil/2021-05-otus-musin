package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.BookRepository;
import ru.otus.homework.model.Book;
import ru.otus.homework.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Transactional
    @Override
    public Book create(Book book) {
        return bookRepository.create(book);
    }

    @Transactional
    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }


}
