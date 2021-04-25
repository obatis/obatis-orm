package com.obatis.orm.provider.condition;

import com.obatis.orm.constant.type.JoinTypeEnum;
import com.obatis.orm.constant.type.OrderEnum;
import com.obatis.orm.provider.QueryProvider;

public interface AbstractConditionProvider extends ConditionProvider {

    /**
     * 连接查询 on 连接的 like 模糊查询
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onLike(String filterName, Object value);

    /**
     * 连接查询on 连接的 or 关系的模糊查询 like
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrLike(String filterName, Object value);

    /**
     * 连接查询 and查询条件的左模糊查询 like
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onLeftLike(String filterName, Object value);

    /**
     * 连接查询 or 查询条件左模糊查询， like
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrLeftLike(String filterName, Object value);

    /**
     * 连接查询 and 查询条件的右模糊查询，like
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onRightLike(String filterName, Object value);

    /**
     * 连接查询 or查询条件的右模糊查询， like
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrRightLike(String filterName, Object value);

    /**
     * 连接查询且为and关系查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onEquals(String filterName, Object value);

    /**
     * 连接查询且为and关系查询条件，针对时间格式化使用
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onEqualsDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询且为and关系的字段值相等的查询条件，例如 a = b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onEqualsField(String filterName, String fieldName);

    /**
     * 连接查询 or 关系的查询条件，等于查询，=
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrEquals(String filterName, Object value);

    /**
     * 连接查询 or 关系的查询条件，等于查询，=，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrEqualsDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 关系的字段值相等的查询条件，例如 a = b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrEqualsField(String filterName, String fieldName);

    /**
     * 连接查询 and 查询条件，大于查询，>
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onGreaterThan(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，大于查询，>，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onGreaterThanDateFormat(String filterName, Object value, String pattern);

    /**
     * and 查询条件，字段大于查询，>，例如 a > b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onGreaterThanField(String filterName, String fieldName);

    /**
     * 连接查询 or 查询条件，大于查询，>
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrGreaterThan(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，大于查询，>，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrGreaterThanDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 关系字段大于查询条件，大于查询，>，例如 a > b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrGreaterThanField(String filterName, String fieldName);

    /**
     * 连接查询 and 查询条件，大于等于查询，>=
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onGreaterEqual(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，大于等于查询，>=，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onGreaterEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 and 字段查询条件，大于等于查询，>=，例如 a >= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onGreaterEqualField(String filterName, String fieldName);

    /**
     * 连接查询 or 查询条件，大于等于查询，>=
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrGreaterEqual(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，大于等于查询，>=，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrGreaterEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 字段查询条件，大于等于查询，>=，例如 a >= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrGreaterEqualField(String filterName, String fieldName);

    /**
     * 连接查询 and 大于等于0的条件表达式，传入字段名称即可
     * @param filterName
     */
    AbstractConditionProvider onGreaterEqualZero(String filterName);

    /**
     * 连接查询 or 大于等于0的条件表达式，传入字段名称即可
     * @param filterName
     */
    AbstractConditionProvider onOrGreaterEqualZero(String filterName);

