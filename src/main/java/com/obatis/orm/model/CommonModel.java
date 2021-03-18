package com.obatis.orm.model;

import com.obatis.orm.annotation.Column;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共基础实体，所有定义的实体都需要继承此类
 * ID类型自定义
 * @author HuangLongPu
 */
public class CommonModel<T> implements Serializable {

	private static final long serialVersionUID = -3683453180317600090L;

	/**
	 * ID 主键，唯一性标识
	 */
	private T id;
	/**
	 * 创建时间
	 */
	@Column(name = CommonField.FIELD_CREATE_TIME)
	private LocalDateTime createTime;

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}
