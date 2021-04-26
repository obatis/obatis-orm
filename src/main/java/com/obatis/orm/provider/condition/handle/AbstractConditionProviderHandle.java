package com.obatis.orm.provider.condition.handle;

import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.FilterEnum;
import com.obatis.orm.constant.type.AppendTypeEnum;
import com.obatis.orm.constant.type.OrderEnum;
import com.obatis.orm.constant.type.SqlHandleEnum;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.condition.AbstractConditionProvider;
import com.obatis.orm.sql.mysql.HandleOrderMethod;
import com.obatis.tools.ValidateTool;

import java.util.ArrayList;
import java.util.List;

public class AbstractConditionProviderHandle extends ConditionProviderHandle implements AbstractConditionProvider {

    /**
     * 字段列表
     */
    private List<Object[]> columnArray;
    /**
     * 连接查询 on 条件列表
     */
    private List<Object[]> onFilterArray;
    /**
     * 连接查询 on 追加的代理句柄列表
     */
    private List<Object[]> onProviderArray;
    /**
     * 连接查询代理句柄列表
     */
    private List<Object[]> leftJoinProviderArray;
    /**
     * 排序列表
     */
    private List<Object[]> orderArray;

    /**
     * 排序抽象类
     */
    protected static AbstractOrder abstractOrder;

    /**
     * 添加字段
     * @param fieldName
     * @param fieldType
     * @param value
     */
    public void addColumn(String fieldName, SqlHandleEnum fieldType, Object value) {
        this.addColumn(fieldName, fieldType, value, null);
    }

    /**
     * 添加字段
     * @param fieldName
     * @param fieldType
     * @param value
     * @param pattern
     */
    public void addColumn(String fieldName, SqlHandleEnum fieldType, Object value, String pattern) {
        if (this.columnArray == null) {
            this.columnArray = new ArrayList<>();
        }
        if(ValidateTool.isEmpty(pattern)) {
            Object[] obj = { fieldName, fieldType, value };
            this.columnArray.add(obj);
        } else {
            Object[] obj = { fieldName, fieldType, value, pattern};
            this.columnArray.add(obj);
        }
    }

    /**
     * 获取字段列表
     * @return
     */
    public List<Object[]> getColumnArray() {
        return this.columnArray;
    }

    /**
     * 体现为 left join on 的连接查询条件
     * 参数分别为字段名称，比如name。条件类型，比如=，具体的值参考QueryParam的FILTER开头的常量值
     * @param filterName
     * @param filterType
     * @param value
     */
    private void andOnFilter(String filterName, FilterEnum filterType, Object value) {
        this.addOnFilter(filterName, filterType, value, AppendTypeEnum.AND);
    }

    /**
     * 体现为 left join on 的连接查询条件
     * 参数分别为字段名称，比如name。条件类型，比如=，具体的值参考QueryParam的FILTER开头的常量值
     * @param filterName
     * @param filterType
     * @param value
     */
    private void andOnFilter(String filterName, FilterEnum filterType, Object value, String pattern) {
        this.addOnFilter(filterName, filterType, value, AppendTypeEnum.AND, pattern);
    }

    /**
     * 体现为 left join on 的连接查询条件
     * 设置查询条件，可以传入定义的类型
     * @param filterName
     * @param filterType
     * @param value
     * @param joinTypeEnum
     */
    private void addOnFilter(String filterName, FilterEnum filterType, Object value, AppendTypeEnum joinTypeEnum) {
        this.addOnFilter(filterName, filterType, value, joinTypeEnum, null);
    }

    private void addOnFilter(String filterName, FilterEnum filterType, Object value, AppendTypeEnum joinTypeEnum, String pattern) {
        if (ValidateTool.isEmpty(filterName)) {
            throw new HandleException("error: on filter field is null");
        } else if (!FilterEnum.IS_NULL.equals(filterType) && !FilterEnum.IS_NOT_NULL.equals(filterType) && null == value) {
            throw new HandleException("error: on filter value<" + filterName + "> is null");
        }
        if (this.onFilterArray == null) {
            this.onFilterArray = new ArrayList<>();
        } else {
            this.checkFilter(this.onFilterArray, filterName, filterType, value, joinTypeEnum);
        }

        if(ValidateTool.isEmpty(pattern)) {
            Object[] obj = {filterName, filterType, value, joinTypeEnum};
            this.onFilterArray.add(obj);
        } else {
            Object[] obj = {filterName, filterType, value, joinTypeEnum, pattern};
            this.onFilterArray.add(obj);
        }
    }

    /**
     * 设置连接查询 on 拼接的 or 条件
     * @param filterName
     * @param filterType
     * @param value
     */
    private void onOr(String filterName, FilterEnum filterType, Object value) {
        this.addOnFilter(filterName, filterType, value, AppendTypeEnum.OR);
    }

