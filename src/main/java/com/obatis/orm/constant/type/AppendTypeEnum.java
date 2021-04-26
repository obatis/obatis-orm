package com.obatis.orm.constant.type;

public enum AppendTypeEnum {

    /**
     * 表示连接类型为 and 默认
     */
    AND(" and "),
    /**
     * 表示连接类型为 or
     */
    OR (" or ");

    private String joinTypeName;

    AppendTypeEnum(String joinTypeName) {
        this.joinTypeName = joinTypeName;
    }

    public String getJoinTypeName() {
        return this.joinTypeName;
    }
}
