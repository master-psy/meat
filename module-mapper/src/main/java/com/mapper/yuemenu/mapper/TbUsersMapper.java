package com.mapper.yuemenu.mapper;

import com.mapper.yuemenu.entity.TbUsers;
import com.mapper.yuemenu.entity.TbUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TbUsersMapper {
    long countByExample(TbUsersExample example);

    int deleteByExample(TbUsersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbUsers record);

    int insertSelective(TbUsers record);

    List<TbUsers> selectByExample(TbUsersExample example);

    TbUsers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbUsers record, @Param("example") TbUsersExample example);

    int updateByExample(@Param("record") TbUsers record, @Param("example") TbUsersExample example);

    int updateByPrimaryKeySelective(TbUsers record);

    int updateByPrimaryKey(TbUsers record);
}