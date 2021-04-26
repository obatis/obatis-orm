package com.obatis.orm.provider.condition.handle;

import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.AppendTypeEnum;
import com.obatis.orm.constant.type.FilterEnum;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.condition.AbstractConditionProvider;
import com.obatis.orm.provider.condition.ConditionProvider;
import com.obatis.tools.ValidateTool;

import java.util.ArrayList;
import java.util.List;

public class ConditionProviderHandle implements ConditionProvider {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 条件列表
     */
    private List<Object[]> filterArray;
    /**
     * 追加的代理句柄列表
     */
    private List<Object[]> providerArray;

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }

    /**
     * 添加查询条件，where后的字段;
     * 参数分别为字段名称，比如name。条件类型，比如=，具体的值参考QueryParam的FILTER开头的常量值
     * @param filterName
     * @param filterType
     * @param value
     */
    private void andFilter(String filterName, FilterEnum filterType, Object value) {
        this.addFilter(filterName, filterType, value, AppendTypeEnum.AND);
    }

    /**
     * 添加查询条件，带表达式格式
     * @param filterName
     * @param filterType
     * @param value
     * @param pattern
     */
    private void andFilter(String filterName, FilterEnum filterType, Object value, String pattern) {
        this.addFilter(filterName, filterType, value, AppendTypeEnum.AND, pattern);
    }

    /**
     * 设置条件
     * @param filterName
     * @param filterType
     * @param value
     * @param joinTypeEnum
     */
    private void addFilter(String filterName, FilterEnum filterType, Object value, AppendTypeEnum joinTypeEnum) {
        this.addFilter(filterName, filterType, value, joinTypeEnum, null);
    }

    /**
     * 添加 Filter 条件
     * @param filterName
     * @param filterType
     * @param value
     * @param joinTypeEnum
     * @param pattern
     */
    private void addFilter(String filterName, FilterEnum filterType, Object value, AppendTypeEnum joinTypeEnum, String pattern) {
        if (ValidateTool.isEmpty(filterName)) {
            throw new HandleException("error: filter field is null");
        } else if (!FilterEnum.IS_NULL.equals(filterType) && !FilterEnum.IS_NOT_NULL.equals(filterType) && value == null) {
            throw new HandleException("error: filter value<" + filterName + "> is null");
        }

        if (this.filterArray == null) {
            this.filterArray = new ArrayList<>();
        } else {
            this.checkFilter(this.filterArray, filterName, filterType, value, joinTypeEnum);
        }
        if(ValidateTool.isEmpty(pattern)) {
            Object[] obj = {filterName, filterType, value, joinTypeEnum};
            this.filterArray.add(obj);
        } else {
            Object[] obj = {filterName, filterType, value, joinTypeEnum, pattern};
            this.filterArray.add(obj);
        }
    }

    /**
     * 检测是否重复添加条件
     * @param filterList
     * @param filterName
     * @param filterType
     * @param value
     * @param joinTypeEnum
     */
    protected void checkFilter(List<Object[]> filterList, String filterName, FilterEnum filterType, Object value, AppendTypeEnum joinTypeEnum) {
        for (int i = 0, j = filterList.size(); i < j; i++) {
            Object[] filter = filterList.get(i);
            if(filter[0].toString().equals(filterName) && filterType.equals(filter[1]) && joinTypeEnum.equals(filter[3])) {
                if(FilterEnum.IS_NULL.equals(filterType) || FilterEnum.IS_NOT_NULL.equals(filterType)) {
                    filterList.remove(i);
                } else if (!value.equals(filter[2])) {
                    break;
                } else {
                    filterList.remove(i);
                }
            }
        }
    }

    /**
     * 设置or 查询条件数据
     * @param filterName
     * @param filterType
     * @param value
     */
    private void or(String filterName, FilterEnum filterType, Object value) {
        this.addFilter(filterName, filterType, value, AppendTypeEnum.OR);
    }

    /**
     * 设置or 查询条件数据，针对时间格式化
     * @param filterName
     * @param filterType
     * @param value
     */
    private void or(String filterName, FilterEnum filterType, Object value, String pattern) {
        this.addFilter(filterName, filterType, value, AppendTypeEnum.OR, pattern);
    }

    @Override
    public ConditionProvider equal(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider equalDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider equalField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orEqual(String filterName, Object value) {
        this.or(filterName, FilterEnum.EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orEqualDateFormat(String filterName, Object value, String pattern) {
        return null;
    }

    @Override
    public ConditionProvider orEqualField(String filterName, String fieldName) {
        return null;
    }

    @Override
    public ConditionProvider like(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider orLike(String filterName, Object value) {
        this.or(filterName, FilterEnum.LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider leftLike(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.LEFT_LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider orLeftLike(String filterName, Object value) {
        this.or(filterName, FilterEnum.LEFT_LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider rightLike(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.RIGHT_LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider orRightLike(String filterName, Object value) {
        this.or(filterName, FilterEnum.RIGHT_LIKE, value);
        return this;
    }

    @Override
    public ConditionProvider greaterThan(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider greaterThanDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.GREATER_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider greaterThanField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.GREATER_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orGreaterThan(String filterName, Object value) {
        this.or(filterName, FilterEnum.GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider orGreaterThanDateFormat(String filterName, Object value, String pattern) {
        this.or(filterName, FilterEnum.GREATER_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider orGreaterThanField(String filterName, String fieldName) {
        this.or(filterName, FilterEnum.GREATER_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider greaterEqual(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider greaterEqualDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, value);
        return this;
    }

    @Override
    public ConditionProvider greaterEqualField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.GREATER_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orGreaterEqual(String filterName, Object value) {
        this.or(filterName, FilterEnum.GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orGreaterEqualDateFormat(String filterName, Object value, String pattern) {
        this.or(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider orGreaterEqualField(String filterName, String fieldName) {
        this.or(filterName, FilterEnum.GREATER_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider greaterEqualZero(String filterName) {
        this.andFilter(filterName, FilterEnum.GREATER_EQUAL, 0);
        return this;
    }

    @Override
    public ConditionProvider greaterEqualZeroDateFormat(String filterName, String pattern) {
        this.andFilter(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, 0, pattern);
        return this;
    }

    @Override
    public ConditionProvider orGreaterEqualZero(String filterName) {
        this.or(filterName, FilterEnum.GREATER_EQUAL, 0);
        return this;
    }

    @Override
    public ConditionProvider orGreaterEqualZeroDateFormat(String filterName, String pattern) {
        this.or(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, 0, pattern);
        return this;
    }

    @Override
    public ConditionProvider lessThan(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.LESS_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider lessThanDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.LESS_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider lessThanField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.LESS_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orLessThan(String filterName, Object value) {
        this.or(filterName, FilterEnum.LESS_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider orLessThanDateFormat(String filterName, Object value, String pattern) {
        this.or(filterName, FilterEnum.LESS_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider orLessThanField(String filterName, String fieldName) {
        this.or(filterName, FilterEnum.LESS_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider lessEqual(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.LESS_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider lessEqualDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.LESS_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider lessEqualField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.LESS_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orLessEqual(String filterName, Object value) {
        this.or(filterName, FilterEnum.LESS_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orLessEqualDateFormat(String filterName, Object value, String pattern) {
        this.or(filterName, FilterEnum.LESS_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider orLessEqualField(String filterName, String fieldName) {
        this.or(filterName, FilterEnum.LESS_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider notEqual(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.NOT_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider notEqualDateFormat(String filterName, Object value, String pattern) {
        this.andFilter(filterName, FilterEnum.NOT_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider notEqualField(String filterName, String fieldName) {
        this.andFilter(filterName, FilterEnum.NOT_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider orNotEqual(String filterName, Object value) {
        this.or(filterName, FilterEnum.NOT_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orNotEqualDateFormat(String filterName, Object value, String pattern) {
        this.or(filterName, FilterEnum.NOT_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public ConditionProvider orNotEqualField(String filterName, String fieldName) {
        this.or(filterName, FilterEnum.NOT_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public ConditionProvider in(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public ConditionProvider in(String filterName, Object... value) {
        this.andFilter(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public ConditionProvider orIn(String filterName, Object value) {
        this.or(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public ConditionProvider orIn(String filterName, Object... value) {
        this.or(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public ConditionProvider notIn(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public ConditionProvider notIn(String filterName, Object... value) {
        this.andFilter(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public ConditionProvider orNotIn(String filterName, Object value) {
        this.or(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public ConditionProvider orNotIn(String filterName, Object... value) {
        this.or(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public ConditionProvider inProvider(String filterName, QueryProvider provider) {
        this.andFilter(filterName, FilterEnum.IN_PROVIDER, provider);
        return this;
    }

    @Override
    public ConditionProvider orInProvider(String filterName, QueryProvider provider) {
        this.or(filterName, FilterEnum.IN_PROVIDER, provider);
        return this;
    }

    @Override
    public ConditionProvider notInProvider(String filterName, QueryProvider provider) {
        this.andFilter(filterName, FilterEnum.NOT_IN_PROVIDER, provider);
        return this;
    }

    @Override
    public ConditionProvider orNotInProvider(String filterName, QueryProvider provider) {
        this.or(filterName, FilterEnum.NOT_IN_PROVIDER, provider);
        return this;
    }

    @Override
    public ConditionProvider isNull(String filterName) {
        this.andFilter(filterName, FilterEnum.IS_NULL, null);
        return this;
    }

    @Override
    public ConditionProvider orIsNull(String filterName) {
        this.or(filterName, FilterEnum.IS_NULL, null);
        return this;
    }

    @Override
    public ConditionProvider isNotNull(String filterName) {
        this.andFilter(filterName, FilterEnum.IS_NOT_NULL, null);
        return this;
    }

    @Override
    public ConditionProvider orIsNotNull(String filterName) {
        this.or(filterName, FilterEnum.IS_NOT_NULL, null);
        return this;
    }

    @Override
    public ConditionProvider upGreaterThanZero(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.UP_GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider orUpGreaterThanZero(String filterName, Object value) {
        this.or(filterName, FilterEnum.UP_GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider upGreaterEqualZero(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.UP_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orUpGreaterEqualZero(String filterName, Object value) {
        this.or(filterName, FilterEnum.UP_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider reduceGreaterThanZero(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.REDUCE_GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider orReduceGreaterThanZero(String filterName, Object value) {
        this.or(filterName, FilterEnum.REDUCE_GREATER_THAN, value);
        return this;
    }

    @Override
    public ConditionProvider reduceGreaterEqualZero(String filterName, Object value) {
        this.andFilter(filterName, FilterEnum.REDUCE_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider orReduceGreaterEqualZero(String filterName, Object value) {
        this.or(filterName, FilterEnum.REDUCE_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public ConditionProvider addProvider(QueryProvider queryProvider) {
        this.addProvider(queryProvider, AppendTypeEnum.AND);
        return this;
    }

    @Override
    public ConditionProvider addProvider(QueryProvider queryProvider, AppendTypeEnum joinTypeEnum) {
        if (queryProvider == null) {
            throw new HandleException("error: queryProvider is null");
        } else if (queryProvider == this) {
            throw new HandleException("error: queryProvider is same");
        }

        if (this.providerArray == null) {
            providerArray = new ArrayList<>();
        }

        Object[] obj = {queryProvider, joinTypeEnum};
        this.providerArray.add(obj);
        return this;
    }

    @Override
    public QueryProvider setLimit(int rows) {
        return null;
    }

    @Override
    public AbstractConditionProvider setFilters(Object obj) {
        return null;
    }

    @Override
    public AbstractConditionProvider reset() {
        return null;
    }
}
