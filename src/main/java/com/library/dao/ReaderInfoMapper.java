package com.library.dao;

import com.library.model.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReaderInfoMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(ReaderInfo record);


    int insertSelective(ReaderInfo record);

    ReaderInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReaderInfo record);


    int updateByPrimaryKey(ReaderInfo record);


    List<ReaderInfo> queryAllReaderInfo(ReaderInfo readerInfo);


    ReaderInfo queryUserInfoByNameAndPassword(@Param("username") String username, @Param("password") String password);
}