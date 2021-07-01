package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookRepository;
import ru.otus.homework.model.Book;
import ru.otus.homework.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book getById(long id) {
        return bookRepository.getById(id);
    }

    @Override
    public boolean deleteById(long id) {
        return bookRepository.deleteById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public long insert(Book book) {
        return bookRepository.insert(book);
    }

    @Override
    public boolean update(Book book) {
        return bookRepository.update(book);
    }


}
