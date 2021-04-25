package com.obatis.orm;

import com.obatis.config.response.result.PageInfo;
import com.obatis.config.response.result.ResultInfo;
import com.obatis.exception.HandleException;
import com.obatis.orm.provider.DeleteProvider;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.UpdateProvider;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SqlHandle<T> {

    /**
     * 单个添加，传入一个 CommonEntity对象，并返回影响行数
     * @param t    单个添加的实体数据
     * @return
     */
    int insert(T t) throws HandleException;

    /**
     * 批量添加，传入list CommonModel 对象，返回影响行数
     * @param list
     * @return
     */
    int batchInsert(List<T> list) throws HandleException;

    /**
     * 传入数据库封装操作对象 QueryProvider，进行更新
     * @param provider
     * @return
     */
    int update(UpdateProvider provider) throws HandleException;

    /**
     * 批量更新，传入list 操作对象，返回影响行数
     * @param list
     * @return
     * @throws HandleException
     */
    int batchUpdate(List<UpdateProvider> list) throws HandleException;

    /**
     * 根据传入的id主键，删除一条记录
     * @param id
     * @return
     */
    int deleteById(Object id) throws HandleException;

    /**
     * 根据传入的 QueryProvider 对象，进行删除操作
     * @param provider
     * @return
     */
    int delete(DeleteProvider provider) throws HandleException;

    /**
     * 1、根据id主键查询一条记录，返回所有字段。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param id
     * @return
     */
    T findById(Object id);

    /**
     * 1、根据id主键查询一条记录，返回所有字段，返回类型为预设的class类型，需强制转换一次。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param id
     * @param resultCls
     * @return
     */
    <M extends ResultInfo> M findById(Object id, Class<M> resultCls);

    /**
     * 1、根据id主键查询一条记录，返回设定的字段。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @param id
     * @return
     */
    T findById(QueryProvider provider, Object id);

    /**
     * 1、根据id主键查询一条记录，返回设定的字段，返回类型为预设的class类型，需强制转换一次。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @param id
     * @param resultCls
     * @return
     */
    <M extends ResultInfo> M findById(QueryProvider provider, Object id, Class<M> resultCls);

    /**
     * 1、根据传入的 QueryProvider 对象，查询一条 CommonModel 子类的记录。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    T find(QueryProvider provider);

    /**
     * 1、根据传入的 QueryProvider 对象，返回类型为预设的class类型，需强制转换一次。
     * 2、如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @param resultCls
     * @return
     */
    <M extends ResultInfo> M find(QueryProvider provider, Class<M> resultCls);

    /**
     * 主要针对有多条记录符合查询条件时，获取第一条数据(排序方式自行决定)
     * @param provider
     * @return
     */
    T findOne(QueryProvider provider);

    /**
     * 主要针对有多条记录符合查询条件时，获取第一条数据(排序方式自行决定)
     * @param provider
     * @param resultCls
     * @param <M>
     * @return
     */
    <M extends ResultInfo> M findOne(QueryProvider provider, Class<M> resultCls);

    /**
     * 1、主要作用为校验，provider 只需传入条件值即可，映射的SQL语句例如：select count(1) from test t where t.name='test';
     * 2、根据 count 函数的返回值进行判断，返回值大于0表示存在，否则不存在。
     * @param provider
     * @return
     */
    boolean validate(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回一条Map格式记录。 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Map<String, Object> findConvertMap(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回符合条件的list集合的BaseEntity记录。
     * 如果有传入分页标识，只返回设置页面的极限值，否则返回所有符合条件的数据。
     * @param provider
     * @return
     */
    List<T> list(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回符合条件的list集合，返回类型为预设的class类型，需强制转换一次。
     * 如果有传入分页标识，只返回设置页面的极限值，否则返回所有符合条件的数据。
     * @param provider
     * @return
     */
    <M extends ResultInfo> List<M> list(QueryProvider provider, Class<M> resultCls);

    /**
     * 根据传入的 QueryProvider 对象，返回符合条件的List集合的Map格式记录。
     * 如果有传入分页标识，只返回设置页面的极限值，否则返回所有符合条件的数据。
     * @param provider
     * @return
     */
    List<Map<String, Object>> listConvertMap(QueryProvider provider);

    /**
     * 查询单个字段返回 List<Integer> 数据
     * @param provider
     * @return
     */
    List<Integer> listInteger(QueryProvider provider);

    /**
     * 查询单个字段返回 List<BigInteger> 数据
     * @param provider
     * @return
     */
    List<BigInteger> listBigInteger(QueryProvider provider);

    /**
     * 查询单个字段返回 List<Long> 数据
     * @param provider
     * @return
     */
    List<Long> listLong(QueryProvider provider);

    /**
     * 查询单个字段返回 List<Double> 数据
     * @param provider
     * @return
     */
    List<Double> listDouble(QueryProvider provider);

    /**
     * 查询单个字段返回 List<BigDecimal> 数据
     * @param provider
     * @return
     */
    List<BigDecimal> listBigDecimal(QueryProvider provider);

    /**
     * 查询单个字段返回 List<String> 数据
     * @param provider
     * @return
     */
    List<String> listString(QueryProvider provider);

    /**
     * 查询单个字段返回 List<Date> 数据
     * @param provider
     * @return
     */
    List<Date> listDate(QueryProvider provider);

    /**
     * 查询单个字段返回 List<LocalDate> 数据
     * @param provider
     * @return
     */
    List<LocalDate> listLocalDate(QueryProvider provider);

    /**
     * 查询单个字段返回 List<LocalDateTime> 数据
     * @param provider
     * @return
     */
    List<LocalDateTime> listLocalDateTime(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回int的类型值。 该方法常用于查询count等类型的业务。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Integer findInteger(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回int的类型值。 该方法常用于查询count等类型的业务。
     * 该方法与 findInt 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findInt 方法。
     * @param provider
     * @return
     */
    Integer findIntegerOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 BigInteger 的类型值。 该方法常主要用于查询ID类型字段。
     * @param provider
     * @return
     */
    BigInteger findBigInteger(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 BigInteger 的类型值。 该方法常主要用于查询ID类型字段。
     * 该方法与 findBigInteger 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findBigInteger 方法。
     * @param provider
     * @return
     */
    BigInteger findBigIntegerOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回int的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Long findLong(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回int的类型值。
     * 该方法与 findLong 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findLong 方法。
     * @param provider
     * @return
     */
    Long findLongOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回Double的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Double findDouble(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回Double的类型值。
     * 该方法与 findDouble 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findDouble 方法。
     * @param provider
     * @return
     */
    Double findDoubleOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回BigDecimal的类型值。 该方法常用于查询金额字段。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    BigDecimal findBigDecimal(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回BigDecimal的类型值。 该方法常用于查询金额字段。
     * 该方法与 findBigDecimal 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findBigDecimal 方法。
     * @param provider
     * @return
     */
    BigDecimal findBigDecimalOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 Date 的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Date findDate(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 Date 的类型值。
     * 该方法与 findDate 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findDate 方法。
     * @param provider
     * @return
     */
    Date findDateOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalDate 的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    LocalDate findLocalDate(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalDate 的类型值。
     * 该方法与 findDate 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findLocalDate 方法。
     * @param provider
     * @return
     */
    LocalDate findLocalDateOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalDateTime 的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    LocalDateTime findLocalDateTime(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalDateTime 的类型值。
     * 该方法与 findDate 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findLocalDateTime 方法。
     * @param provider
     * @return
     */
    LocalDateTime findLocalDateTimeOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalDateTime 的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    LocalTime findLocalTime(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 LocalTime 的类型值。
     * 该方法与 findDate 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 LocalTime 方法。
     * @param provider
     * @return
     */
    LocalTime findTimeOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 String 的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    String findString(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回 String 的类型值。
     * 该方法与 findString 用法区别在于根据查询条件会返回多条数据，取第一条，可根据使用场景进行排序。
     * 如果能确保数据只有一条，建议使用 findString 方法。
     * @param provider
     * @return
     */
    String findStringOne(QueryProvider provider);

    /**
     * 根据传入的 QueryProvider 对象，返回Object的类型值。
     * 如果根据条件有多条数据符合，则抛出异常。
     * @param provider
     * @return
     */
    Object findObject(QueryProvider provider);

    /**
     * 需传入的条件值。
     * @param sql       sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param params    需传入的条件值，按顺序存放
     * @return
     */
    T findBySql(String sql, List<Object> params);

    /**
     * 返回Object 类型，比如int、decimal、String等。
     * @param sql
     * @param params
     * @return
     */
    Object findObjectBySql(String sql, List<Object> params);

    /**
     * 获取总条数，针对count 等SQL语句。
     * @param sql
     * @param params
     * @return
     */
    int findTotal(String sql, List<Object> params);

    /**
     * 传入SQL，返回预设类型对象。返回类型为预设的class类型，需强制转换一次。
     * @param sql          sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param params       需传入的条件值，按顺序存放
     * @param resultCls    返回类型
     * @return
     */
    <M extends ResultInfo> M findBySql(String sql, List<Object> params, Class<M> resultCls);

    /**
     * 传入SQL，返回map类型。
     * @param sql    sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param list   需传入的条件值，按顺序存放
     * @return
     */
    Map<String, Object> findMapBySql(String sql, List<Object> list);

    /**
     * 根据传入的SQL语句，返回符合条件的list集合的Map格式记录。
     * @param sql     sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param params  需传入的条件值，按顺序存放
     * @return
     */
    List<T> listBySql(String sql, List<Object> params);

    /**
     * 传入SQL，返回预设类型集合。返回类型为预设的class类型，需强制转换一次。
     * @param sql          sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param params       需传入的条件值，按顺序存放
     * @param resultCls    返回bean类型
     * @return
     */
    <M extends ResultInfo> List<M> listBySql(String sql, List<Object> params, Class<M> resultCls);

    /**
     * 根据传入的SQL语句，返回符合条件的list集合的Map格式记录。
     * @param sql      sql语句中的条件，用 "?" 号代替，防止SQL注入
     * @param params   需传入的条件值，按顺序存放
     * @return
     */
    List<Map<String, Object>> listMapBySql(String sql, List<Object> params);

    /**
     * 主要实现于在前端查询时选中的页面超过总条数，非前端分页查询，不建议使用。
     * 分页查询，同时返回分页数据和总条数。
     * @param sql           主体查询语句
     * @param totalSql      总条数查询语句
     * @param params        条件值
     * @param pageNumber    页码
     * @param pageSize      每行显示条数
     * @return
     */
    PageInfo<T> page(String sql, String totalSql, List<Object> params, int pageNumber, int pageSize);

    /**
     * 主要实现于在前端查询时选中的页面超过总条数，非前端分页查询，不建议使用。
     * 分页查询，同时返回分页数据和总条数。
     * @param sql           主体查询语句
     * @param totalSql      总条数查询语句
     * @param params        条件值
     * @param pageNumber    页码
     * @param pageSize      每行显示条数
     * @param resultCls     resultCls 返回 预定义的 resultCls Bean 泛型数据类型
     * @return
     */
    <M extends ResultInfo> PageInfo<M> page(String sql, String totalSql, List<Object> params, int pageNumber, int pageSize, Class<M> resultCls);

    /**
     * 主要实现于在前端查询时选中的页面超过总条数，非前端分页查询，不建议使用。
     * 分页查询，同时返回分页数据和总条数，返回 Map 数据。
     * @param sql           主体查询语句
     * @param totalSql      总条数查询语句
     * @param params        条件值
     * @param pageNumber    页面
     * @param pageSize      每行显示条数
     * @return
     */
    PageInfo<Map<String, Object>> pageResultMap(String sql, String totalSql, List<Object> params, int pageNumber, int pageSize);

    /**
     * 主要实现于在前端查询时选中的页面超过总条数，非前端分页查询，不建议使用。
     * 分页查询，同时返回分页数据和总条数。
     * @param provider 封装的参数对象
     * @return
     */
    PageInfo<T> page(QueryProvider provider);

    /**
     * 主要实现于在前端查询时选中的页面超过总条数，非前端分页查询，不建议使用。
     * 分页查询，同时返回分页数据和总条数。
     * @param provider       封装的参数对象
     * @param resultCls      返回 预定义的 resultCls Bean 泛型数据类型
     * @return
     */
    <M extends ResultInfo> PageInfo<M> page(QueryProvider provider, Class<M> resultCls);
}
