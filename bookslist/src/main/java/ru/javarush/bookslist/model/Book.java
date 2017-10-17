package ru.javarush.bookslist.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;

/**
 * Created by Ganichev on 16.10.2017.
 */
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "title", nullable = false, insertable = true, updatable = true)
    private String title;
    @Column(name = "description", nullable = true, insertable = true, updatable = true)
    private String description;
    @NotNull
    @Column(name = "author", nullable = false, insertable = true, updatable = false)
    private String author;
    @NotNull
    @Column(name = "isbn", nullable = false, insertable = true, updatable = true)
    private String isbn;
    @NotNull
    @Column(name = "printYear", nullable = false, insertable = true, updatable = true)
    private Integer printYear;
    @NotNull
    @Column(name = "readAlready", nullable = false, insertable = true, updatable = true)
    private boolean readAlready;

    public Book(long id, String title, String description, String author, String isbn, int printYear, boolean readAlready) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.printYear = printYear;
        this.readAlready = readAlready;
    }

    public Book(){
        this.readAlready = false;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrintYear(Integer printYear) {
        this.printYear = printYear;
    }

    public void setReadAlready(boolean readAlready) {
        this.readAlready = readAlready;
    }

    public boolean isNew() {
        return this.id == null;
    }

    public Long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public String getAuthor() {
        return author;
    }


    public String getIsbn() {
        return isbn;
    }



    public Integer getPrintYear() {
        return printYear;
    }


    public boolean isReadAlready() {
        return readAlready;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (readAlready != book.readAlready) return false;
        if (!author.equals(book.author)) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (!id.equals(book.id)) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!printYear.equals(book.printYear)) return false;
        if (!title.equals(book.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + printYear.hashCode();
        result = 31 * result + (readAlready ? 1 : 0);
        return result;
    }
}