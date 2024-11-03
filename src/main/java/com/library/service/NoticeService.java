package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.Notice;

import java.util.List;

public interface NoticeService {


    PageInfo<Notice> queryAllNotice(Notice notice,Integer pageNum,Integer limit);


    void addNotice(Notice notice);

    Notice queryNoticeById(Integer id);

    void deleteNoticeByIds(List<String> ids);
}
