package com.obatis.orm.provider;

import com.obatis.exception.HandleException;
import com.obatis.orm.provider.condition.AbstractConditionProvider;

public interface UpdateProvider extends AbstractConditionProvider {

    /**
     * 添加字段方法，接收两个参数，第一个参数为要修改的字段名称，第二个为修改后的值
     * @param fieldName
     * @param value
     * @throws HandleException
     */
    UpdateProvider set(String fieldName, Object value) throws HandleException;

    /**
     * 实现累加，比如money = money + 20类似的SQL语句; fieldName 表示要操作的字段名称,value 表示要操作的值
     * @param fieldName
     * @param value
     */
    UpdateProvider up(String fieldName, Object value);

    /**
     * 实现累加，比如money = money - 20类似的SQL语句; fieldName 表示要操作的字段名称,value 表示要操作的值
     * @param fieldName
     * @param value
     */
    UpdateProvider reduce(String fieldName, Object value);

    /**
     * 根据前端传入的参数实体类，获取修改属性的 @UpdateField 注解值
     * obj 必须为 RequestParam 的子类，否则抛出 HandleException 异常
     * @param obj
     */
    UpdateProvider setUpdate(Object obj);
}
