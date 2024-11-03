package com.library.controller;
import com.library.codeutil.IVerifyCodeGen;
import com.library.codeutil.SimpleCharVerifyCodeGenImpl;
import com.library.codeutil.VerifyCode;
import com.library.model.Admin;
import com.library.model.ReaderInfo;
import com.library.service.AdminService;
import com.library.service.ReaderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ReaderInfoService readerService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @RequestMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        IVerifyCodeGen iVerifyCodeGen = new SimpleCharVerifyCodeGenImpl();
        try {

            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();

            request.getSession().setAttribute("VerifyCode", code);

            response.setHeader("Pragma", "no-cache");

            response.setHeader("Cache-Control", "no-cache");

            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("Exception handling");
        }
    }


    @RequestMapping("/loginIn")
    public String loginIn(HttpServletRequest request, Model model){

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("captcha");
        String type = request.getParameter("type");

        HttpSession session = request.getSession();
        String realCode = (String)session.getAttribute("VerifyCode");
        if (!realCode.toLowerCase().equals(code.toLowerCase())){
            model.addAttribute("msg","Incorrect verification code");
            return "login";
        }else{

            if(type.equals("1")){
                Admin admin=adminService.queryUserByNameAndPassword(username,password);
                if(admin==null){
                    model.addAttribute("msg","Wrong username or password");
                    return "login";
                }
                session.setAttribute("user",admin);
                session.setAttribute("type","admin");
                session.setAttribute("adminType", admin.getAdminType());
                session.setAttribute("username", admin.getUsername());
            }else{
                ReaderInfo readerInfo=readerService.queryUserInfoByNameAndPassword(username,password);
                if(readerInfo==null){
                    model.addAttribute("msg","Wrong username or password");
                    return "login";
                }
                session.setAttribute("user",readerInfo);
                session.setAttribute("readerId",readerInfo.getId());
                session.setAttribute("type","reader");
            }

            return "index";
        }
    }

    @GetMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "/login";
    }
}
