package com.service.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * @Desc
 */
@Data
public class TbUserExcel {
    /**
     *
     */
    @Excel(name = "id")
    private Integer id;

    /**
     * 用户名
     */
    @Excel(name = "用户名")
    private String username;

    /**
     * 电话号码
     */
    @Excel(name = "电话号码")
    private Integer phone;


    /**
     * 加入时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "加入时间")
    private Date addtime;


}
