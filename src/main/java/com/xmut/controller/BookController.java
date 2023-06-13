package com.xmut.controller;

import com.xmut.entity.PageResult;

import com.xmut.entity.Result;
import com.xmut.pojo.Book;
import com.xmut.pojo.User;
import com.xmut.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author
 * @date: 2023/4/26
 **/
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    //图书推荐
    @RequestMapping("/selectNewbooks")
    public ModelAndView selectNewBooks(){

        ModelAndView modelAndView=new ModelAndView();
        //数据和视图
        int pageNum=1;
        int pageSize=5;
        PageResult pageResult = bookService.selectNewBooks(pageNum, pageSize);
        modelAndView.addObject("pageResult",pageResult);

        modelAndView.setViewName("books_new");

        return modelAndView;
    }

    //获取图书的信息
    @RequestMapping("/findById")
    @ResponseBody
    public Result<Book> findById(String id){

        Book book = bookService.findById(id);

        return new Result<>(true,"",book);
    }

    //借阅图书
    @RequestMapping("/borrowBook")
    @ResponseBody
    public Result borrowBook(Book book, HttpSession session){
        //设置借阅人信息
        User user = (User) session.getAttribute("USER_SESSION");
        book.setBorrower(user.getName());
        //调用service
        Integer num = bookService.borrowBook(book);
        if (num>0){
            //借阅成功
            return new Result(true,"借阅成功");
        }else {
            return new Result(true,"借阅失败");
        }

    }

    //图书查询
    @RequestMapping("/search")
    public ModelAndView searach(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request){
            ModelAndView modelAndView=new ModelAndView();
            //设置分页的默认值
            if (pageNum==null){
                pageNum=1;//默认为一页
            }
            if (pageSize==null){
                pageSize=10;//默认一页10条数据
            }

            //调用service
            PageResult pageResult = bookService.search(book, pageNum,pageSize);
            //页面
            modelAndView.setViewName("books");
            //数据
            //搜索框数据回显
            modelAndView.addObject("search",book);
            //分页数据信息
            modelAndView.addObject("pageResult",pageResult);
            //当前页码
            modelAndView.addObject("pageNum",pageNum);
            //分页请求再次请求的地址
            modelAndView.addObject("gourl",request.getRequestURI());

            return modelAndView;
    }
    //新增图书
    @RequestMapping("/addBook")
    @ResponseBody //返回一个json数据
    public  Result addBook(Book book){
        Integer num = bookService.addBook(book);
        if (num>0){
            //增加成功
            return new Result(true,"添加图书成功");
        }else {
            //增加成功
            return new Result(false,"添加图书失败");
        }
    }
    //编辑图书
    @RequestMapping("/editBook")
    @ResponseBody
    public  Result editBook(Book book){
        Integer num = bookService.editBook(book);
        if (num>0){
            //修改成功
            return new Result(true,"图书修改成功");
        }else {
            //修改成功
            return new Result(false,"图书修改失败");
        }
    }

    //图书借阅
    //图书查询
    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book,
                                       Integer pageNum,
                                       Integer pageSize,
                                       HttpServletRequest request){

        ModelAndView modelAndView=new ModelAndView();
        //设置分页的默认值
        if (pageNum==null){
            pageNum=1;//默认为一页
        }
        if (pageSize==null){
            pageSize=10;//默认一页10条数据
        }

        //调用service
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = bookService.searchBorrowed(book,user, pageNum,pageSize);
        //页面
        modelAndView.setViewName("book_borrowed");
        //数据
        //搜索框数据回显
        modelAndView.addObject("search",book);
        //分页数据信息
        modelAndView.addObject("pageResult",pageResult);
        //当前页码
        modelAndView.addObject("pageNum",pageNum);
        //分页请求再次请求的地址
        modelAndView.addObject("gourl",request.getRequestURI());

        return modelAndView;
    }
    //图书归还
    @ResponseBody
    @RequestMapping("/returnBook")
    public Result returnBook(String id,HttpSession session){
        User user= (User) session.getAttribute("USER_SESSION");
        boolean flag = bookService.returnBook(id, user);
        if (flag){
            return new Result(flag,"还书确认中，请到行政中心还书");
        }else {
            return new Result(flag,"还书失败");
        }
    }

    //管理员确认归还
    @ResponseBody
    @RequestMapping("/returnConfirm")
    public Result returnConfirm(String id){

        boolean flag = bookService.confirmBook(id);
        if (flag){
            return new Result(flag,"确认成功");
        }else {
            return new Result(flag,"确认失败");
        }

    }
}
