package com.obatis.orm.provider.handle;

import com.obatis.config.request.RequestConstant;
import com.obatis.config.response.result.ResultInfo;
import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.SqlHandleEnum;
import com.obatis.orm.constant.type.UnionEnum;
import com.obatis.orm.convert.BeanCacheConvert;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.condition.handle.AbstractQueryConditionProviderHandle;
import com.obatis.tools.ValidateTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询代理句柄接口
 */
public class QueryProviderHandle extends AbstractQueryConditionProviderHandle implements QueryProvider {

    /**
     * 初始化查询代理句柄，主要为连接查询、union 等场景使用
     * @param tableName
     */
    protected QueryProviderHandle(String tableName) {
        if(!ValidateTool.isEmpty(tableName)) {
            this.tableName = tableName;
        }
    }

    /**
     * 表名
     */
    private String tableName;
    /**
     * 过滤(排除)不需要查询的字段列表
     */
    private Map<String, String> removeColumnArray;
    /**
     * 不查询任何字段标识，连接查询时使用，默认为 false，连接表默认为 true
     */
    private boolean selectNothingFlag;
    /**
     * 默认页面，分页查询时生效
     */
    private int page = RequestConstant.DEFAULT_PAGE;
    /**
     * 连接查询 QueryProvider
     */
    private List<Object[]> unionProviderArray;

    public String getTableName() {
        return tableName;
    }

    public Map<String, String> getRemoveColumnArray() {
        return removeColumnArray;
    }

    public boolean isSelectNothingFlag() {
        return selectNothingFlag;
    }

    public int getPage() {
        return page;
    }

    public List<Object[]> getUnionProviderArray() {
        return unionProviderArray;
    }

