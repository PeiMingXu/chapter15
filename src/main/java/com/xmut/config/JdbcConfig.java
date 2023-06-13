package com.xmut.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

/**
 * @author
 * @date: 2023/4/24
 **/
@PropertySource("classpath:jdbc.properties") //读取外部文件
public class JdbcConfig {
    @Value("${jdbc.url}")//读取外部配置
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    //创建数据源
    @Bean //将方法的返回值装配到Spring容器
    public DataSource dataSource(){
        DruidDataSource dataSource=new DruidDataSource();

        //设置连接参数
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);

        return dataSource;
    }
}
