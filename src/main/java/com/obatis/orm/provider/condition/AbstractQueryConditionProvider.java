package com.obatis.orm.provider.condition;

import com.obatis.orm.constant.type.FilterEnum;

public interface AbstractQueryConditionProvider extends AbstractConditionProvider {

    /**
     * 分组，支持一次性传入多个分组字段
     * @param groupNames
     */
    AbstractQueryConditionProvider addGroup(String...groupNames);

    /**
     * 针对日期格式分组
     * @param groupName
     * @param pattern
     * @return
     */
    AbstractQueryConditionProvider addGroupDateFormat(String groupName, String pattern);

    /**
     * 常规的字段 having 判断，字段包括兼容表达式
     * @param fieldName
     * @param value
     */
    AbstractQueryConditionProvider addHaving(String fieldName, FilterEnum filterEnum, Number value);

    /**
     * 常规的字段 having 判断
     * @param fieldName
     * @param value
     */
    AbstractQueryConditionProvider addHavingGreaterThan(String fieldName, Number value);

    /**
     * count 聚合函数的 having 判断
     */
    AbstractQueryConditionProvider addHavingCountGreaterThan(String fieldName, Number value);
}