    /**
     * 添加字段方法，接收两个参数，此方法主要用于查询(select)
     * @param fieldName
     * @param value
     * @throws HandleException
     */
    private void addColumn(String fieldName, Object value) throws HandleException {
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_DEFAULT, value);
    }

    /**
     * 用于select查询，设置查询字段/属性
     * @param columns
     * @return
     * @throws HandleException
     */
    @Override
    public QueryProvider select(String... columns) throws HandleException {
        for (String column : columns) {
            if(ValidateTool.isEmpty(column)) {
                throw new HandleException("error: column is null");
            }
            this.addColumn(column, null);
        }
        return this;
    }

    /**
     * count 统计函数 >> count(1)，默认别名为 count。
     * @return
     */
    @Override
    public QueryProvider selectCount() {
        this.selectCount("count");
        return this;
    }

    /**
     * count 统计函数 >> count(1) as 'aliasName'。
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectCount(String aliasName) {
        this.addColumn("", SqlHandleEnum.HANDLE_COUNT, aliasName);
        return this;
    }

    /**
     * count与distinct 去重函数联合使用 >> count distinct 'fieldName'，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectCountDistinct(String fieldName) {
        this.selectCountDistinct(fieldName, fieldName);
        return this;
    }

    /**
     * count与distinct 去重函数联合使用 >> count distinct 'fieldName' as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectCountDistinct(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_COUNT, aliasName);
        return this;
    }

    /**
     * sum 求和函数 >> sum('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectSum(String fieldName) {
        this.selectSum(fieldName, fieldName.matches("[0-9A-Za-z_]*") ? fieldName : "sumValue");
        return this;
    }

    /**
     * sum 求和函数 >> sum('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectSum(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_SUM, aliasName);
        return this;
    }

    /**
     * min 最小值函数 >> min('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectMin(String fieldName) {
        this.selectMin(fieldName, fieldName.matches("[0-9A-Za-z_]*") ? fieldName : "minValue");
        return this;
    }

    /**
     * min 最小值函数 >> min('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectMin(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_MIN, aliasName);
        return this;
    }

    /**
     * max 最大值函数 >> max('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectMax(String fieldName) {
        this.selectMax(fieldName, fieldName.matches("[0-9A-Za-z_]*") ? fieldName : "maxValue");
        return this;
    }

    /**
     * max 最大值函数 >> max('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectMax(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_MAX, aliasName);
        return this;
    }

    /**
     * avg 平均值函数 >> avg('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectAvg(String fieldName) {
        this.selectAvg(fieldName, fieldName.matches("[0-9A-Za-z_]*") ? fieldName : "avgValue");
        return this;
    }

    /**
     * avg 平均值函数 >> avg('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectAvg(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_AVG, aliasName);
        return this;
    }

    /**
     * distinct 去除重复值函数 >> distinct('fieldName')，默认别名为 'fieldName'
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectDistinct(String fieldName) {
        this.selectAvg(fieldName, fieldName.matches("[0-9A-Za-z_]*") ? fieldName : "distinctValue");
        return this;
    }

    /**
     * distinct 去除重复值函数 >> distinct('fieldName') as 'aliasName'
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectDistinct(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = fieldName;
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_DISTINCT, aliasName);
        return this;
    }

    /**
     * 表达式函数，非聚合函数时使用，如需聚合，直接使用提供的聚合函数方法即可，同等原理
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider selectExp(String fieldName) {
        this.selectExp(fieldName, "expValue");
        return this;
    }

    /**
     * 表达式函数，非聚合函数时使用，如需聚合，直接使用提供的聚合函数方法即可，同等原理
     * @param fieldName
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectExp(String fieldName, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: selectExp field is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = "exp_value";
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_EXP, aliasName);
        return this;
    }

    /**
     * 针对日期进行 format 处理，fieldName 默认为别名
     * @param fieldName
     * @param pattern
     * @return
     */
    @Override
    public QueryProvider selectDateFormat(String fieldName, String pattern) {
        this.selectDateFormat(fieldName, pattern, fieldName);
        return this;
    }

    /**
     * 针对日期进行 format 处理
     * @param fieldName
     * @param pattern
     * @param aliasName
     * @return
     */
    @Override
    public QueryProvider selectDateFormat(String fieldName, String pattern, String aliasName) {
        if(ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if(ValidateTool.isEmpty(pattern)) {
            throw new HandleException("error: pattern is null");
        }
        if(ValidateTool.isEmpty(aliasName)) {
            aliasName = "date_value";
        }
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_DATE_FORMAT, aliasName, pattern);
        return this;
    }

    /**
     * 排除不需要查询的字段，主要针对实体泛型返回的查询中，如果字段被加入，则会在 SQL 中过滤。
     * @param fieldName
     * @return
     */
    @Override
    public QueryProvider removeColumn(String... fieldName) {
        if (ValidateTool.isEmpty(fieldName)) {
            throw new HandleException("error: field is null");
        }
        if (this.removeColumnArray == null) {
            this.removeColumnArray = new HashMap<>();
        }
        for(String field : fieldName) {
            this.removeColumnArray.put(field, field);
        }
        return this;
    }

    /**
     * 如果需要 QueryProvider 不查询任何字段，调用此方法传入 true 即可。
     * 该方法主要用于主表对应的 QueryProvider，left join 连接的从表可以不用调用此方法传入，从表不指定查询字段默认不查询
     * @param selectNothingFlag
     * @return
     */
    @Override
    public QueryProvider selectNothing(boolean selectNothingFlag) {
        this.selectNothingFlag = selectNothingFlag;
        return this;
    }

    /**
     * 传入 ResultInfo 的子类进行自动转换。
     * 如果接收的属性与数据库字段不一致，用@Column 注解映射，映射可以是实体属性名和字段名。
     * 如果有属性不想被添加到addField中，用@NotColumn 注解映射，将会自动过滤。
     * @param cls
     * @return
     */
    @Override
    public QueryProvider setColumn(Class<?> cls) {
        if(!ResultInfo.class.isAssignableFrom(cls)) {
            throw new HandleException("error: the select is not instanceof ResultInfoOutput");
        }

        List<String[]> result = BeanCacheConvert.getResultFields(cls);
        for (String[] field : result) {
            this.addColumn(field[0], field[1]);
        }
        return this;
    }

    /**
     * 设置页码，查询行数为默认值
     * @param page
     * @return
     */
    @Override
    public QueryProvider setPage(int page) {
        if(this.page != page) {
            this.page = page;
        }
        return this;
    }

    /**
     * 设置分页属性
     * @param page
     * @param rows
     * @return
     */
    @Override
    public QueryProvider setPageInfo(int page, int rows) {
        this.setPage(page);
        this.setLimit(rows);
        return this;
    }

    /**
     * 添加 union all 连接查询
     * @param queryProvider
     * @return
     */
    @Override
    public QueryProvider addUnionProvider(QueryProvider queryProvider) {
        this.addUnionProvider(queryProvider, UnionEnum.UNION_ALL);
        return this;
    }

    /**
     * 添加 union all 连接查询
     * @param queryProvider
     * @param unionEnum
     * @return
     */
    @Override
    public QueryProvider addUnionProvider(QueryProvider queryProvider, UnionEnum unionEnum) {
        if (queryProvider == null) {
            throw new HandleException("error: union queryProvider can't null");
        }
        if(queryProvider == this) {
            throw new HandleException("error: union queryProvider is same");
        }

        if(this.unionProviderArray == null) {
            this.unionProviderArray = new ArrayList<>();
        }
        Object[] unionProvider = {unionEnum, queryProvider};
        this.unionProviderArray.add(unionProvider);
        return this;
    }

    /**
     * 移除所有属性，方便对象复用，但是要确保对象已经被消费
     * @return
     */
    @Override
    public QueryProvider reset() {
        super.reset();
        if(removeColumnArray != null && !removeColumnArray.isEmpty()) {
            removeColumnArray.clear();
        }
        if(selectNothingFlag) {
            selectNothingFlag = false;
        }
        if(page != RequestConstant.DEFAULT_PAGE) {
            page = RequestConstant.DEFAULT_PAGE;
        }
        if(unionProviderArray != null && !unionProviderArray.isEmpty()) {
            unionProviderArray.clear();
        }
        return this;
    }
}
