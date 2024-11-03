package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.BookInfo;
import com.library.model.TypeInfo;
import com.library.service.BookInfoService;
import com.library.service.TypeInfoService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @Autowired
    private TypeInfoService typeInfoService;


    @GetMapping("/bookIndex")
    public String bookIndex(){
        return "book/bookIndex";
    }

    @GetMapping("/bookIndexForReader")
    public String bookIndexForReader(){
        return "book/bookIndexForReader";
    }


    @RequestMapping("/bookAll")
    @ResponseBody
    public DataInfo bookAll(BookInfo bookInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer limit){
        PageInfo<BookInfo> pageInfo = bookInfoService.queryBookInfoAll(bookInfo,pageNum,limit);
        return DataInfo.ok("Success",pageInfo.getTotal(),pageInfo.getList());
    }


    @GetMapping("/bookAdd")
    public String bookAdd(){
        return "book/bookAdd";
    }

    @RequestMapping("/addBookSubmit")
    @ResponseBody
    public DataInfo addBookSubmit(BookInfo info){
        bookInfoService.addBookSubmit(info);
        return DataInfo.ok();
    }


    @GetMapping("/queryBookInfoById")
    public String queryTypeInfoById(Integer id, Model model){
        BookInfo bookInfo= bookInfoService.queryBookInfoById(id);
        model.addAttribute("info",bookInfo);
        return "book/updateBook";
    }


    @RequestMapping("/updateBookSubmit")
    @ResponseBody
    public DataInfo updateBookSubmit(@RequestBody BookInfo info){
        bookInfoService.updateBookSubmit(info);
        return DataInfo.ok();
    }


    @RequestMapping("/deleteBook")
    @ResponseBody
    public DataInfo deleteBook(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        bookInfoService.deleteBookByIds(list);
        return DataInfo.ok();
    }

    @RequestMapping("/findAllList")
    @ResponseBody
    public List<TypeInfo> findAll(){
        PageInfo<TypeInfo> pageInfo = typeInfoService.queryTypeInfoAll(null,1,100);
        List<TypeInfo> lists = pageInfo.getList();
        return lists;
    }
}
