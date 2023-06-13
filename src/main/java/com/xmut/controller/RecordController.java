package com.xmut.controller;

import com.xmut.entity.PageResult;
import com.xmut.pojo.Record;
import com.xmut.pojo.User;
import com.xmut.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date: 2023/4/29
 **/
@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    //根据借阅人和书名进行条件查询
    @RequestMapping("/searchRecords")
    public ModelAndView searchRecords(Record record, HttpServletRequest request,
                                      Integer pageNum,Integer pageSize){

        //当前用户
        User user= (User) request.getSession().getAttribute("USER_SESSION");
        //如果没有值，初始化设置默认值
        if(pageNum==null){
            pageNum=1;
        }
        if (pageSize==null){
            pageSize=10;//设置默认值
        }

        ModelAndView modelAndView=new ModelAndView();

        PageResult pageResult = recordService.searchRecords(record, user, pageNum, pageSize);

        //页面
        modelAndView.setViewName("record");
        //数据
        //回显搜索信息
        modelAndView.addObject("search",record);
        //分页信息
        modelAndView.addObject("pageResult", pageResult);
        //当前页码数
        modelAndView.addObject("pageNum",pageNum);
        //地址
        modelAndView.addObject("gourl",request.getRequestURI());



        return  modelAndView;

    }
}
