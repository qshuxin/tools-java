package com.qinsx.bean;

import lombok.Data;

/**
 * Created by shuxinqin
 * DATE : 18-3-20
 * TIME : 下午4:44
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.bean
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Data
public class Field {

    /**
     * 备注
     */
    private String remark;

    /**
     * 实体类字段名
     */
    private String name;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 数据库表字段名
     */
    private String column;
}
