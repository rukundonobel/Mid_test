package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.Admin;

import java.util.List;
public interface AdminService {

    PageInfo<Admin> queryAdminAll(Admin admin,Integer pageNum,Integer limit);


    void addAdminSubmit(Admin admin);

    Admin queryAdminById(Integer id);


    void updateAdminSubmit(Admin admin);

    void deleteAdminByIds(List<String> ids);

    Admin queryUserByNameAndPassword(String username,String password);
}