    private void onOr(String filterName, FilterEnum filterType, Object value, String pattern) {
        this.addOnFilter(filterName, filterType, value, AppendTypeEnum.OR, pattern);
    }

    @Override
    public AbstractConditionProvider onEqual(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrEqual(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onEqualDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrEqualDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onEqualField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrEqualField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onLike(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLike(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onLeftLike(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.LEFT_LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLeftLike(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.LEFT_LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onRightLike(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.RIGHT_LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrRightLike(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.RIGHT_LIKE, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterThan(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterThanDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.GREATER_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterThanField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.GREATER_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterThan(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterThanDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.GREATER_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterThanField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.GREATER_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterEqual(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterEqualDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterEqualField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.GREATER_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterEqual(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterEqualDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterEqualField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.GREATER_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterEqualZero(String filterName) {
        this.andOnFilter(filterName, FilterEnum.GREATER_EQUAL, 0);
        return this;
    }

    @Override
    public AbstractConditionProvider onGreaterEqualZeroDateFormat(String filterName, String pattern) {
        this.andOnFilter(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, 0, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrGreaterEqualZero(String filterName) {
        this.onOr(filterName, FilterEnum.GREATER_EQUAL, 0);
        return this;
    }

    /**
     * 连接查询 or 大于等于0的条件表达式，传入字段名称即可,针对时间格式化查询条件
     * @param filterName
     * @param pattern
     * @return
     */
    @Override
    public AbstractConditionProvider onOrGreaterEqualZeroDateFormat(String filterName, String pattern) {
        this.onOr(filterName, FilterEnum.GREATER_EQUAL_DATE_FORMAT, 0, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessThan(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.LESS_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessThanDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.LESS_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessThanField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.LESS_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessThan(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.LESS_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessThanDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.LESS_THAN_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessThanField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.LESS_THAN_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessEqual(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.LESS_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessEqualDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.LESS_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onLessEqualField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.LESS_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessEqual(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.LESS_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessEqualDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.LESS_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrLessEqualField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.LESS_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotEqual(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.NOT_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotEqualDateFormat(String filterName, Object value, String pattern) {
        this.andOnFilter(filterName, FilterEnum.NOT_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotEqualField(String filterName, String fieldName) {
        this.andOnFilter(filterName, FilterEnum.NOT_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotEqual(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.NOT_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotEqualDateFormat(String filterName, Object value, String pattern) {
        this.onOr(filterName, FilterEnum.NOT_EQUAL_DATE_FORMAT, value, pattern);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotEqualField(String filterName, String fieldName) {
        this.onOr(filterName, FilterEnum.NOT_EQUAL_FIELD, fieldName);
        return this;
    }

    @Override
    public AbstractConditionProvider onIn(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onIn(String filterName, Object... value) {
        this.andOnFilter(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrIn(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrIn(String filterName, Object... value) {
        this.onOr(filterName, FilterEnum.IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotIn(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotIn(String filterName, Object... value) {
        this.andOnFilter(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotIn(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotIn(String filterName, Object... value) {
        this.onOr(filterName, FilterEnum.NOT_IN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onInProvider(String filterName, QueryProvider provider) {
        this.andOnFilter(filterName, FilterEnum.IN_PROVIDER, provider);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrInProvider(String filterName, QueryProvider provider) {
        this.onOr(filterName, FilterEnum.IN_PROVIDER, provider);
        return this;
    }

    @Override
    public AbstractConditionProvider onNotInProvider(String filterName, QueryProvider provider) {
        this.andOnFilter(filterName, FilterEnum.NOT_IN_PROVIDER, provider);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrNotInProvider(String filterName, QueryProvider provider) {
        this.onOr(filterName, FilterEnum.NOT_IN_PROVIDER, provider);
        return this;
    }

    @Override
    public AbstractConditionProvider onIsNull(String filterName) {
        this.andOnFilter(filterName, FilterEnum.IS_NULL, null);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrIsNull(String filterName) {
        this.onOr(filterName, FilterEnum.IS_NULL, null);
        return this;
    }

    @Override
    public AbstractConditionProvider onIsNotNull(String filterName) {
        this.andOnFilter(filterName, FilterEnum.IS_NOT_NULL, null);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrIsNotNull(String filterName) {
        this.onOr(filterName, FilterEnum.IS_NOT_NULL, null);
        return this;
    }

    @Override
    public AbstractConditionProvider onUpGreaterThanZero(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.UP_GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrUpGreaterThanZero(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.UP_GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onUpGreaterEqualZero(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.UP_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrUpGreaterEqualZero(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.UP_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onReduceGreaterThanZero(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.REDUCE_GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrReduceGreaterThanZero(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.REDUCE_GREATER_THAN, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onReduceGreaterEqualZero(String filterName, Object value) {
        this.andOnFilter(filterName, FilterEnum.REDUCE_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider onOrReduceGreaterEqualZero(String filterName, Object value) {
        this.onOr(filterName, FilterEnum.REDUCE_GREATER_EQUAL, value);
        return this;
    }

    @Override
    public AbstractConditionProvider addOnProvider(QueryProvider queryProvider) {
        this.addOnProvider(queryProvider, AppendTypeEnum.AND);
        return this;
    }

    @Override
    public AbstractConditionProvider addOnProvider(QueryProvider queryProvider, AppendTypeEnum joinTypeEnum) {
        if (queryProvider == null) {
            throw new HandleException("error: queryProvider is null");
        } else if (queryProvider == this) {
            throw new HandleException("error: queryProvider is same");
        }

        if (this.onProviderArray == null) {
            onProviderArray = new ArrayList<>();
        }

        Object[] obj = {queryProvider, joinTypeEnum};
        this.onProviderArray.add(obj);
        return this;
    }

    @Override
    public AbstractConditionProvider addleftJoin(String fieldName, String paramFieldName, QueryProvider queryProvider) {
        if (fieldName == null) {
            throw new HandleException("error: left join fieldName is null");
        }
        if (paramFieldName == null) {
            throw new HandleException("error: left join paramFieldName is null");
        }
        if (queryProvider == null) {
            throw new HandleException("error: queryProvider can't null");
        }
        if(queryProvider == this) {
            throw new HandleException("error: queryProvider is same");
        }

        if (this.leftJoinProviderArray == null) {
            leftJoinProviderArray = new ArrayList<>();
        }

        Object[] obj = { fieldName, paramFieldName, queryProvider };
        this.leftJoinProviderArray.add(obj);
        return this;
    }

    @Override
    public AbstractConditionProvider addleftJoin(String[] fieldName, String[] paramFieldName, QueryProvider queryProvider) {
        int fieldLength = 0;
        if (fieldName == null || (fieldLength = fieldName.length) == 0) {
            throw new HandleException("error: left join fieldName is null");
        }
        int paramFieldLength = 0;
        if (paramFieldName == null || (paramFieldLength = paramFieldName.length) == 0) {
            throw new HandleException("error: left join paramFieldName is null");
        }
        if (fieldLength != paramFieldLength) {
            throw new HandleException("error: left join 'on' filter length must be equal");
        }
        if (queryProvider == null) {
            throw new HandleException("error: queryProvider is null");
        }
        if(queryProvider == this) {
            throw new HandleException("error: queryProvider is same");
        }

        if (this.leftJoinProviderArray == null) {
            leftJoinProviderArray = new ArrayList<>();
        }

        Object[] obj = { fieldName, paramFieldName, queryProvider };
        this.leftJoinProviderArray.add(obj);
        return this;
    }

    @Override
    public AbstractConditionProvider addOrder(String orderName, OrderEnum orderType) {
        this.setOrder(orderName, orderType, SqlHandleEnum.HANDLE_DEFAULT);
        return this;
    }

    /**
     * 排序，参数分别为排序字段，排序值，排序值类型参考 QueryParam 中 ORDER 开头的常量
     * @param orderName
     * @param orderType
     */
    private void setOrder(String orderName, OrderEnum orderType, SqlHandleEnum sqlHandleEnum) {
        if (ValidateTool.isEmpty(orderName) && !sqlHandleEnum.equals(SqlHandleEnum.HANDLE_COUNT)) {
            throw new HandleException("error: order field<" + orderName + "> is null ！！！");
        }

        if (this.orderArray == null) {
            this.orderArray = new ArrayList<>();
        }

        if(abstractOrder == null) {
            abstractOrder = new HandleOrderMethod();
        }
        abstractOrder.addOrder(orderArray, orderName, orderType, sqlHandleEnum);
    }

    @Override
    public AbstractConditionProvider addSumOrder(String orderName, OrderEnum orderType) {
        this.setOrder(orderName, orderType, SqlHandleEnum.HANDLE_SUM);
        return this;
    }

    @Override
    public AbstractConditionProvider addAvgOrder(String orderName, OrderEnum orderType) {
        this.setOrder(orderName, orderType, SqlHandleEnum.HANDLE_AVG);
        return this;
    }

    @Override
    public AbstractConditionProvider addExpOrder(String orderName, OrderEnum orderType) {
        this.setOrder(orderName, orderType, SqlHandleEnum.HANDLE_EXP);
        return this;
    }
}
