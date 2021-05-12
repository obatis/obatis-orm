package com.obatis.orm.provider.condition.handle;

import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.FilterEnum;
import com.obatis.orm.constant.type.SqlHandleEnum;
import com.obatis.orm.provider.condition.AbstractQueryConditionProvider;
import com.obatis.tools.ValidateTool;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbstractQueryConditionProviderHandle extends AbstractConditionProviderHandle implements AbstractQueryConditionProvider {

    /**
     * 分组列表
     */
    private List<Object[]> groupArray;
    /**
     * having 条件表达式列表
     */
    private List<Object[]> havingArray;

    public List<Object[]> getGroupArray() {
        return groupArray;
    }

    public List<Object[]> getHavingArray() {
        return havingArray;
    }

    /**
     * 设置分组字段
     * @param column
     * @param sqlHandleEnum
     * @param pattern
     */
    private void setGroup(String column, SqlHandleEnum sqlHandleEnum, String pattern) {
        if (ValidateTool.isEmpty(column)) {
            throw new HandleException("error: group<" + column + "> field is null");
        }

        /**
         *  判断传入的分组字段是否包含特殊字符
         */
        String regExp = "[~!/@$%^&*()=+\\|[{}];:\'\",<>/?]+";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(column);
        if(m.find()) {
            throw new HandleException("error: group field is invalid");
        }

        if (this.groupArray == null) {
            this.groupArray = new ArrayList<>();
        }
        Object[] group = {column, sqlHandleEnum, pattern};
        this.groupArray.add(group);
    }

    @Override
    public AbstractQueryConditionProvider addGroup(String... columns) {
        for(String column : columns) {
            this.setGroup(column, SqlHandleEnum.HANDLE_DEFAULT, null);
        }
        return this;
    }

    @Override
    public AbstractQueryConditionProvider addGroupDateFormat(String groupName, String pattern) {
        if (ValidateTool.isEmpty(pattern)) {
            throw new HandleException("error: pattern<" + groupName + "> is null");
        }
        this.setGroup(groupName, SqlHandleEnum.HANDLE_DATE_FORMAT, pattern);
        return this;
    }

    @Override
    public AbstractQueryConditionProvider addHaving(String fieldName, FilterEnum filterEnum, Number value) {
        this.addHaving(fieldName, SqlHandleEnum.HANDLE_DEFAULT, filterEnum, value);
        return this;
    }

    /**
     * 设置 having 条件表达式
     * @param fieldName
     * @param sqlHandleEnum
     * @param filterEnum
     * @param value
     */
    private void addHaving(String fieldName, SqlHandleEnum sqlHandleEnum, FilterEnum filterEnum, Number value) {
        if (ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: having field is null");
        }
        if(value == null) {
            throw new HandleException("error: having field value is null");
        }
        if(this.havingArray == null) {
            this.havingArray = new ArrayList<>();
        }

        Object[] having = {fieldName, sqlHandleEnum, filterEnum, value};
        this.havingArray.add(having);
    }

    @Override
    public AbstractQueryConditionProvider addHavingGreaterThan(String fieldName, Number value) {
        this.addHaving(fieldName, SqlHandleEnum.HANDLE_DEFAULT, FilterEnum.GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractQueryConditionProvider addHavingCountGreaterThan(String fieldName, Number value) {
        this.addHaving(fieldName, SqlHandleEnum.HANDLE_COUNT, FilterEnum.GREATER_THAN, value);
        return this;
    }

    /**
     * 移除所有属性，方便对象复用，但是要确保对象已经被消费
     * @return
     */
    @Override
    public AbstractQueryConditionProvider reset() {
        super.reset();
        if(groupArray != null && !groupArray.isEmpty()) {
            groupArray.clear();
        }
        if(havingArray != null && !havingArray.isEmpty()) {
            havingArray.clear();
        }
        return this;
    }
}
