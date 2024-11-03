package com.library.dao;

import com.library.model.LendList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LendListMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(LendList record);


    int insertSelective(LendList record);


    LendList selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LendList record);

    int updateByPrimaryKey(LendList record);

    List<LendList> queryLendListAll(LendList lendList);



    List<LendList> queryLookBookList(@Param("rid") Integer rid, @Param("bid") Integer bid);


    void updateLendListSubmit(LendList lendList);
}