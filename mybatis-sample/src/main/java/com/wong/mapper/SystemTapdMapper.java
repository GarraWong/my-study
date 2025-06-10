package com.wong.mapper;

import com.wong.po.devinsight.database.SystemDemandDto;
import com.wong.po.devinsight.database.SystemTaskDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 这是接口的描述 补充它
 *
 * @author : Wym's Code coding MacBook pro 2020 Silicon
 * @date : 2025/6/10 13:09
 */
@Mapper
public interface SystemTapdMapper {

    List<SystemTaskDto> selectAllSystemTask();
    List<SystemDemandDto> selectAllSystemDemand();
}
