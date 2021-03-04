package com.obatis.db.autoconfigure;

import com.obatis.config.response.result.ResultInfo;
import com.obatis.db.annotation.Table;
import com.obatis.db.convert.BeanCacheConvert;
import com.obatis.db.mapper.factory.BeanSessionMapperFactory;
import com.obatis.db.mapper.factory.ResultSessionMapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.Set;

@Order(-99999)
public class BeanHandleAutoConfiguration {

	@Resource
	private SqlSession sqlSession;

	@Bean
	public int onApplicationEvent() {

		Reflections reflections = new Reflections(StartupAutoConfiguration.PROJECT_BASE_DIR);

        /**
         * 将注解的表加载到缓存
         */
		Set<Class<?>> tableClassList = reflections.getTypesAnnotatedWith(Table.class);
		for (Class<?> cls : tableClassList) {
			BeanCacheConvert.loadEntityCache(cls);
			BeanSessionMapperFactory.compileMapper(sqlSession, cls.getCanonicalName());
		}

		Set<Class<? extends ResultInfo>> resultInfoClassList = reflections.getSubTypesOf(ResultInfo.class);
		for (Class<? extends ResultInfo> cls : resultInfoClassList) {
			ResultSessionMapperFactory.compileMapper(sqlSession, cls.getCanonicalName());
		}

		return 0;
	}

}
