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
import com.service.excel.TbUserExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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

    @Override
    public BaseRespDto index(IndexDto dto) {
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(5);
        TbUsers tbUsers = tbUsersMapper.selectByPrimaryKey(3);
        return new BaseRespDto(tbUser.getUsername() + " & " + tbUsers.getUsername());
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
