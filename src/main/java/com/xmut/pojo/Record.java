package com.xmut.pojo;

/**
 * @author
 * @date: 2023/4/29
 **/
public class Record {
    private Integer id;  //图书借阅id
    private String bookname;//借阅的图书名称
    private String bookisbn;//借阅的图书编号
    private String borrower;//图书借阅人
    private String borrowTime;///图书借阅时间borrowTime
    private String remandTime;//图书归还时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getBookisbn() {
        return bookisbn;
    }

    public void setBookisbn(String bookisbn) {
        this.bookisbn = bookisbn;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getRemandTime() {
        return remandTime;
    }

    public void setRemandTime(String remandTime) {
        this.remandTime = remandTime;
    }
}
