package com.wong.testformac.service;

import cn.hutool.core.util.RandomUtil;
import com.wong.testformac.datasource.DynamicDataSourceContextHolder;
import com.wong.testformac.domain.Name;
import com.wong.testformac.enums.DataSourceType;
import com.wong.testformac.mapper.MysqlMapper;
import com.wong.testformac.mapper.PgMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 这是类的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/9/29 12:14
 */
@Service
public class TransactionTestImpl implements TransactionTestService {

    @Autowired
    private PgMapper pgMapper;

    @Autowired
    private MysqlMapper mysqlMapper;

    private static final Logger logger = LoggerFactory.getLogger(TransactionTestImpl.class);

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFail() {
        Name name = new Name();
        name.setName("一号");
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MYSQL.name());
        mysqlMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.KINGBASE.name());
        pgMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();
        int i = 1 / 0;
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSuccess() {
        Name name = new Name();
        name.setId(RandomUtil.randomInt());
        name.setName("成功测试");
        logger.info("开始=====");
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MYSQL.name());
        mysqlMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();

        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.KINGBASE.name());
        pgMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();
        logger.info("结束=====");
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFailOfSqlException() {
        Name name = new Name();
        name.setName("一号");
        name.setId(1);
        Name name2 = new Name();
        name2.setName("二号");
        name2.setId(1);
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MYSQL.name());
        mysqlMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.KINGBASE.name());
        pgMapper.insertName(name);
        DynamicDataSourceContextHolder.clearDataSourceType();
        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.MYSQL.name());
        mysqlMapper.insertName(name2);
        DynamicDataSourceContextHolder.clearDataSourceType();
        int i = 1 / 0;
        return i;
    }

}
