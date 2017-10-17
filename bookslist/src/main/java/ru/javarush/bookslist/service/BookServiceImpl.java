package ru.javarush.bookslist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.bookslist.dao.BookDao;
import ru.javarush.bookslist.model.Book;

import java.util.List;

/**
 * Created by Ganichev on 16.10.2017.
 */
@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }


    @Override
    public void deleteById(long id) {

        bookDao.deleteById(id);
    }

    @Override
    public void saveOrUpdateBook(Book book) {
        if (book.getId() == null){
            bookDao.saveBook(book);
        }
        else {
            bookDao.update(book);
        }
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public void readBook(long id) {
        bookDao.readBook(id);
    }

    @Override
    public List<Book> getPage(int currentPage) {
        return bookDao.getPage(currentPage);
    }

    @Override
    public boolean isNextPageAvailable(int pageNum, String title) {
        return bookDao.isNextPageAvailable(pageNum, title);
    }

    @Override
    public  List<Book> findByTitle(String title, int page) {
        return bookDao.findByTitle(title, page);
    }
}
