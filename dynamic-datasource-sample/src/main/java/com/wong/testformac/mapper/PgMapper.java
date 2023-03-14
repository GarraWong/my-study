package com.wong.testformac.mapper;

import com.wong.testformac.domain.Name;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/9/29 12:04
 */
@Mapper
public interface PgMapper {

    List<Name> selectList();

    int insertName(Name name);

    int deleteName(int id);
}
