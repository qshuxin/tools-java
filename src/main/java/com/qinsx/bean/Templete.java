package com.qinsx.bean;

import lombok.Data;

/**
 * Created by qinsx
 * DATE : 2019-07-09
 * TIME : 17:11
 * PROJECT : tools2.0
 * PACKAGE : com.qinsx.bean
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Data
public class Templete {

    /**
     * 实体类
     */
    private String model;
    /**
     * mapper接口
     */
    private String mapper;
    /**
     * sql
     */
    private String xml;
}
