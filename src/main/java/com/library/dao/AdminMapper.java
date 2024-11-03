package com.library.dao;

import com.library.model.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Admin record);


    int insertSelective(Admin record);


    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);


    int updateByPrimaryKey(Admin record);

    List<Admin> queryAdminInfoAll(Admin admin);

    Admin queryUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}