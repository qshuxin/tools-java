package com.qinsx.enums;

/**
 * Created by shuxinqin
 * DATE : 18-3-20
 * TIME : 下午5:00
 * PROJECT : convenienceTools
 * PACKAGE : com.qsx.autoTools.enums
 *
 * @author <a href="mailto:shuxin.qin@qunar.com">shuxin.qin</a>
 */
public enum FieldType {
    BIGINT("bigint", "Integer"),
    BINARY("binary", "String"),
    BIT("bit", "String"),
    BLOB("blob", "String"),
    CHAR("char", "String"),
    DATE("date", "Date"),
    DATETIME("datetime", "Date"),
    DECIMAL("decimal", "BigDecimal"),
    DOUBLE("double", "BigDecimal"),
    ENUM("enum", "String"),
    FLOAT("float", "BigDecimal"),
    INT("int", "Integer"),
    INTEGER("integer", "Integer"),
    LONGBLOB("longblob", "String"),
    LONGTEXT("longtext", "String"),
    MEDIUMBLOB("mediumblob", "String"),
    MEDIUMINT("mediumint", "Integer"),
    MEDIUMTEXT("mediumtext", "String"),
    MULTILINESTRING("multilinestring", "String"),
    SMALLINT("smallint", "Integer"),
    TEXT("text", "String"),
    TIME("time", "Date"),
    TIMESTAMP("timestamp", "Date"),
    TINYBLOB("tinyblob", "String"),
    TINYINT("tinyint", "Integer"),
    TINYTEXT("tinytext", "String"),
    VARCHAR("varchar", "String"),
    YEAR("year", "Date")
    ;

    private String sqlType;
    private String javaType;

    FieldType(String sqlType, String javaType) {
        this.sqlType = sqlType;
        this.javaType = javaType;
    }

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
