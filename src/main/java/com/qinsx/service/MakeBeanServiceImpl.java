package com.qinsx.service;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.qinsx.bean.Field;
import com.qinsx.bean.Templete;
import com.qinsx.enums.FieldType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shuxinqin
 * DATE : 18-3-20
 * TIME : 下午2:57
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.service.impl
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
@Service
public class MakeBeanServiceImpl {

    public Templete makeBeanByCreateTableString(String createTable) {

        Templete templete = new Templete();
        List<String> createTableList = Splitter.on("\n").trimResults().splitToList(createTable);

        Map<String, String> fieldTypeMap = new HashMap<String, String>();
        for (FieldType e : FieldType.values()) {
            fieldTypeMap.put(e.getSqlType(), e.getJavaType());
        }
        String title = getTitle(createTableList.get(0));
        String tableName = Splitter.on("`").splitToList(createTableList.get(0)).get(1);
        List<Field> fieldList = new ArrayList<Field>();
        // 提取字段
        for (String line : createTableList) {
            if (StringUtils.isEmpty(line)){
                continue;
            }
            Field field = new Field();
            String fieldName = getFieldName(line, field);
            String fieldType = getFieldType(line, fieldTypeMap);
            if (!Objects.equal(fieldName, null) && !Objects.equal(fieldType, null)) {
                field.setName(fieldName);
                field.setType(fieldType);
                field.setRemark(getFieldRemark(line));
                fieldList.add(field);
            }
        }
        templete.setModel(makeBean(title, fieldList, getFieldRemark(createTableList.get(createTableList.size() - 1))));
        templete.setMapper(makeMapper(title, getFieldRemark(createTableList.get(createTableList.size() - 1))));
        templete.setXml(makeXml(title, fieldList, tableName));

        return templete;
    }

