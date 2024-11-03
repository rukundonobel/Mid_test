package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.LendList;

import java.util.List;

public interface LendListService {


    PageInfo<LendList> queryLendListAll(LendList lendList, int page, int limit);


    void addLendListSubmit(LendList lendList);


    void deleteLendListById(List<String> ids, List<String> bookIds);

    void updateLendListSubmit(List<String> ids, List<String> bookIds);


    void backBook(LendList lendList);


    List<LendList> queryLookBookList(Integer rid, Integer bid);
}
