package com.obatis.orm.provider.condition;

import com.obatis.orm.constant.type.AppendTypeEnum;
import com.obatis.orm.provider.QueryProvider;

public interface ConditionProvider {

    /**
     * and 查询条件，等于查询，=
     * @param filterName
     * @param value
     */
    ConditionProvider equal(String filterName, Object value);

    /**
     * and 查询条件，等于查询，=，针对时间格式化使用
     * @param filterName
     * @param value
     * @param pattern
     */
    ConditionProvider equalDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询，等于查询，=，例如 a = b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider equalField(String filterName, String fieldName);

    /**
     * or 查询条件，等于查询，=
     * @param filterName
     * @param value
     */
    ConditionProvider orEqual(String filterName, Object value);

    /**
     * or 查询条件，等于查询，= ， 针对时间格式化
     * @param filterName
     * @param value
     */
    ConditionProvider orEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * or 关系的字段值相等的查询条件，例如 a = b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orEqualField(String filterName, String fieldName);

    /**
     * and 查询条件，模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider like(String filterName, Object value);

    /**
     * or 查询条件，模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider orLike(String filterName, Object value);

    /**
     * and 查询条件，左模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider leftLike(String filterName, Object value);

    /**
     * or 查询条件，左模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider orLeftLike(String filterName, Object value);

    /**
     * and 查询条件，右模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider rightLike(String filterName, Object value);

    /**
     * or 查询条件，右模糊查询, like
     * @param filterName
     * @param value
     */
    ConditionProvider orRightLike(String filterName, Object value);

    /**
     * and 查询条件，大于查询，>
     * @param filterName
     * @param value
     */
    ConditionProvider greaterThan(String filterName, Object value);

    /**
     * and 查询条件，大于查询，>， 针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider greaterThanDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询条件，大于查询，>，例如 a > b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider greaterThanField(String filterName, String fieldName);

    /**
     * or 查询条件，大于查询，>
     * @param filterName
     * @param value
     */
    ConditionProvider orGreaterThan(String filterName, Object value);

    /**
     * or 查询条件，大于查询，>，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider orGreaterThanDateFormat(String filterName, Object value, String pattern);

    /**
     * or 字段比较查询条件，大于查询，>，例如 a > b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orGreaterThanField(String filterName, String fieldName);

    /**
     * and 查询条件，大于等于查询，>=
     * @param filterName
     * @param value
     */
    ConditionProvider greaterEqual(String filterName, Object value);

    /**
     * and 查询条件，大于等于查询，>=，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider greaterEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询条件，大于等于查询，>=，例如 a >= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider greaterEqualField(String filterName, String fieldName);

    /**
     * or 查询条件，大于等于查询，>=
     * @param filterName
     * @param value
     */
    ConditionProvider orGreaterEqual(String filterName, Object value);

    /**
     * or 查询条件，大于等于查询，>=,针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider orGreaterEqualDateFormat(String filterName, Object value, String pattern);
    /**
     * or 字段比较查询条件，大于等于查询，>=，例如 a >= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orGreaterEqualField(String filterName, String fieldName);

    /**
     * and 大于等于0的条件表达式，传入字段名称即可
     * @param filterName
     */
    ConditionProvider greaterEqualZero(String filterName);

    /**
     * and 大于等于0的条件表达式，传入字段名称即可,针对时间格式化查询条件
     * @param filterName
     */
    ConditionProvider greaterEqualZeroDateFormat(String filterName, String pattern);

    /**
     * or 大于等于0的条件表达式，传入字段名称即可
     * @param filterName
     */
    ConditionProvider orGreaterEqualZero(String filterName);

    /**
     * or 大于等于0的条件表达式, 针对时间格式化查询条件
     * @param filterName
     */
    ConditionProvider orGreaterEqualZeroDateFormat(String filterName, String pattern);

