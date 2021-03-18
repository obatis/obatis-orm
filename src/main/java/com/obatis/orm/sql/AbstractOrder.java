package com.obatis.orm.sql;

import com.obatis.orm.constant.type.OrderEnum;
import com.obatis.orm.constant.type.SqlHandleEnum;

import java.util.List;

public abstract class AbstractOrder {

	protected AbstractOrder() {

	}
	
	protected abstract void addOrder(List<Object[]> orders, String orderName, OrderEnum orderType, SqlHandleEnum sqlHandleEnum);
}
