package com.wong.mapper;

import com.wong.domain.TjDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code code in MacBook pro 2020 Silicon
 * @date : 2022/12/29 11:58
 */
@Mapper
public interface Tjmapper {


    int insertTj(List<TjDomain> tjDomain);


    List<TjDomain> selectAll();
}