    /**
     * and 查询条件，小于查询，<
     * @param filterName
     * @param value
     */
    ConditionProvider lessThan(String filterName, Object value);

    /**
     * and 查询条件，小于查询，<,针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    ConditionProvider lessThanDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询条件，小于查询，<，例如 a < b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider lessThanField(String filterName, String fieldName);

    /**
     * or 查询条件，小于查询，<
     * @param filterName
     * @param value
     */
    ConditionProvider orLessThan(String filterName, Object value);

    /**
     * or 查询条件，小于查询，<，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider orLessThanDateFormat(String filterName, Object value, String pattern);

    /**
     * or 字段比较查询条件，小于查询，<，例如 a < b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orLessThanField(String filterName, String fieldName);

    /**
     * and 查询条件，小于等于查询，<=
     * @param filterName
     * @param value
     */
    ConditionProvider lessEqual(String filterName, Object value);

    /**
     * and 查询条件，小于等于查询，<=，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    ConditionProvider lessEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询条件，小于等于查询，<=，例如 a <= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider lessEqualField(String filterName, String fieldName);

    /**
     * or 查询条件，小于等于查询，<=
     * @param filterName
     * @param value
     */
    ConditionProvider orLessEqual(String filterName, Object value);

    /**
     * or 查询条件，小于等于查询，<= 针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    ConditionProvider orLessEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * or 字段比较查询条件，小于等于查询，<=，例如 a <= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orLessEqualField(String filterName, String fieldName);

    /**
     * and 查询，不等于查询，<>
     * @param filterName
     * @param value
     */
    ConditionProvider notEqual(String filterName, Object value);

    /**
     * and 查询，不等于查询，<>  针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    ConditionProvider notEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * and 字段比较查询，不等于查询，<>，例如 a <> b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider notEqualField(String filterName, String fieldName);

    /**
     * or 查询，不等于查询，<>
     * @param filterName
     * @param value
     */
    ConditionProvider orNotEqual(String filterName, Object value);

    /**
     * or 查询，不等于查询，<> 针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    ConditionProvider orNotEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * or 字段比较查询，不等于查询，<>,例如 a <> b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    ConditionProvider orNotEqualField(String filterName, String fieldName);

    /**
     * and 查询条件，属于查询，in
     * @param filterName
     * @param value
     */
    ConditionProvider in(String filterName, Object value);

    /**
     * and 查询条件，属于查询，in >> 接收可变参数
     * @param filterName
     * @param value
     */
    ConditionProvider in(String filterName, Object...value);

    /**
     * or 查询条件，属于查询，in
     * @param filterName
     * @param value
     */
    ConditionProvider orIn(String filterName, Object value);

    /**
     * or 查询条件，属于查询，in >> 接收可变参数
     * @param filterName
     * @param value
     */
    ConditionProvider orIn(String filterName, Object...value);

    /**
     * and 查询条件，不属于查询，not in
     * @param filterName
     * @param value
     */
    ConditionProvider notIn(String filterName, Object value);

    /**
     * and 查询条件，不属于查询，not in >> 接收可变参数
     * @param filterName
     * @param value
     */
    ConditionProvider notIn(String filterName, Object...value);

    /**
     * or 查询条件，不属于查询，not in
     * @param filterName
     * @param value
     */
    ConditionProvider orNotIn(String filterName, Object value);

    /**
     * or 查询条件，不属于查询，not in  >>  接收可变参数
     * @param filterName
     * @param value
     */
    ConditionProvider orNotIn(String filterName, Object...value);

    /**
     * in 嵌套子查询
     * @param filterName
     * @param provider
     */
    ConditionProvider inProvider(String filterName, QueryProvider provider);

    /**
     * or 关系的 in 嵌套子查询
     * @param filterName
     * @param provider
     */
    ConditionProvider orInProvider(String filterName, QueryProvider provider);

    /**
     * not in 嵌套子查询
     * @param filterName
     * @param provider
     */
    ConditionProvider notInProvider(String filterName, QueryProvider provider);

