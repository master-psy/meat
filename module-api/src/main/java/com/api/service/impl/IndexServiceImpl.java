package com.api.service.impl;

import com.api.dto.IndexDto;
import com.api.service.IndexService;
import com.common.base.BaseRespDto;
import com.common.enumeration.ApiCodeEnum;
import com.common.exception.BizException;
import com.common.util.ExcelUtils;
import com.mapper.shanyou.entity.TbUser;
import com.mapper.shanyou.entity.TbUserExample;
import com.mapper.shanyou.mapper.TbUserMapper;
import com.mapper.yuemenu.entity.TbUsers;
import com.mapper.yuemenu.mapper.TbUsersMapper;
import com.redis.service.RedisService;
import com.service.excel.TbUserExcel;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc
 */
@Slf4j
@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private TbUsersMapper tbUsersMapper;
    @Autowired
    private RedisService redisService;

    @Override
    public BaseRespDto index(IndexDto dto) {
        String key = "test_key";
        redisService.setCacheObject(key, "哈哈哈");
        String reidsTest=redisService.getCacheObject(key);
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(5);
        TbUsers tbUsers = tbUsersMapper.selectByPrimaryKey(3);
        Map map=new HashMap<>();
        map.put("mysql",null!=tbUser?"success":"error");
        map.put("redis",StringUtils.isNotBlank(reidsTest)?"sueecss":"error");
        // return new BaseRespDto(tbUser.getUsername() + " & " + tbUsers.getUsername()+redisService.getCacheObject(key));
        return new BaseRespDto(map);
    }

    @Override
    public void exportUser(HttpServletResponse response) {
        TbUserExample example = new TbUserExample();
        example.createCriteria().andIsdeleteEqualTo(0);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        try {
            ExcelUtils.exportExcelToTarget(response, "全部用户", tbUsers, TbUserExcel.class);
        } catch (Exception e) {
            throw new BizException(ApiCodeEnum.FILE_EXPORT_FAILED);
        }
    }
}
