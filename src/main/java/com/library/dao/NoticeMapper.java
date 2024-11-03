package com.library.dao;

import com.library.model.Notice;

import java.util.List;

public interface NoticeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);


    int insertSelective(Notice record);


    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

    List<Notice> queryNoticeAll(Notice notice);
}