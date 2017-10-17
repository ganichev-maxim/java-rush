package ru.javarush.bookslist.service;

import ru.javarush.bookslist.model.Book;

import java.util.List;

/**
 * Created by Ganichev on 16.10.2017.
 */
public interface BookService {


    void deleteById(long id);

    void saveOrUpdateBook(Book book);

    Book findById(long id);

    void readBook(long id);

    List<Book> getPage(int currentPage);

    boolean isNextPageAvailable(int pageNum, String title);

    List<Book> findByTitle(String title, int page);
}
