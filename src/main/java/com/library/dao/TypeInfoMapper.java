package com.library.dao;

import com.library.model.TypeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TypeInfoMapper {

    List<TypeInfo> queryTypeInfoAll(@Param(value = "name") String name);


    void addTypeSubmit(TypeInfo info);


    TypeInfo queryTypeInfoById(Integer id);


    void updateTypeSubmit(TypeInfo info);

    void deleteTypeByIds(List<Integer> id);


}