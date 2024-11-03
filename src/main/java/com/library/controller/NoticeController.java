package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.Notice;
import com.library.service.NoticeService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/noticeIndexOfBack")
    public String noticeIndexOfBack(){
        return "notice/noticeIndexOfBack";
    }

    @GetMapping("/noticeIndexOfReader")
    public String noticeIndexOfReader(){
        return "notice/noticeIndexOfReader";
    }


    @RequestMapping("/noticeAll")
    @ResponseBody
    public DataInfo noticeAll(Notice notice,@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10")Integer limit){
        PageInfo<Notice> pageInfo = noticeService.queryAllNotice(notice, pageNum, limit);
        return DataInfo.ok("Success",pageInfo.getTotal(),pageInfo.getList());
    }

    @GetMapping("/noticeAdd")
    public String noticeAdd(){
        return "notice/noticeAdd";
    }


    @RequestMapping("/addNoticeSubmit")
    @ResponseBody
    public DataInfo addNoticeSubmit(HttpServletRequest request, Notice notice){
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        notice.setAuthor(username);
        notice.setCreateDate(new Date());
        noticeService.addNotice(notice);
        return DataInfo.ok();
    }

    @GetMapping("/queryNoticeById")
    public String queryNoticeById(Integer id, Model model){
        Notice notice = noticeService.queryNoticeById(id);
        model.addAttribute("info",notice);
        return "notice/updateNotice";
    }

    @RequestMapping("/deleteNoticeByIds")
    @ResponseBody
    public DataInfo deleteNoticeByIds(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        noticeService.deleteNoticeByIds(list);
        return DataInfo.ok();
    }
}
