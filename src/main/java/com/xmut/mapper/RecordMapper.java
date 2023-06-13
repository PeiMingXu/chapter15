package com.xmut.mapper;

import com.github.pagehelper.Page;
import com.xmut.pojo.Book;
import com.xmut.pojo.Record;
import org.apache.ibatis.annotations.*;

/**
 * @author
 * @date: 2023/4/29
 **/
public interface RecordMapper {

    //根据借阅人和书名进行条件查询
    @Select(" <script>\n" +
            "        select * from record\n" +
            "        <where>\n" +
            "            <if test=\"bookname!=null and bookname.trim()!=''\">\n" +
            "                and record_bookname like concat('%',#{bookname},'%')\n" +
            "            </if>\n" +
            "            <if test=\"borrower!=null and borrower.trim()!=''\">\n" +
            "                and record_borrower like concat('%',#{borrower},'%')\n" +
            "            </if>\n" +
            "        </where>\n" +
            "        </script>")
    @Results(id = "recordMap",value = {
            @Result(id = true,column = "record_id",property = "id"),
            @Result(column = "record_remandTime",property = "remandTime"),
            @Result(column = "record_bookname",property = "bookname"),
            @Result(column = "record_bookisbn",property = "bookisbn"),
            @Result(column = "record_borrower",property = "borrower"),
            @Result(column = "record_borrowTime",property = "borrowTime"),

    })
    public Page<Record> searchRecords(Record record);

    //添加图书归还记录
    @Insert("insert into record values(#{id},#{bookname},#{bookisbn},#{borrower},#{borrowTime},#{remandTime})")
    @ResultMap("recordMap")
    public Integer addRecord(Record record);
}