    /**
     * or 关系的 in 嵌套子查询
     * @param filterName
     * @param provider
     * @return
     */
    ConditionProvider orNotInProvider(String filterName, QueryProvider provider);

    /**
     * and 查询条件，表示null值查询，is null
     * @param filterName
     */
    ConditionProvider isNull(String filterName);

    /**
     * or 查询条件，表示null值查询，is null
     * @param filterName
     */
    ConditionProvider orIsNull(String filterName);

    /**
     * and 查询条件，表示null值查询，is not null
     * @param filterName
     */
    ConditionProvider isNotNull(String filterName);

    /**
     * or 查询条件，表示null值查询，is not null
     * @param filterName
     */
    ConditionProvider orIsNotNull(String filterName);

    /**
     * and 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    ConditionProvider upGreaterThanZero(String filterName, Object value);

    /**
     * or 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    ConditionProvider orUpGreaterThanZero(String filterName, Object value);

    /**
     * and 设定值后大于等于条件判断，比如count + 10 >= 0
     * @param filterName
     * @param value
     */
    ConditionProvider upGreaterEqualZero(String filterName, Object value);

    /**
     * or 设定值后大于等于条件判断，比如count + 10 >= 0
     * @param filterName
     * @param value
     */
    ConditionProvider orUpGreaterEqualZero(String filterName, Object value);

    /**
     * and 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    ConditionProvider reduceGreaterThanZero(String filterName, Object value);

    /**
     * or 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    ConditionProvider orReduceGreaterThanZero(String filterName, Object value);

    /**
     * 减少 and 设定值后小于等于条件判断，比如count - 10 >= 0
     * @param filterName
     * @param value
     */
    ConditionProvider reduceGreaterEqualZero(String filterName, Object value);

    /**
     * 减少 or 设定值后小于等于条件判断，比如count - 10 >= 0
     * @param filterName
     * @param value
     */
    ConditionProvider orReduceGreaterEqualZero(String filterName, Object value);

    /**
     * 添加 or 查询条件，比如 and (type = 1 or name = 2)，主要作用于拼接 and 后括号中的表达式，主要用于 or
     * 查询的表达式，不然没必要。 如果 多条件拼接 or 查询(类似 where id = ? or type = 1
     * 的条件)，or 条件查询不能被当成第一个条件放入(type属性 orFilter 方法不能在第一个加入)，否则会被解析为 and 条件查询。
     * 默认与主体表达式用 and 拼接
     * @param queryProvider
     */
    ConditionProvider addProvider(QueryProvider queryProvider);

    /**
     * 添加 or 查询条件，比如 and (type = 1 or name = 2)，主要作用于拼接 and 后括号中的表达式，主要用于 or
     * 查询的表达式，不然没必要。 如果 多条件拼接 or 查询(类似 where id = ? or type = 1
     * 的条件)，or 条件查询不能被当成第一个条件放入(type属性 orFilter 方法不能在第一个加入)，否则会被解析为 and 条件查询。
     * 采用枚举的形式，灵活与主体拼接连接方式
     * @param queryProvider
     */
    ConditionProvider addProvider(QueryProvider queryProvider, AppendTypeEnum joinTypeEnum);

    /**
     * 用于设置获取条数
     * 如果是分页查询，不设置时自动设置默认值
     * 如果不是分页查询，不设置表示查询所有数据
     * @param limit
     */
    ConditionProvider setLimit(int limit);

    /**
     * 根据前端传入的 command 实体，获取查询属性的 @QueryFilter 注解值
     * obj 必须为 RequestParam 的子类，否则抛出 HandleException 异常
     * @author HuangLongPu
     * @param obj
     */
    ConditionProvider setFilters(Object obj);

    /**
     * 移除所有属性，方便对象复用，但是要确保对象已经被消费
     * @param
     */
    ConditionProvider reset();
}
