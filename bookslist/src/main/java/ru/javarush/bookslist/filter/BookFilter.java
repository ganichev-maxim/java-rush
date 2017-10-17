package ru.javarush.bookslist.filter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Ganichev on 16.10.2017.
 */
public class BookFilter {
    private String title;
    private Integer page;

    public BookFilter() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (page != null){
            stringBuilder.append("page=");
            stringBuilder.append(page);
        }
        String filter = getFilterWithoutPage();

        if (filter.length() > 0){
            if (stringBuilder.length() > 0) stringBuilder.append("&");
            stringBuilder.append(filter);
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        else{
            return "?" + stringBuilder.toString();
        }

    }

    public String getFilterWithoutPage(){
        StringBuilder stringBuilder = new StringBuilder();
        if (title != null && title.length() > 0){
            try {
                if (stringBuilder.length() > 0) stringBuilder.append("&");
                stringBuilder.append("title=");
                stringBuilder.append(URLEncoder.encode(title,"UTF-8"));

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (stringBuilder.length() == 0) {
            return "";
        }
        else{
            return stringBuilder.toString();
        }
    }

    public void clear(){
        page = 1;
        title = null;
    }
}
