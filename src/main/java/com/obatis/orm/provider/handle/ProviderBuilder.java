package com.obatis.orm.provider.handle;

import com.obatis.orm.provider.DeleteProvider;
import com.obatis.orm.provider.QueryProvider;
import com.obatis.orm.provider.UpdateProvider;

public class ProviderBuilder {

    /**
     * 创建查询代理
     * @return
     */
    public static QueryProvider query() {
        return query(null);
    }

    /**
     * 创建查询代理
     * @param table
     * @return
     */
    public static QueryProvider query(String table) {
        return new QueryProviderHandle();
    }

    /**
     * 创建更新代理
     * @return
     */
    public static UpdateProvider update() {
        return new UpdateProviderHandle();
    }

    /**
     * 创建删除代理
     * @return
     */
    public static DeleteProvider delete() {
        return new DeleteProviderHandle();
    }

}
