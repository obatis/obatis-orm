package com.obatis.orm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共基础实体静态属性，和数据库表字段对应
 * @author HuangLongPu
 */
public class CommonField {

	/**
	 * 主键ID，唯一性标识
	 */
	public static final String FIELD_ID = "id";
	/**
	 * 创建时间
	 */
	public static final String FIELD_CREATE_TIME = "create_time";

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(1);
	}
}
