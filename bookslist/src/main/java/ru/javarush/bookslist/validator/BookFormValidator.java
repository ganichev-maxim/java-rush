package ru.javarush.bookslist.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.javarush.bookslist.model.Book;

import java.util.Calendar;

/**
 * Created by Ganichev on 16.10.2017.
 */
@Component
public class BookFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        Book book = (Book) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "title.is.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "author.is.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", "isbn.is.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "printYear", "year.is.required");
        if (book.getPrintYear() == null || book.getPrintYear() < 1000 || book.getPrintYear() > Calendar.getInstance().get(Calendar.YEAR)){
            errors.rejectValue("printYear", "enter.valid.year");
        }
    }
}
