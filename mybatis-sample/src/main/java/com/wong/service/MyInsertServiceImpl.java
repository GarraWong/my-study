package com.wong.service;

import com.wong.domain.Dept;
import com.wong.mapper.MyInsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/3 21:32
 */
@Service
public class MyInsertServiceImpl implements MyInsertService{
    @Autowired
    MyInsertMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert() {
        Dept dept = new Dept();
        dept.setDept_name("测试1");
        Dept dept2 = new Dept();
        dept2.setDept_name("测试2");
        dept2.setId(1);

        int i = mapper.insertOne(dept);

        mapper.insertTwo(dept2);
    }
}
