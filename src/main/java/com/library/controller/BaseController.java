package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.Notice;
import com.library.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;

@Controller
public class BaseController {

    @Autowired
    private NoticeService noticeService;


    @GetMapping("/index")
    public String index(){
        return "index";
    }


    @GetMapping("/welcome")
    public String welcome(Model model){

        PageInfo<Notice> pageInfo =  noticeService.queryAllNotice(null,1,5);
        if (pageInfo!=null){
            List<Notice> noticeList = pageInfo.getList();
            model.addAttribute("noticeList",noticeList);
        }
        return "welcome";
    }

    @GetMapping("/updatePassword")
    public String updatePwd(){
        return "pwdUpdate/updatePwd";
    }

}

