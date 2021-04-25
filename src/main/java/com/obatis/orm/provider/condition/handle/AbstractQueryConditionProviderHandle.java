package com.obatis.orm.provider.condition.handle;

import com.obatis.orm.constant.type.FilterEnum;
import com.obatis.orm.provider.condition.AbstractQueryConditionProvider;

public class AbstractQueryConditionProviderHandle extends AbstractConditionProviderHandle implements AbstractQueryConditionProvider {

    @Override
    public AbstractQueryConditionProvider addGroup(String... groupNames) {
        return null;
    }

    @Override
    public AbstractQueryConditionProvider addGroupDateFormat(String groupName, String pattern) {
        return null;
    }

    @Override
    public AbstractQueryConditionProvider addHaving(String fieldName, FilterEnum filterEnum, Number value) {
        return null;
    }

    @Override
    public AbstractQueryConditionProvider addHavingGreaterThan(String fieldName, Number value) {
        return null;
    }

    @Override
    public AbstractQueryConditionProvider addHavingCountGreaterThan(String fieldName, Number value) {
        return null;
    }
}
