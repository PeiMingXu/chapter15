package com.xmut.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author
 * @date: 2023/4/24
 **/
public class ServletContainerInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class}; //spring配置
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { //springmvc配置
        return new Class[]{SpringMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {//servlet映射路径
        return new String[]{"/"};
    }
}
