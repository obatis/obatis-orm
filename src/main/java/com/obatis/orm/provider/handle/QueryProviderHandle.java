package com.obatis.orm.provider.handle;

import com.obatis.exception.HandleException;
import com.obatis.orm.constant.type.UnionEnum;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.condition.handle.AbstractQueryConditionProviderHandle;

public class QueryProviderHandle extends AbstractQueryConditionProviderHandle implements QueryProvider {

    @Override
    public QueryProvider select(String... columns) throws HandleException {
        return null;
    }

    @Override
    public QueryProvider selectCount() {
        return null;
    }

    @Override
    public QueryProvider selectCount(String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectCountDistinct(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectCountDistinct(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectSum(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectSum(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectMin(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectMin(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectMax(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectMax(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectAvg(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectAvg(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectDistinct(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectDistinct(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectExp(String fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectExp(String fieldName, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider selectDateFormat(String fieldName, String pattern) {
        return null;
    }

    @Override
    public QueryProvider selectDateFormat(String fieldName, String pattern, String aliasName) {
        return null;
    }

    @Override
    public QueryProvider removeField(String... fieldName) {
        return null;
    }

    @Override
    public QueryProvider selectNothing(boolean selectNothingFlag) {
        return null;
    }

    @Override
    public QueryProvider setColumn(Class<?> cls) {
        return null;
    }

    @Override
    public QueryProvider setPage(int page) {
        return null;
    }

    @Override
    public QueryProvider setPageInfo(int page, int rows) {
        return null;
    }

    @Override
    public QueryProvider addUnionProvider(QueryProvider queryProvider) {
        return null;
    }

    @Override
    public QueryProvider addUnionProvider(QueryProvider queryProvider, UnionEnum unionEnum) {
        return null;
    }
}