    /**
     * 连接查询 and 查询条件，小于查询，<
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onLessThan(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，小于查询，<，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onLessThanDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 and 字段比较查询条件，小于查询，<，例如 a < b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onLessThanField(String filterName, String fieldName);

    /**
     * 连接查询 or 查询条件，小于查询，<
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrLessThan(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，小于查询 <，针对时间格式化查询条件
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrLessThanDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 字段比较查询条件，小于查询，<，例如 a < b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrLessThanField(String filterName, String fieldName);

    /**
     * 连接查询 and 查询条件，小于等于查询，<=
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onLessEqual(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，小于等于查询，<= 针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    AbstractConditionProvider onLessEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 and 字段比较查询条件，小于等于查询，<=，例如 a <= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onLessEqualField(String filterName, String fieldName);

    /**
     * 连接查询 or 查询条件，小于等于查询，<=
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrLessEqual(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，小于等于查询，<=  针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    AbstractConditionProvider onOrLessEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 字段比较查询条件，小于等于查询，<=，例如 a <= b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrLessEqualField(String filterName, String fieldName);

    /**
     * 连接查询 and 查询，不等于查询，<>
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onNotEqual(String filterName, Object value);

    /**
     * 连接查询 and 查询，不等于查询，<>  针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    AbstractConditionProvider onNotEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 and 查询，不等于查询，<>,例如 a <> b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onNotEqualField(String filterName, String fieldName);


    /**
     * 连接查询 or 查询，不等于查询，<>
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrNotEqual(String filterName, Object value);

    /**
     * 连接查询 or 查询，不等于查询，<>  针对时间格式化查询条件
     * @param filterName
     * @param value
     * @param pattern
     */
    AbstractConditionProvider onOrNotEqualDateFormat(String filterName, Object value, String pattern);

    /**
     * 连接查询 or 字段比较查询，不等于查询，<>,例如 a <> b，a和b均为数据库字段
     * @param filterName
     * @param fieldName
     */
    AbstractConditionProvider onOrNotEqualField(String filterName, String fieldName);

    /**
     * 连接查询 and 查询条件，属于查询，in
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onIn(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，属于查询，in >> 接收可变参数
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onIn(String filterName, Object...value);


    /**
     * 连接查询 or 查询条件，属于查询，in
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrIn(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，属于查询，in >> 接收可变参数
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrIn(String filterName, Object...value);

    /**
     * 连接查询 and 查询条件，不属于查询，not in
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onNotIn(String filterName, Object value);

    /**
     * 连接查询 and 查询条件，不属于查询，not in  >> 接收可变参数
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onNotIn(String filterName, Object...value);

    /**
     * 连接查询 or 查询条件，不属于查询，not in
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrNotIn(String filterName, Object value);

    /**
     * 连接查询 or 查询条件，不属于查询，not in  >>  接收可变参数
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrNotIn(String filterName, Object...value);

    /**
     * 连接查询 in 嵌套子查询
     * @param filterName
     * @param provider
     */
    AbstractConditionProvider onInProvider(String filterName, QueryProvider provider);

    /**
     * 连接查询 or 关系的 in 嵌套子查询
     * @param filterName
     * @param provider
     */
    AbstractConditionProvider onOrInProvider(String filterName, QueryProvider provider);

    /**
     * 连接查询 not in 嵌套子查询
     * @param filterName
     * @param provider
     * @return
     */
    AbstractConditionProvider onNotInProvider(String filterName, QueryProvider provider);

    /**
     * 连接查询 or 关系的 not in 嵌套子查询
     * @param filterName
     * @param provider
     * @return
     */
    AbstractConditionProvider onOrNotInProvider(String filterName, QueryProvider provider);

    /**
     * 连接查询 and 查询条件，表示null值查询，is null
     * @param filterName
     */
    AbstractConditionProvider onIsNull(String filterName);

    /**
     * 连接查询 or 查询条件，表示null值查询，is null
     * @param filterName
     */
    AbstractConditionProvider onOrIsNull(String filterName);

    /**
     * 连接查询 and 查询条件，表示null值查询，is not null
     * @param filterName
     */
    AbstractConditionProvider onIsNotNull(String filterName);

    /**
     * 连接查询 or 查询条件，表示null值查询，is not null
     * @param filterName
     */
    AbstractConditionProvider onOrIsNotNull(String filterName);

