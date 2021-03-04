package com.obatis.db.autoconfigure;

import com.obatis.db.annotation.Table;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.Resource;
import java.util.Map;

@Configuration
public class BeanHandleAutoConfiguration implements ImportBeanDefinitionRegistrar {

	@Resource
	private SqlSession sqlSession;

//	@Bean
//	public int onApplicationEvent() {
//
//		/**
//		 * 将注解的表加载到缓存
//		 */
//		Set<Class<?>> classList = reflections.getTypesAnnotatedWith(Table.class);
//		for (Class<?> cls : classList) {
//			BeanCacheConvert.loadEntityCache(cls);
//			BeanSessionMapperFactory.compileMapper(sqlSession, cls.getCanonicalName());
//		}
//
//		Set<Class<? extends ResultInfo>> resultOutputClass = reflections.getSubTypesOf(ResultInfo.class);
//		for (Class<? extends ResultInfo> cls : resultOutputClass) {
//			ResultSessionMapperFactory.compileMapper(sqlSession, cls.getCanonicalName());
//		}
//
//		return 0;
//	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
		Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(Table.class.getName(), true);
		System.out.println(annotationAttributes);
	}
}
