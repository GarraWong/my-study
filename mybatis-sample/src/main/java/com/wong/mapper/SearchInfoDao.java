package com.wong.mapper;

import com.wong.domain.SearchInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 体检表(SearchInfo)表数据库访问层
 *
 * @author makejava
 * @since 2023-02-25 12:42:12
 */
@Mapper
public interface SearchInfoDao {


    List<SearchInfo> queryByCardIds(@Param("ids") List<String> cardids);

    List<SearchInfo> queryAllDataWithDeal();


    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SearchInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SearchInfo> entities);
    int insertBatch2(@Param("entities") List<SearchInfo> entities);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 根据 姓名，年龄，性别，门诊号group by 得到
     *
     * @return
     */
    List<SearchInfo> queryGroupBy();

    // @Select("SELECT name,age,cardid,MAX(intime) as intime FROM search_info WHERE cardid = '80284611' or cardid = '80473623' GROUP BY cardid,name,age")
    List<SearchInfo> queryIntimeInSameName(@Param("cardids") List<String> cardids);


}

