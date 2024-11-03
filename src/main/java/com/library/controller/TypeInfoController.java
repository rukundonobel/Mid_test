package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.TypeInfo;
import com.library.service.TypeInfoService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class TypeInfoController {

    @Autowired
    private TypeInfoService typeInfoService;


    @GetMapping("/typeIndex")
    public String typeIndex(){
        return "type/typeIndex";
    }

    @RequestMapping("/typeAll")

    @ResponseBody
    public DataInfo typeAll(String name, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer limit){
        PageInfo<TypeInfo> pageInfo = typeInfoService.queryTypeInfoAll(name,pageNum,limit);
        return DataInfo.ok("Success",pageInfo.getTotal(),pageInfo.getList());

    }


    @GetMapping("/typeAdd")
    public String typeAdd(){
        return "type/typeAdd";
    }


    @PostMapping("/addTypeSubmit")
    @ResponseBody
    public DataInfo addTypeSubmit(TypeInfo info){
        typeInfoService.addTypeSubmit(info);
        return DataInfo.ok();
    }

    @GetMapping("/queryTypeInfoById")
    public String queryTypeInfoById(Integer id, Model model){
        TypeInfo info= typeInfoService.queryTypeInfoById(id);
        model.addAttribute("info",info);
        return "type/updateType";
    }



    @RequestMapping("/updateTypeSubmit")
    @ResponseBody
    public DataInfo updateTypeSubmit(@RequestBody TypeInfo info){
        typeInfoService.updateTypeSubmit(info);
        return DataInfo.ok();
    }


    @RequestMapping("/deleteType")
    @ResponseBody
    public DataInfo deleteType(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        typeInfoService.deleteTypeByIds(list);
        return DataInfo.ok();
    }
}
