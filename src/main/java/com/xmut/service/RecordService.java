package com.xmut.service;

import com.xmut.entity.PageResult;
import com.xmut.mapper.RecordMapper;
import com.xmut.pojo.Record;
import com.xmut.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date: 2023/4/29
 **/

public interface RecordService {

    //增加图书借阅记录
    public boolean addRecord(Record record);

    //根据借阅人和书名进行条件查询
    public PageResult searchRecords(Record record, User user,Integer pageNum,Integer pageSize);
}
