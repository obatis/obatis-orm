package com.obatis.db.autoconfigure;

import com.obatis.exception.HandleException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class EnvironmentPrepareAutoConfiguration implements ImportBeanDefinitionRegistrar {

    protected static String PROJECT_BASE_DIR = null;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        String startupClassName = annotationMetadata.getClassName();
        if(!startupClassName.contains(".")) {
            /**
             * 说明启动类在缺省目录下，直接返回
             * 说明启动类在缺省目录下
             */
            throw new HandleException("项目启动类不允许在缺省目录下");
        }
        PROJECT_BASE_DIR = startupClassName.substring(0, startupClassName.lastIndexOf("."));
    }
}
