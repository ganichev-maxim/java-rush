package ru.javarush.bookslist.dao;


import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.javarush.bookslist.model.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ganichev on 16.10.2017.
 */
@Repository("bookDao")

public class BookDaoImpl extends AbstractDao<Long, Book> implements BookDao {

    private static List<Book> books = new ArrayList<>();
    private static final int LIMIT_RECORDS = 10;



    @Override
    public void deleteById(long id) {
        Object persistentInstance = getSession().load(Book.class, id);
        if (persistentInstance != null){
            getSession().delete(persistentInstance);
        }
    }

    @Override
    public void saveBook(Book book) {
        getSession().save(book);
        getSession().flush();
    }

    @Override
    public Book findById(long id) {
        return getByKey(id);
        /*
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getId() == id){
                return book;
            }
        }
        return null;
        */
    }

    @Override
    public void update(Book book) {
         book.setReadAlready(false);
         saveOrUpdate(book);
         getSession().flush();
    }


    @Override
    public void readBook(long id) {
        Book book = getSession().find(Book.class, id);
        book.setReadAlready(true);
        getSession().flush();
        /*
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()){
            Book book = iterator.next();
            if(book.getId() == id && !book.isReadAlready()){
                book.setReadAlready(true);
            }
        }
        */
    }

    @Override
    public List<Book> getPage(int currentPage) {

        Query query =  getSession().createQuery("from Book");
        query.setFirstResult((currentPage - 1) * LIMIT_RECORDS);
        query.setMaxResults(LIMIT_RECORDS);
        return query.list();


        /*List<Book> booksResult = new ArrayList<>();
        for (int i = (currentPage - 1) * LIMIT_RECORDS; i < currentPage * LIMIT_RECORDS && i < books.size(); i++) {
            booksResult.add(books.get(i));
        }
        return booksResult;
        */
    }

    @Override
    public boolean isNextPageAvailable(int pageNum, String title) {
        String queryString = "from Book";
        if (title != null && title.length() > 0){
            queryString += " where lower(title) like :title";
        }

        Query query =  getSession().createQuery(queryString);

        if (title != null && title.length() > 0){
            query.setParameter("title", "%" + title.toLowerCase() + "%");
        }
        query.setFirstResult(pageNum * LIMIT_RECORDS);
        query.setMaxResults(1);
        return query.list().size() > 0;
    }

    @Override
    public List<Book> findByTitle(String title, int page) {
        String queryString = "from Book";
        Query query = getSession().createQuery("from Book where lower(title) like :title");
        query.setParameter("title", "%" + title.toLowerCase() + "%");
        query.setFirstResult((page - 1) * LIMIT_RECORDS);
        query.setMaxResults(LIMIT_RECORDS);
        return query.list();
    }
}
