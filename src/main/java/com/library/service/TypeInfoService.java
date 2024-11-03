package com.library.service;

import com.github.pagehelper.PageInfo;
import com.library.model.TypeInfo;

import java.util.List;

public interface TypeInfoService {
    PageInfo<TypeInfo> queryTypeInfoAll(String name, Integer pageNum, Integer limit);


    void addTypeSubmit(TypeInfo info);


    TypeInfo queryTypeInfoById(Integer id);


    void updateTypeSubmit(TypeInfo info);

    void deleteTypeByIds(List<String> id);

}
