package com.xmut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmut.entity.PageResult;
import com.xmut.mapper.BookMapper;
import com.xmut.pojo.Book;
import com.xmut.pojo.Record;
import com.xmut.pojo.User;
import com.xmut.service.BookService;
import com.xmut.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author
 * @date: 2023/4/26
 **/
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RecordService recordService;
    //图书推荐
    @Override
    public PageResult selectNewBooks(Integer pageNum, Integer pageSize) {
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用mapper
        Page<Book> page = bookMapper.selectNewBooks();
        return new PageResult(page.getTotal(),page.getResult());
    }
    //通过id获取
    @Override
    public Book findById(String id) {

        return bookMapper.findById(id);
    }

    //图书借阅
    @Override
    public Integer borrowBook(Book book) {
        //设置借阅状态 0：
        book.setStatus("1");
        //借阅时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String borrowTime = format.format(new Date());
        book.setBorrowtime(borrowTime);

        return bookMapper.borrowBook(book);//调用mapper
    }
    //图书查询
    @Override
    public PageResult search(Book book, Integer pageNum, Integer pageSize) {
        //开启分页查询
        PageHelper.startPage(pageNum,pageSize);
        //调用Mapper
        Page<Book> page = bookMapper.searchBooks(book);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public Integer addBook(Book book) {
        //设置上架时间
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String uploadTime = format.format(new Date());
        book.setUploadtime(uploadTime);
        return bookMapper.addBook(book);
    }
    //图书修改
    @Override
    public Integer editBook(Book book) {

        return bookMapper.editBook(book);
    }

    @Override
    public PageResult searchBorrowed(Book book, User user, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);
        //设置借阅人
        book.setBorrower(user.getName());

        Page<Book> page=null;
        //区分用户类型，调用不同的mapper
        if ("ADMIN".equals(user.getRole())){
            page= bookMapper.selectBorrowed(book);
        }else {
            page=bookMapper.selectMyBorrowed(book);
        }
        return new PageResult(page.getTotal(),page.getResult());


    }
    //图书归还
    @Override
    public boolean returnBook(String id, User user) {
        //判断当前用户和图书的借阅者是否是同一个，如果是，才进行归还
        Book book = bookMapper.findById(id);
        boolean flag = book.getBorrower().equals(user.getName());

        //调用mapper,修改图书状态
        Integer num=0;
        if (flag){
            book.setStatus("2");
            num = bookMapper.editBook(book);
        }
        return num>0;
    }
    //管理员确认归还
    @Override
    public boolean confirmBook(String id) {
        //查询书本信息
        Book book = bookMapper.findById(id);
        String borrower=book.getBorrower();
        String borrowtime=book.getBorrowtime();
        //设置书籍状态为可借阅
        book.setStatus("0");
        //清空借阅信息
        book.setBorrower("");
        book.setBorrowtime("");
        book.setReturntime("");

        Integer num = bookMapper.editBook(book);

        if (num>0){
            //确认归还无误，新增一条借阅记录
            Record record=new Record();

            record.setBookisbn(book.getIsbn());//把借阅的图书编号写入借阅记录
            record.setBookname(book.getName());//把借阅的图书名字写入借阅记录
            record.setBorrower(borrower);//把借阅人写入借阅记录
            record.setBorrowTime(borrowtime);//把借阅的图书图书的时间写入借阅记录

            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");//指定时间日期格式
            String remandTime = simpleDateFormat.format(new Date());//把当前的还书确认时间添加到借阅记录中
            record.setRemandTime(remandTime);

            recordService.addRecord(record);
        }
        return num>0;
    }
}
