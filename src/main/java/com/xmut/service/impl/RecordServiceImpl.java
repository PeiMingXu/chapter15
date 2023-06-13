package com.xmut.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xmut.entity.PageResult;
import com.xmut.mapper.BookMapper;
import com.xmut.mapper.RecordMapper;
import com.xmut.pojo.Record;
import com.xmut.pojo.User;
import com.xmut.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date: 2023/4/29
 **/
@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    //增加借阅记录
    @Override
    public boolean addRecord(Record record) {

        return recordMapper.addRecord(record)>0;
    }
    //根据借阅人和书名进行条件查询
    @Override
    public PageResult searchRecords(Record record, User user, Integer pageNum, Integer pageSize) {
        //开启分页
        PageHelper.startPage(pageNum,pageSize);

        //如果不是管理员，借阅人的设置成当前登陆的用户
        if (!"ADMIN".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page = recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
