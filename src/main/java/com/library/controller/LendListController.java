package com.library.controller;

import com.github.pagehelper.PageInfo;
import com.library.model.BookInfo;
import com.library.model.LendList;
import com.library.model.ReaderInfo;
import com.library.service.BookInfoService;
import com.library.service.LendListService;
import com.library.service.ReaderInfoService;
import com.library.utils.DataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
public class LendListController {
    @Autowired
    private LendListService lendListService;
    @Autowired
    private ReaderInfoService readerService;

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping("/lendListIndex")
    public String lendListIndex(){
        return "lend/lendListIndex";
    }


    @ResponseBody
    @RequestMapping("/lendListAll")
    public DataInfo lendListAll(Integer type, String readerNumber, String name, Integer status,
                                @RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer limit){

        LendList info=new LendList();
        info.setBackType(type);


        ReaderInfo reader=new ReaderInfo();
        reader.setReaderNumber(readerNumber);

        info.setReaderInfo(reader);


        BookInfo book=new BookInfo();
        book.setName(name);
        book.setStatus(status);
        info.setBookInfo(book);


        PageInfo pageInfo=lendListService.queryLendListAll(info,page,limit);
        return DataInfo.ok("ok",pageInfo.getTotal(),pageInfo.getList());
    }



    @GetMapping("/addLendList")
    public String addLendList(){
        return "lend/addLendList";
    }

    @ResponseBody
    @RequestMapping("/addLend")
    public DataInfo addLend(String readerNumber,String ids){

        List<String> list= Arrays.asList(ids.split(","));

        ReaderInfo reader=new ReaderInfo();
        reader.setReaderNumber(readerNumber);
        PageInfo<ReaderInfo> pageInfo=readerService.queryAllReaderInfo(reader,1,1);
        if(pageInfo.getList().size()==0){
            return DataInfo.fail("Reader No. does not exist");
        }else{
            ReaderInfo readerCard2=pageInfo.getList().get(0);

            for(String bid:list) {
                LendList lendList = new LendList();
                lendList.setReaderId(readerCard2.getId());
                lendList.setBookId(Integer.valueOf(bid));
                lendList.setLendDate(new Date());
                lendListService.addLendListSubmit(lendList);
                BookInfo info = bookInfoService.queryBookInfoById(Integer.valueOf(bid));
                info.setStatus(1);
                bookInfoService.updateBookSubmit(info);
            }

        }

        return DataInfo.ok();
    }

    @ResponseBody
    @RequestMapping("/deleteLendListByIds")
    public DataInfo deleteLendListByIds(String ids, String bookIds){
        List list=Arrays.asList(ids.split(","));
        List blist=Arrays.asList(bookIds.split(","));

        lendListService.deleteLendListById(list,blist);
        return DataInfo.ok();
    }


    @ResponseBody
    @RequestMapping("/backLendListByIds")
    public DataInfo backLendListByIds(String ids,String bookIds){
        List list=Arrays.asList(ids.split(","));
        List blist=Arrays.asList(bookIds.split(","));
        lendListService.updateLendListSubmit(list,blist);
        return DataInfo.ok();
    }

    @GetMapping("/excBackBook")
    public String excBackBook(HttpServletRequest request, Model model){
        //获取借阅记录id
        String id=request.getParameter("id");
        String  bId=request.getParameter("bookId");
        model.addAttribute("id",id);
        model.addAttribute("bid",bId);
        return "lend/excBackBook";
    }


    @ResponseBody
    @RequestMapping("/updateLendInfoSubmit")
    public DataInfo updateLendInfoSubmit(LendList lendList){
        lendListService.backBook(lendList);
        return DataInfo.ok();
    }


    @RequestMapping("/queryLookBookList")
    public String queryLookBookList(String flag,Integer id,Model model){
        List<LendList> list=null;
        if(flag.equals("book")){
            list=lendListService.queryLookBookList(null,id);
        }else{
            list=lendListService.queryLookBookList(id,null);
        }
        model.addAttribute("info",list);
        return "lend/lookBookList";
    }

    @RequestMapping("/queryLookBookList2")
    public String queryLookBookList(HttpServletRequest request,Model model){
        ReaderInfo readerInfo = (ReaderInfo) request.getSession().getAttribute("user");
        List<LendList> list = list=lendListService.queryLookBookList(readerInfo.getId(),null);
        model.addAttribute("info",list);
        return "lend/lookBookList";
    }


}
