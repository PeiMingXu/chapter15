package com.xmut.mapper;

import com.github.pagehelper.Page;
import com.xmut.pojo.Book;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author
 * @date: 2023/4/26
 **/
public interface BookMapper {
    //图书推荐
    @Select("select * from book where book_status!='3' order by book_uploadtime desc")
    @Results(id ="bookMap",value = {
            @Result(id = true,property = "id",column = "book_id"),
            @Result(property = "name",column = "book_name"),
            @Result(property = "isbn",column = "book_isbn"),
            @Result(property = "press",column = "book_press"),
            @Result(property = "author",column = "book_author"),
            @Result(property = "pagination",column = "book_pagination"),
            @Result(property = "price",column = "book_price"),
            @Result(property = "uploadtime",column = "book_uploadtime"),
            @Result(property = "status",column = "book_status"),
            @Result(property = "borrower",column = "book_borrower"),
            @Result(property = "borrowtime",column = "book_borrowtime"),
            @Result(property = "returntime",column = "book_returntime")
    })
    public Page<Book> selectNewBooks();
    //根据id查询
    @Select("select * from book where book_id=#{id}")
    @ResultMap("bookMap")
    public Book findById(String id);

    //图书借阅
    public Integer borrowBook(Book book);

    //图书查询
    @Select("<script>\n" +
            "        select * from book\n" +
            "        <where>\n" +
            "            <if test=\"name!=null and name.trim()!=''\">\n" +
            "                and book_name like concat('%',#{name},'%')\n" +
            "            </if>\n" +
            "            <if test=\"author!=null and author.trim()!=''\">\n" +
            "                and book_author like concat('%',#{author},'%')\n" +
            "            </if>\n" +
            "            <if test=\"press!=null and press.trim()!=''\">\n" +
            "                and book_press like concat('%',#{press},'%')\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        order by book_status\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> searchBooks(Book book);

    //添加图书
    public Integer addBook(Book book);

    //修改图书
    public Integer editBook(Book book);

    //查询当前用户借阅的图书和所有用户归还中的
    @Select("<script>\n" +
            "        select * from book\n" +
            "        where book_borrower=#{borrower}\n" +
            "        and book_status in (1,2)\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "          and  book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and  book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and  book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> selectMyBorrowed(Book book);

    //查询当前用户借阅中的和当前用户归还中的
    @Select("<script>\n" +
            "        select * from book\n" +
            "        where book_borrower=#{borrower}\n" +
            "        and book_status =1\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "            and  book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and  book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and  book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        or book_status=2\n" +
            "        <if test=\"name!=null and name.trim()!=''\">\n" +
            "            and  book_name like concat('%',#{name},'%')\n" +
            "        </if>\n" +
            "        <if test=\"author!=null and author.trim()!=''\">\n" +
            "            and  book_author like concat('%',#{author},'%')\n" +
            "        </if>\n" +
            "        <if test=\"press!=null and press.trim()!=''\">\n" +
            "            and  book_press like concat('%',#{press},'%')\n" +
            "        </if>\n" +
            "        </script>")
    @ResultMap("bookMap")
    public Page<Book> selectBorrowed(Book book);
}
