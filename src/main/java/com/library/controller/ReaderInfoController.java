package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.Admin;
import com.library.model.ReaderInfo;
import com.library.service.AdminService;
import com.library.service.ReaderInfoService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Date;

@Controller
public class ReaderInfoController {

    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private AdminService adminService;

    @GetMapping("/readerIndex")
    public String readerIndex(){
        return "reader/readerIndex";
    }

    @RequestMapping("/readerAll")
    @ResponseBody
    public DataInfo queryReaderAll(ReaderInfo readerInfo, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer limit){
        PageInfo<ReaderInfo> pageInfo = readerInfoService.queryAllReaderInfo(readerInfo,pageNum,limit);
        return DataInfo.ok("Success",pageInfo.getTotal(),pageInfo.getList());
    }

    @RequestMapping("/readerAdd")
    public String readerAdd(){
        return "reader/readerAdd";
    }

    @RequestMapping("/addReaderSubmit")
    @ResponseBody
    public DataInfo addReaderSubmit(@RequestBody ReaderInfo readerInfo){
        readerInfo.setPassword("123456");
        readerInfo.setRegisterDate(new Date());
        readerInfoService.addReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }

    @GetMapping("/queryReaderInfoById")
    public String queryReaderInfoById(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, Model model){
        ReaderInfo readerInfo;
        if (id != null) {
            readerInfo = readerInfoService.queryReaderInfoById(id);
        } else {
            readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
        }

        model.addAttribute("id", id);
        model.addAttribute("info", readerInfo);
        return "reader/updateReader";
    }


    @RequestMapping("/updateReaderSubmit")
    @ResponseBody
    public DataInfo updateReaderSubmit(@RequestBody ReaderInfo readerInfo){
        readerInfoService.updateReaderInfoSubmit(readerInfo);
        return DataInfo.ok();
    }

    @RequestMapping("/deleteReader")
    @ResponseBody
    public DataInfo deleteReader(String ids){
        List<String> list= Arrays.asList(ids.split(","));
        readerInfoService.deleteReaderInfoByIds(list);
        return DataInfo.ok();
    }


    @RequestMapping("/updatePwdSubmit2")
    @ResponseBody
    public DataInfo updatePwdSubmit(HttpServletRequest request, String oldPwd, String newPwd){
        HttpSession session = request.getSession();
        if(session.getAttribute("type")=="admin"){
            Admin admin = (Admin)session.getAttribute("user");
            Admin admin1 = (Admin)adminService.queryAdminById(admin.getId());
            if (!oldPwd.equals(admin1.getPassword())){
                return DataInfo.fail("The old password entered is wrong");
            }else{
                admin1.setPassword(newPwd);
                adminService.updateAdminSubmit(admin1);
            }
        }else{

            ReaderInfo readerInfo = (ReaderInfo) session.getAttribute("user");
            ReaderInfo readerInfo1 = readerInfoService.queryReaderInfoById(readerInfo.getId());
            if (!oldPwd.equals(readerInfo1.getPassword())){
                return DataInfo.fail("The old password entered is wrong");
            }else{
                readerInfo1.setPassword(newPwd);
                readerInfoService.updateReaderInfoSubmit(readerInfo1);
            }
        }
        return DataInfo.ok();
    }
}
