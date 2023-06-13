package com.xmut.config;

import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author
 * @date: 2023/4/24
 **/

@MapperScan("com.xmut.mapper") //扫描mapper映射交给Spring管理
public class MyBatisConfig {
    //配置分页插件拦截器
    @Bean //第三方插件装载spring容器
    public PageInterceptor pageInterceptor(){
        PageInterceptor pageInterceptor=new PageInterceptor();

        //分页插件配置
        Properties properties=new Properties();
        properties.setProperty("value","true");//开启分页
        pageInterceptor.setProperties(properties);

        return pageInterceptor;
    }
    @Autowired
    private PageInterceptor pageInterceptor;//分页拦截器注入

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.xmut.pojo");//配置包扫描

        //将分页拦截器插件交给Mybatis
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor});

        return sqlSessionFactoryBean;
    }
}