    /**
     * 连接查询 and 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onUpGreaterThanZero(String filterName, Object value);

    /**
     * 连接查询 or 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrUpGreaterThanZero(String filterName, Object value);
    /**
     * 连接查询 and 设定值后大于等于条件判断，比如count + 10 >= 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onUpGreaterEqualZero(String filterName, Object value);

    /**
     * 连接查询 or 设定值后大于等于条件判断，比如count + 10 >= 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrUpGreaterEqualZero(String filterName, Object value);

    /**
     * 连接查询 and 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onReduceGreaterThanZero(String filterName, Object value);

    /**
     * 连接查询 or 设定值后大于条件判断，比如count + 10 > 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrReduceGreaterThanZero(String filterName, Object value);

    /**
     * 连接查询 减少 and 设定值后小于等于条件判断，比如count - 10 >= 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onReduceGreaterEqualZero(String filterName, Object value);

    /**
     * 连接查询 减少 or 设定值后小于等于条件判断，比如count - 10 >= 0
     * @param filterName
     * @param value
     */
    AbstractConditionProvider onOrReduceGreaterEqualZero(String filterName, Object value);

    /**
     * 用于连接查询on条件的拼接，默认and方式拼接
     * 添加 or 查询条件，比如 and (type = 1 or name = 2)，主要作用于拼接 and 后括号中的表达式，主要用于 or
     * 查询的表达式，不然没必要。 如果 多条件拼接 or 查询(类似 where id = ? or type = 1
     * 的条件)，or 条件查询不能被当成第一个条件放入(type属性 orFilter 方法不能在第一个加入)，否则会被解析为 and 条件查询。
     * 默认与主体表达式用 and 拼接
     * @param queryProvider
     */
    AbstractConditionProvider addOnProvider(QueryProvider queryProvider);

    /**
     * 用于连接查询on条件的拼接
     * 添加 or 查询条件，比如 and (type = 1 or name = 2)，主要作用于拼接 and 后括号中的表达式，主要用于 or
     * 查询的表达式，不然没必要。 如果 多条件拼接 or 查询(类似 where id = ? or type = 1
     * 的条件)，or 条件查询不能被当成第一个条件放入(type属性 orFilter 方法不能在第一个加入)，否则会被解析为 and 条件查询。
     * 采用枚举的形式，灵活与主体拼接连接方式
     * @param queryProvider
     */
    AbstractConditionProvider addOnProvider(QueryProvider queryProvider, JoinTypeEnum joinTypeEnum);

    /**
     * 添加 left join 查询，会被拼接到left join 的连体SQL。 当使用这个属性时，必须设置 joinTableName的连接表名。
     * @param fieldName        表示left join 前面一张关联字段。
     * @param paramFieldName   表示left join 后紧跟表的关联字段。
     * @param queryProvider    被left join的封装对象。
     */
    AbstractConditionProvider addleftJoin(String fieldName, String paramFieldName, QueryProvider queryProvider);

    /**
     * 添加 left join 查询，会被拼接到left join 的连体SQL。 当使用这个属性时，必须设置 joinTableName
     * 的连接表名。 针对多条件，两数组长度必须一致。
     * @param fieldName         表示left join 前面一张关联字段。
     * @param paramFieldName    表示left join 后紧跟表的关联字段。
     * @param queryProvider             被left join的封装对象。
     */
    AbstractConditionProvider addleftJoin(String[] fieldName, String[] paramFieldName, QueryProvider queryProvider);

    /**
     * 排序，参数分别为排序字段，排序值，排序值类型参考 QueryParam 中 ORDER 开头的常量
     * @param orderName
     * @param orderType
     */
    AbstractConditionProvider addOrder(String orderName, OrderEnum orderType);

    /**
     * 针对 sum 聚合函数的排序
     * @param orderName
     * @param orderType
     */
    AbstractConditionProvider addSumOrder(String orderName, OrderEnum orderType);

    /**
     * 针对平均数 avg 聚合函数的排序
     * @param orderName
     * @param orderType
     */
    AbstractConditionProvider addAvgOrder(String orderName, OrderEnum orderType);

    /**
     * 支持传入表达式的排序
     * @param orderName
     * @param orderType
     */
    AbstractConditionProvider addExpOrder(String orderName, OrderEnum orderType);

}
