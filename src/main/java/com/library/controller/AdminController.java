package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.Admin;
import com.library.service.AdminService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminIndex")
    public String adminIndex(){
        return "admin/adminIndex";
    }

    @RequestMapping("/adminAll")
    @ResponseBody
    public DataInfo queryAdminAll(Admin admin, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer limit){
        PageInfo<Admin> pageInfo = adminService.queryAdminAll(admin,pageNum,limit);
        return DataInfo.ok("Success",pageInfo.getTotal(),pageInfo.getList());
    }


    @GetMapping("/adminAdd")
    public String adminAdd(){
        return "admin/adminAdd";
    }

    @RequestMapping("/addAdminSubmit")
    @ResponseBody
    public DataInfo addBookSubmit(Admin admin){
        adminService.addAdminSubmit(admin);
        return DataInfo.ok();
    }

    @GetMapping("/queryAdminById")
    public String queryAdminById(Integer id, Model model){
        model.addAttribute("id",id);
        return "admin/updateAdmin";
    }


    @RequestMapping("/updatePwdSubmit")
    @ResponseBody
    public DataInfo updatePwdSubmit(Integer id,String oldPwd,String newPwd){
        Admin admin = adminService.queryAdminById(id);   //Query objects based on id
        if (!oldPwd.equals(admin.getPassword())){
            return DataInfo.fail("The old password entered is wrong");
        }else{
            admin.setPassword(newPwd);
            adminService.updateAdminSubmit(admin);   //Database modification
            return DataInfo.ok();
        }
    }

    @RequestMapping("/deleteAdminByIds")
    @ResponseBody
    public DataInfo deleteAdminByIds(String ids){
        List<String> list = Arrays.asList(ids.split(","));
        adminService.deleteAdminByIds(list);
        return DataInfo.ok();
    }

}
