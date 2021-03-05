package com.obatis.db.mapper.factory;

import com.obatis.db.autoconfigure.BeanHandleAutoConfiguration;
import com.obatis.db.constant.CacheInfoConstant;
import com.obatis.db.mapper.BaseBeanSessionMapper;
import com.obatis.db.mapper.BaseResultSessionMapper;
import com.obatis.exception.HandleException;
import org.apache.ibatis.session.SqlSession;

public class ResultSessionMapperFactory {
	
	private ResultSessionMapperFactory() {}

	/**
	 * 获取 mapper
	 * @param sqlSession
	 * @param canonicalName
	 * @return
	 */
	public static BaseResultSessionMapper<?> getSessionMapper(SqlSession sqlSession, String canonicalName) {

		if(getSessionMapper(canonicalName) == null) {
			createSessionMapper(sqlSession, canonicalName);
		}

		BaseResultSessionMapper<?> sessionMapper = getSessionMapper(canonicalName);
		if(sessionMapper == null) {
			throw new HandleException("error: result sessionMapper is null");
		}

		return sessionMapper;
	}

	private static BaseResultSessionMapper<?> getSessionMapper(String canonicalName) throws HandleException {

		if(CacheInfoConstant.RESULT_SESSION_MAPPER.containsKey(canonicalName)) {
			return CacheInfoConstant.RESULT_SESSION_MAPPER.get(canonicalName);
		}

		return null;
	}

	private static synchronized void createSessionMapper(SqlSession sqlSession, String canonicalName) {
		if(!CacheInfoConstant.RESULT_SESSION_MAPPER.containsKey(canonicalName)) {
			BeanHandleAutoConfiguration.crateBeanInfoHandle(sqlSession);
		}
	}
	
	public static synchronized void compileMapper(SqlSession sqlSession, String canonicalName) {
		
		if(CacheInfoConstant.RESULT_SESSION_MAPPER.containsKey(canonicalName)) {
			return;
		}

		compileMapperHandle(sqlSession, canonicalName);
	}

	private static synchronized void compileMapperHandle(SqlSession sqlSession, String canonicalName) {

		if(CacheInfoConstant.RESULT_SESSION_MAPPER.containsKey(canonicalName)) {
			return;
		}

		Class<?> mapperCls;
		try {
			mapperCls = SessionMapperCompilerTemplet.compilerMapper(canonicalName, BaseResultSessionMapper.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new HandleException("error: compilerMapper is fail");
		}

		if(mapperCls == null) {
			throw new HandleException("error: compilerMapper is fail");
		}

		sqlSession.getConfiguration().addMapper(mapperCls);
		BaseResultSessionMapper<?> resultMapper = (BaseResultSessionMapper<?>) sqlSession.getConfiguration().getMapper(mapperCls, sqlSession);
		if(resultMapper == null) {
			throw new HandleException("error: compilerMapper is fail");
		}
		CacheInfoConstant.RESULT_SESSION_MAPPER.put(canonicalName, resultMapper);
	}
}
