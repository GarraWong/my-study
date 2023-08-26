package com.wong.mapper;

import com.wong.domain.Dept;
import org.apache.ibatis.annotations.Mapper;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2023/8/3 21:11
 */
@Mapper
public interface MyInsertMapper {

    int insertOne(Dept dept);

    int insertTwo(Dept dept);

}