    /**
     * 构造sql文件
     *
     * @param title
     * @param fieldList
     * @param tableName
     * @return
     */
    private String makeXml(String title, List<Field> fieldList, String tableName) {
        StringBuilder xml = new StringBuilder();
        xml.append("<!DOCTYPE mapper\n" + "\t\tPUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n"
                + "\t\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" + "<mapper namespace=\"xxx.xxx.xxx.xxx.")
                .append(title).append("Mapper").append("\">\n\n");
        xml.append("\t<sql id=\"baseSql\">\n");
        for (Field field : fieldList) {
            xml.append("\t\t").append(field.getColumn()).append(" as ").append(field.getName()).append("\n");
        }
        xml.append("\t</sql>\n");
        xml.append("\t<insert id=\"insert\">\n");
        xml.append("\t\tinsert into ").append(tableName).append("\n\t\t(\n");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            if (i > 0) {
                xml.append(" ,\n");
            }
            xml.append("\t\t\t").append(field.getColumn());
        }
        xml.append("\n\t\t) values (\n");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            if (i > 0) {
                xml.append(" ,\n");
            }
            xml.append("\t\t\t#{").append(field.getName()).append("}");
        }
        xml.append("\n\t\t)\n");
        xml.append("\t</insert>\n");
        xml.append("\t<insert id=\"batchInsert\">\n");
        xml.append("\t\tinsert into ").append(tableName).append("\n\t\t(\n");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            if (i > 0) {
                xml.append(" ,\n");
            }
            xml.append("\t\t\t").append(field.getColumn());
        }
        xml.append("\n\t\t) values \n");
        xml.append("\t\t<foreach collection=\"list\" item=\"item\" separator=\",\">\n");
        xml.append("\t\t(\n");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            if (i > 0) {
                xml.append(" ,\n");
            }
            xml.append("\t\t\t#{item.").append(field.getName()).append("}");
        }
        xml.append("\n\t\t)\n");
        xml.append("\t\t</foreach>\n");
        xml.append("\t</insert>\n");
        xml.append("\t<delete id=\"delete\" parameterType=\"integer\">\n");
        xml.append("\t\tdelete from ").append(tableName).append(" where id = #{id}\n");
        xml.append("\t</delete>\n");
        xml.append("\t<update id=\"update\" parameterType=\"xxx.xxx.xxx.xxx.").append(title).append("Model\">\n");
        xml.append("\t\tupdate ").append(tableName).append(" set\n");
        for (int i = 0; i < fieldList.size(); i++) {
            Field field = fieldList.get(i);
            if (field.getName().equals("id")) {
                continue;
            }
            xml.append("\t\t").append(field.getColumn()).append(" = #{").append(field.getName()).append("}");
            if (i < fieldList.size() - 1) {
                xml.append(" ,\n");
            }
        }
        xml.append("\n\t\twhere id = #{id}\n");
        xml.append("\t</update>\n");
        xml.append("\t<select id=\"selectAll\" resultType=\"xxx.xxx.xxx.xxx.").append(title).append("Model\">\n");
        xml.append("\tselect\n").append("\t<include refid=\"baseSql\" />\n\tfrom ").append(tableName);
        xml.append("\n\t</select>\n");
        return xml.toString();
    }

    /**
     * 构造Mapper接口
     *
     * @param title
     * @param titleRemark
     * @return
     */
    private String makeMapper(String title, String titleRemark){
        StringBuilder mapper = new StringBuilder();
        mapper.append(makeRemark(titleRemark)).append("@Mapper\n").append("public interface ").append(title).append("Mapper").append(" {\n\n");
        mapper.append(makeMethodRemark("保存", Lists.newArrayList("item"), null));
        mapper.append("\tvoid insert(@Param(\"item\")").append(title).append("Model").append(" item);\n");
        mapper.append(makeMethodRemark("批量保存", Lists.newArrayList("list"), null));
        mapper.append("\tvoid batchInsert(@Param(\"list\")List<").append(title).append("Model>").append(" list);\n");
        mapper.append(makeMethodRemark("根据id删除", Lists.newArrayList("id"), null));
        mapper.append("\tvoid delete(@Param(\"id\")Integer id);\n");
        mapper.append(makeMethodRemark("根据id修改", Lists.newArrayList("item"), null));
        mapper.append("\tvoid update(@Param(\"item\")").append(title).append("Model").append(" item);\n");
        mapper.append(makeMethodRemark("查询", Lists.newArrayList(), null));
        mapper.append("\tList<").append(title).append("Model> ").append("selectAll();\n");
        return mapper.append("}").toString();
    }

    /**
     * 接口方法备注
     *
     * @param remark
     * @param params
     * @param returnName
     * @return
     */
    private String makeMethodRemark(String remark, List<String> params, String returnName){
        StringBuilder msg = new StringBuilder();
        msg.append("\t/**\n\t * ").append(remark).append("\n\t *\n");
        for (String param : params){
            msg.append("\t * @param ").append(param).append("\n");
        }
        if (StringUtils.isEmpty(returnName)){
            msg.append("\t */\n");
        } else {
            msg.append("\t * @return ").append(returnName).append("\n\t */\n");
        }
        return msg.toString();
    }

    private String makeBean(String title, List<Field> fieldList, String titleRemark) {
        StringBuilder bean = new StringBuilder();
        bean.append(makeRemark(titleRemark)).append("@Data\n").append("public class ").append(title).append("Model").append(" {\n");
        for (Field field : fieldList) {
            bean.append("\t/**\n\t * ").append(field.getRemark()).append("\n\t */\n");
            bean.append("\tprivate ").append(field.getType()).append(" ").append(field.getName()).append(";\n");
        }
        return bean.append("}").toString();
    }

    private String makeRemark(String remark) {
        return ("/**\n * ").concat(remark).concat("\n */\n");
    }

    /**
     * 获取表名的驼峰式 第一个字母打斜
     *
     * @param line
     * @return
     */
    private String getTitle(String line) {
        char[] title = line.toCharArray();
        int flag = 0;
        StringBuilder bean = new StringBuilder();
        for (int i = 0; i < title.length; i++) {
            if (flag == 0) {
                if (title[i] == '`') {
                    flag = 1;
                }
            } else if (flag == 1) {
                bean.append((char) (title[i] - 32));
                flag = 2;
            } else if (flag == 2) {
                if (title[i] == '`') {
                    flag = 3;
                } else if (title[i] == '_') {
                    flag = 1;
                } else {
                    bean.append(title[i]);
                }
            }
        }
        return bean.toString();
    }

    /**
     * 获取字段名的驼峰式 第一个字母小写
     *
     * @param line
     * @param field
     * @return
     */
    private String getFieldName(String line, Field field) {
        if (line.length() <= 0 || line.charAt(0) != '`') {
            return null;
        }
        char[] title = line.toCharArray();
        int flag = 0;
        StringBuilder bean = new StringBuilder();
        StringBuilder column = new StringBuilder();
        for (int i = 0; i < title.length; i++) {
            if (flag == 0) {
                if (title[i] == '`') {
                    flag = 2;
                }
            } else if (flag == 1) {
                bean.append((char) (title[i] - 32));
                column.append(title[i]);
                flag = 2;
            } else if (flag == 2) {
                if (title[i] == '`') {
                    flag = 3;
                } else if (title[i] == '_') {
                    column.append('_');
                    flag = 1;
                } else {
                    bean.append(title[i]);
                    column.append(title[i]);
                }
            }
        }
        field.setColumn(column.toString());
        return bean.toString();
    }

    /**
     * 获取字段类型
     *
     * @param line
     * @param fieldTypeMap
     * @return
     */
    private String getFieldType(String line, Map<String, String> fieldTypeMap) {
        List<String> itemList = Splitter.on(" ").trimResults().splitToList(line);
        String sqlType = Splitter.on("(").trimResults().splitToList(itemList.get(1)).get(0);

        return fieldTypeMap.get(sqlType);
    }

    /**
     * 获取字段备注
     *
     * @param line
     * @return
     */
    private String getFieldRemark(String line) {
        String remark;
        try {
            List<String> itemList = Splitter.on("'").trimResults().splitToList(line);
            remark = itemList.get(itemList.size() - 2);
        } catch (Exception e) {
            remark = "";
        }
        return remark;
    }
}
