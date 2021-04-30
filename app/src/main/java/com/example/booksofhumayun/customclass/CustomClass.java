package com.example.booksofhumayun.customclass;

import java.io.Serializable;

public class CustomClass implements Serializable {

    private int cover;
    private String bookName;
    private String publishYear;
    private String pageNumber;

    public CustomClass(int cover, String bookName, String publishYear, String pageNumber) {
        this.cover = cover;
        this.bookName = bookName;
        this.publishYear = publishYear;
        this.pageNumber = pageNumber;
    }


    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(String publishYear) {
        this.publishYear = publishYear;
    }

    public String getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(String pageNumber) {
        this.pageNumber = pageNumber;
    }
}
