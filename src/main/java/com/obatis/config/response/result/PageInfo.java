package com.obatis.config.response.result;

import java.util.ArrayList;
import java.util.List;

public class PageInfo<T> {

    /**
     * 分页返回的数据列表
     */
    private List<T> list = new ArrayList<>();
    /**
     * 返回符合查询条件的总条数
     */
    private long total = 0;

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
}
