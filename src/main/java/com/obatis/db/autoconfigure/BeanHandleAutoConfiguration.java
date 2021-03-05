package com.obatis.db.autoconfigure;

import com.obatis.config.response.result.ResultInfo;
import com.obatis.db.annotation.Table;
import com.obatis.db.convert.BeanCacheConvert;
import com.obatis.db.mapper.factory.BeanSessionMapperFactory;
import com.obatis.db.mapper.factory.ResultSessionMapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;

import java.util.Set;

public class BeanHandleAutoConfiguration {

    private static boolean CREATE_BEAN_FLAG = false;

    @Bean
	public int beanHandleAutoConfiguration(SqlSession sqlSession) {
        if(!CREATE_BEAN_FLAG) {
            crateBeanInfoHandle(sqlSession);
        }

        return 0;
	}

    /**
     * 构建bean info
     * @param sqlSession
     */
	public static synchronized void crateBeanInfoHandle(SqlSession sqlSession) {

        if(CREATE_BEAN_FLAG) {
            return;
        }

        CREATE_BEAN_FLAG = true;

        Reflections reflections = new Reflections(EnvironmentPrepareAutoConfiguration.PROJECT_BASE_DIR);

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
    }
}
