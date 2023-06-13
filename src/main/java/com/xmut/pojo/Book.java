package com.xmut.pojo;

import java.io.Serializable;

/**
 * @author
 * @date: 2023/4/26
 **/
public class Book implements Serializable {//实现Serializable,适用于一些第三方框架
    private Integer id;//图书编号
    private String name;//图书名称
    private String isbn;    //图书标准编号ISBN
    private String press;
    private String author; //图书作者
    private Integer pagination;//图书页数
    private Double price; //图书价格
    private String uploadtime;//上架时间
    private String status;//图书状态(0:可借阅，1：已借阅,2.归还中,3.已下架)
    private String borrower;//借阅人
    private String borrowtime;//借阅时间
    private String returntime;//图书预计归还时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPagination() {
        return pagination;
    }

    public void setPagination(Integer pagination) {
        this.pagination = pagination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrowtime() {
        return borrowtime;
    }

    public void setBorrowtime(String borrowtime) {
        this.borrowtime = borrowtime;
    }

    public String getReturntime() {
        return returntime;
    }

    public void setReturntime(String returntime) {
        this.returntime = returntime;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isbn='" + isbn + '\'' +
                ", press='" + press + '\'' +
                ", author='" + author + '\'' +
                ", pagination=" + pagination +
                ", price=" + price +
                ", uploadtime='" + uploadtime + '\'' +
                ", status='" + status + '\'' +
                ", borrower='" + borrower + '\'' +
                ", borrowtime='" + borrowtime + '\'' +
                ", returntime='" + returntime + '\'' +
                '}';
    }
}
