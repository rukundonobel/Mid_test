package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.BookInfo;

import java.util.List;

public interface BookInfoService {


    PageInfo<BookInfo> queryBookInfoAll(BookInfo bookInfo,Integer pageNum,Integer limit);

    void addBookSubmit(BookInfo bookInfo);


    BookInfo queryBookInfoById(Integer id);

    void updateBookSubmit(BookInfo info);

    void deleteBookByIds(List<String> ids);


    List<BookInfo> getBookCountByType();
}
