package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.ReaderInfo;

import java.util.List;

public interface ReaderInfoService {

    PageInfo<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo,Integer pageNum,Integer limit);


    void addReaderInfoSubmit(ReaderInfo readerInfo);

    ReaderInfo queryReaderInfoById(Integer id);

    void updateReaderInfoSubmit(ReaderInfo readerInfo);

    void deleteReaderInfoByIds(List<String> ids);

    ReaderInfo queryUserInfoByNameAndPassword(String username,String password);
}
