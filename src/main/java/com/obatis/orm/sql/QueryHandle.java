package com.obatis.orm.sql;

import com.obatis.convert.date.DateConvert;
import com.obatis.orm.annotation.request.QueryFilter;
import com.obatis.orm.annotation.request.UpdateField;
import com.obatis.orm.constant.type.DateHandleEnum;
import com.obatis.orm.constant.type.FilterEnum;
import com.obatis.orm.constant.type.SqlHandleEnum;
import com.obatis.exception.HandleException;
import com.obatis.orm.provider.UpdateProvider;
import com.obatis.orm.provider.condition.ConditionProvider;
import com.obatis.orm.provider.condition.handle.ConditionProviderHandle;
import com.obatis.orm.provider.handle.QueryProviderHandle;
import com.obatis.tools.ValidateTool;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class QueryHandle {

	private QueryHandle() {
	}

	public static final void getFilters(Object object, ConditionProviderHandle conditionProvider) {
		getFilters(object, object.getClass(), conditionProvider);
	}
	
	private static final void getFilters(Object object, Class<?> cls, ConditionProviderHandle conditionProvider) {
		Field[] fields = cls.getDeclaredFields();
		
		for (Field field : fields) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if (isStatic) {
				continue;
			}
			if(!setFilter(object, conditionProvider, field)) {
				continue;
			}
		}
		
		Class<?> supCls = cls.getSuperclass();
		if(supCls != null) {
			getFilters(object, supCls, conditionProvider);
		}
	}

	private static boolean setFilter(Object object, ConditionProviderHandle conditionProvider, Field field) {
		QueryFilter queryFilter = field.getAnnotation(QueryFilter.class);
		if (queryFilter == null) {
			return false;
		}
		String fieldName = !ValidateTool.isEmpty(queryFilter.name()) ? queryFilter.name() : field.getName();
		field.setAccessible(true);
		Object value = null;
		try {
			value = field.get(object);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if(!queryFilter.isnull() && ValidateTool.isEmpty(value)) {
			return false;
		}

		DateHandleEnum dateHandle = queryFilter.datetype();
		if(value instanceof Date) {
			if(DateHandleEnum.BEGIN_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatBeginDateTime(((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			} else if (DateHandleEnum.END_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatEndDateTime(((Date) value).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			}
		} else if(value instanceof LocalDate) {
			if(DateHandleEnum.BEGIN_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatBeginDateTime((LocalDate) value);
			} else if (DateHandleEnum.END_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatEndDateTime((LocalDate) value);
			}
		} else if(value instanceof LocalDateTime) {
			if(DateHandleEnum.BEGIN_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatBeginDateTime((LocalDateTime) value);
			} else if (DateHandleEnum.END_HANDLE.equals(dateHandle)) {
				value = DateConvert.formatEndDateTime((LocalDateTime) value);
			}
		}


		FilterEnum filterType = queryFilter.type();
		switch (filterType) {
			case LIKE:
				conditionProvider.like(fieldName, value);
				break;
			case LEFT_LIKE:
				conditionProvider.leftLike(fieldName, value);
				break;
			case RIGHT_LIKE:
				conditionProvider.rightLike(fieldName, value);
				break;
			case EQUAL:
				conditionProvider.equal(fieldName, value);
				break;
			case GREATER_THAN:
				conditionProvider.greaterThan(fieldName, value);
				break;
			case GREATER_EQUAL:
				conditionProvider.greaterEqual(fieldName, value);
				break;
			case LESS_THAN:
				conditionProvider.lessThan(fieldName, value);
				break;
			case LESS_EQUAL:
				conditionProvider.lessEqual(fieldName, value);
				break;
			case NOT_EQUAL:
				conditionProvider.notEqual(fieldName, value);
				break;
			case IN:
				conditionProvider.in(fieldName, value);
				break;
			case NOT_IN:
				conditionProvider.notIn(fieldName, value);
				break;
			case IS_NULL:
				conditionProvider.isNull(fieldName);
				break;
			case IS_NOT_NULL:
				conditionProvider.isNotNull(fieldName);
				break;
			case UP_GREATER_THAN:
				conditionProvider.upGreaterThanZero(fieldName, value);
				break;
			case UP_GREATER_EQUAL:
				conditionProvider.upGreaterEqualZero(fieldName, value);
				break;
			case REDUCE_GREATER_THAN:
				conditionProvider.reduceGreaterThanZero(fieldName, value);
				break;
			case REDUCE_GREATER_EQUAL:
				conditionProvider.reduceGreaterEqualZero(fieldName, value);
				break;
			default:
				throw new HandleException("error: filter annotation invalid");
		}

		return true;
	}
	
	public static final void getUpdateField(Object object, UpdateProvider updateProvider) {
		getUpdateField(object, object.getClass(), updateProvider);
	}
	
	private static void getUpdateField(Object object, Class<?> cls, UpdateProvider updateProvider) {
		Field[] fields = cls.getDeclaredFields();
		
		for (Field field : fields) {
			boolean isStatic = Modifier.isStatic(field.getModifiers());
			if (isStatic) {
				continue;
			}

			/**
			 * 进行条件的连带处理
			 */
//			setFilter(object, updateProvider, field);
			UpdateField updateField = field.getAnnotation(UpdateField.class);
			if (updateField == null) {
				continue;
			}

			
			String fieldName = !ValidateTool.isEmpty(updateField.name()) ? updateField.name() : field.getName();
			field.setAccessible(true);
			Object value = null;
			try {
				value = field.get(object);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(!updateField.isnull() && ValidateTool.isEmpty(value)) {
				continue;
			}
			
			SqlHandleEnum type = updateField.type();
			switch (type) {
			case HANDLE_DEFAULT:
				/**
				 * 常规类型操作
				 */
				updateProvider.set(fieldName, value);
				break;
			case HANDLE_UP:
				/**
				 * 累加
				 */
				updateProvider.up(fieldName, value);
				break;
			case HANDLE_REDUCE:
				/**
				 * 累加
				 */
				updateProvider.reduce(fieldName, value);
				break;
			default:
				throw new HandleException("error: update annotation invalid");
			}
		}
		
		Class<?> supClas = cls.getSuperclass();
		if(supClas != null) {
			getUpdateField(object, supClas, updateProvider);
		}
	}

}
