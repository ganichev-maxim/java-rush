package ru.javarush.bookslist.dao;

import ru.javarush.bookslist.model.Book;

import java.util.List;

/**
 * Created by Ganichev on 16.10.2017.
 */
public interface BookDao {

    void deleteById(long id);

    void saveBook(Book book);

    Book findById(long id);

    void update(Book book);

    void readBook(long id);

    List<Book> getPage(int currentPage);

    boolean isNextPageAvailable(int pageNum, String title);

    List<Book> findByTitle(String title, int page);
}
