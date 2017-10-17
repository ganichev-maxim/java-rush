package ru.javarush.bookslist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javarush.bookslist.filter.BookFilter;
import ru.javarush.bookslist.model.Book;
import ru.javarush.bookslist.service.BookService;
import ru.javarush.bookslist.validator.BookFormValidator;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Ganichev on 16.10.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookFormValidator bookFormValidator;


    private BookFilter bookFilter = new BookFilter();

    //private int currentPage = 1;



    @InitBinder("bookForm")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(bookFormValidator);
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }



    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index(Model model) {
        return "redirect:/books";
    }


    //list all books
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String showAllBooks(Model model , HttpServletRequest request,
                               @RequestParam(required = false, value = "page",defaultValue = "1") int page,
                               @RequestParam(required = false, value = "title") String title) {


        if(request.getParameter("clear") != null){
            bookFilter.clear();
            return "redirect:/books" + bookFilter;
        }
        else{
            bookFilter.setPage(page);
            bookFilter.setTitle(title);
        }
        model.addAttribute("currentPage", bookFilter.getPage());
        model.addAttribute("bookFilter", bookFilter.getFilterWithoutPage());
        if (title != null && !title.isEmpty()){
            model.addAttribute("books", bookService.findByTitle(title, page));
            model.addAttribute("isNextPageAvailable", bookService.isNextPageAvailable(page, title));
            model.addAttribute("titleSearch", title);
        }else{
            model.addAttribute("books", bookService.getPage(page));
            model.addAttribute("isNextPageAvailable", bookService.isNextPageAvailable(page, null));
        }
        return "books/list";
    }


    //delete book
    @RequestMapping(value = "/books/delete", method = RequestMethod.POST)
    public String deleteBook(@RequestParam(value = "id", required = false) long id){
        bookService.deleteById(id);
        return "redirect:/books" + bookFilter;
    }

    //update book
    @RequestMapping(value = "/books/update", method = RequestMethod.GET)
    public String updateBook(Model model, @RequestParam(required = false, value = "id",defaultValue = "") long id){
        Book book = bookService.findById(id);
        if (book == null) return "redirect:/books";
        model.addAttribute("bookForm", book);
        return "books/bookform";
    }

    //add book
    @RequestMapping(value = "/books/add", method = RequestMethod.GET)
    public String addBook(Model model){
        Book book = new Book();
        model.addAttribute("bookForm", book);
        return "books/bookform";
    }

    //saveBook book
    @RequestMapping(value = "/books", method = RequestMethod.POST)
    public String saveOrUpdateBook(@ModelAttribute("bookForm") @Validated Book book , BindingResult result, Model model, final HttpServletRequest request){
        if (request.getParameter("back") == null){
            if (result.hasErrors()){
                model.addAttribute("bookForm", book);
                return "books/bookform";
            }
            if (book.getId() == null || !book.equals(bookService.findById(book.getId()))){
                bookService.saveOrUpdateBook(book);
            }

        }
        return "redirect:/books" + bookFilter;
    }

    //read book
    @RequestMapping(value = "/books/read", method = RequestMethod.POST)
    public String readBook(Model model, @RequestParam(value = "id") long id){
        bookService.readBook(id);
        return "redirect:/books" + bookFilter;
    }
}
