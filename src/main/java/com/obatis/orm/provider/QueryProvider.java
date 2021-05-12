package com.obatis.orm.provider;

import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.UnionEnum;
import com.obatis.orm.provider.condition.AbstractQueryConditionProvider;
import com.obatis.orm.provider.condition.ConditionProvider;

/**
 * 查询代理句柄接口
 */
public interface QueryProvider extends AbstractQueryConditionProvider {

    /**
     * 用于select查询，设置查询字段/属性
     * @param columns
     * @throws HandleException
     */
    QueryProvider select(String...columns) throws HandleException;


    /**
     * count 统计函数 >> count(1)，默认别名为 count。
     */
    QueryProvider selectCount();

    /**
     * count 统计函数 >> count(1) as 'aliasName'。
     * @param aliasName
     */
    QueryProvider selectCount(String  aliasName);

    /**
     * count与distinct 去重函数联合使用 >> count distinct 'fieldName'，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectCountDistinct(String fieldName);

    /**
     * count与distinct 去重函数联合使用 >> count distinct 'fieldName' as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectCountDistinct(String fieldName, String aliasName);

    /**
     * sum 求和函数 >> sum('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectSum(String fieldName);

    /**
     * sum 求和函数 >> sum('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectSum(String fieldName, String  aliasName);

    /**
     * min 最小值函数 >> min('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectMin(String fieldName);

    /**
     * min 最小值函数 >> min('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectMin(String fieldName, String  aliasName);

    /**
     * max 最大值函数 >> max('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectMax(String fieldName);

    /**
     * max 最大值函数 >> max('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectMax(String fieldName, String  aliasName);

    /**
     * avg 平均值函数 >> avg('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectAvg(String fieldName);

    /**
     * avg 平均值函数 >> avg('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectAvg(String fieldName, String  aliasName);

    /**
     * distinct 去除重复值函数 >> distinct('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     */
    QueryProvider selectDistinct(String fieldName);

    /**
     * distinct 去除重复值函数 >> distinct('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectDistinct(String fieldName, String  aliasName);

    /**
     * 表达式函数，非聚合函数时使用，如需聚合，直接使用提供的聚合函数方法即可，同等原理
     * @param fieldName
     */
    QueryProvider selectExp(String fieldName);

    /**
     * 表达式函数，非聚合函数时使用，如需聚合，直接使用提供的聚合函数方法即可，同等原理
     * @param fieldName
     * @param aliasName
     */
    QueryProvider selectExp(String fieldName, String  aliasName);

    /**
     * 针对日期进行 format 处理，fieldName 默认为别名
     * @param fieldName
     * @param pattern
     */
    QueryProvider selectDateFormat(String fieldName, String pattern);

    /**
     * 针对日期进行 format 处理
     * @param fieldName
     * @param pattern
     * @param aliasName
     */
    QueryProvider selectDateFormat(String fieldName, String pattern, String aliasName);

    /**
     * 排除不需要查询的字段，主要针对实体泛型返回的查询中，如果字段被加入，则会在 SQL 中过滤。
     * @param fieldName
     */
    QueryProvider removeColumn(String...fieldName);

    /**
     * 如果需要 QueryProvider 不查询任何字段，调用此方法传入 true 即可。
     * 该方法主要用于主表对应的 QueryProvider，left join 连接的从表可以不用调用此方法传入，从表不指定查询字段默认不查询
     * @param selectNothingFlag
     */
    QueryProvider selectNothing(boolean selectNothingFlag);

    /**
     * 传入 ResultInfoOutput 的子类进行自动转换。
     * 如果接收的属性与数据库字段不一致，用@Column 注解映射，映射可以是实体属性名和字段名。
     * 如果有属性不想被添加到addField中，用@NotColumn 注解映射，将会自动过滤。
     * @param cls
     */
    QueryProvider setColumn(Class<?> cls);

    /**
     * 设置页码，查询行数为默认值
     * @param page
     * @return
     */
    QueryProvider setPage(int page);

    /**
     * 设置分页属性
     * @param page
     * @param rows
     * @return
     */
    QueryProvider setPageInfo(int page, int rows);

    /**
     * 添加 union all 连接查询
     * @param queryProvider
     */
    QueryProvider addUnionProvider(QueryProvider queryProvider);

    /**
     * 添加 union all 连接查询
     * @param queryProvider
     */
    QueryProvider addUnionProvider(QueryProvider queryProvider, UnionEnum unionEnum);
}
