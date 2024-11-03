package com.library.model;

import java.io.Serializable;
import java.util.Date;

public class LendList implements Serializable {

    private Integer id;


    private Integer bookId;


    private Integer readerId;


    private Date lendDate;


    private Date backDate;

    private Integer backType;


    private String exceptRemarks;

    private BookInfo bookInfo;

    private ReaderInfo readerInfo;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getReaderId() {
        return readerId;
    }

    public void setReaderId(Integer readerId) {
        this.readerId = readerId;
    }

    public Date getLendDate() {
        return lendDate;
    }


    public void setLendDate(Date lenddate) {
        this.lendDate = lenddate;
    }

    public Date getBackDate() {
        return backDate;
    }
    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }


    public Integer getBackType() {
        return backType;
    }


    public void setBackType(Integer backType) {
        this.backType = backType;
    }


    public String getExceptRemarks() {
        return exceptRemarks;
    }


    public void setExceptRemarks(String exceptRemarks) {
        this.exceptRemarks = exceptRemarks == null ? null : exceptRemarks.trim();
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public ReaderInfo getReaderInfo() {
        return readerInfo;
    }

    public void setReaderInfo(ReaderInfo readerInfo) {
        this.readerInfo = readerInfo;
    }
}