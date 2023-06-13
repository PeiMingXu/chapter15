package com.xmut.config;


import com.xmut.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * @author
 * @date: 2023/4/24
 **/
@Configuration//声明当前类是一个配置类
@ComponentScan("com.xmut.controller") //注解扫描
@PropertySource("classpath:ignoreUrl.properties")//读取文件
@EnableWebMvc
public class SpringMvcConfig implements WebMvcConfigurer {
    @Value("#{'${ignoreUrl}'.split(',')}")
    private  List<String> ignoreUrl;

    //开启静态资源的释放
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //配置视图解析器

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/admin/",".jsp");
    }
    //配置拦截器，重写方法addInterceptors

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ResourceInterceptor(ignoreUrl))//调用ResourceInterceptor
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/img/**","/js/**");//排除一些路径
    }
}
