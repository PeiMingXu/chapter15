package com.xmut.service;

import com.xmut.entity.PageResult;
import com.xmut.pojo.Book;
import com.xmut.pojo.User;

/**
 * @author
 * @date: 2023/4/26
 **/
public interface BookService {
    //新书推荐五本
    public PageResult selectNewBooks(Integer pageNum,Integer pageSize);

    //根据id查询
    public Book findById(String id);

    //借阅图书
    public Integer borrowBook(Book book);

    //图书查询，分页
    public PageResult search(Book book,Integer pageNum,Integer pageSize);

    //图书新增
    public Integer addBook(Book book);

    //图书修改
    public Integer editBook(Book book);

    //分页查询当前借阅信息
    public PageResult searchBorrowed(Book book, User user,Integer pageNum,Integer pageSize);

    //图书归还
    public boolean returnBook(String id,User user);

    //管理员确认归还图书
    public boolean confirmBook(String id);
}
