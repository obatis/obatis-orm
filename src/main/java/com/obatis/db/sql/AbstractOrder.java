package com.obatis.db.sql;

import com.obatis.db.constant.type.OrderEnum;
import com.obatis.db.constant.type.SqlHandleEnum;

import java.util.List;

public abstract class AbstractOrder {

	protected AbstractOrder() {

	}
	
	protected abstract void addOrder(List<Object[]> orders, String orderName, OrderEnum orderType, SqlHandleEnum sqlHandleEnum);
}
