package com.obatis.orm.provider.handle;

import com.obatis.config.request.RequestParam;
import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.SqlHandleEnum;
import com.obatis.orm.provider.UpdateProvider;
import com.obatis.orm.provider.condition.handle.AbstractConditionProviderHandle;
import com.obatis.orm.sql.QueryHandle;

public class UpdateProviderHandle extends AbstractConditionProviderHandle implements UpdateProvider {

    protected UpdateProviderHandle() {
    }

    /**
     * 添加字段方法，接收两个参数，第一个参数为要修改的字段名称，第二个为修改后的值
     * @param fieldName
     * @param value
     * @return
     * @throws HandleException
     */
    @Override
    public UpdateProvider set(String fieldName, Object value) throws HandleException {
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_DEFAULT, value);
        return this;
    }

    /**
     * 实现累加，比如money = money + 20类似的SQL语句; fieldName 表示要操作的字段名称,value 表示要操作的值
     * @param fieldName
     * @param value
     */
    public UpdateProvider up(String fieldName, Object value) {
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_UP, value);
        return this;
    }

    /**
     * 实现累加，比如money = money - 20类似的SQL语句; fieldName 表示要操作的字段名称,value 表示要操作的值
     * @param fieldName
     * @param value
     */
    public UpdateProvider reduce(String fieldName, Object value) {
        this.addColumn(fieldName, SqlHandleEnum.HANDLE_REDUCE, value);
        return this;
    }

    /**
     * 根据前端传入的参数实体类，获取修改属性的 @UpdateField 注解值
     * obj 必须为 RequestParam 的子类，否则抛出 HandleException 异常
     * @param obj
     */
    @Override
    public UpdateProvider setUpdate(Object obj) {
        if (!(obj instanceof RequestParam)) {
            throw new HandleException("error: the update is not instanceof RequestParam");
        }
        updateObj = obj;
        QueryHandle.getUpdateField(obj, this);
        QueryHandle.getFilters(obj, this);
        return this;
    }
}
