package com.xmut.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author
 * @date: 2023/4/24
 **/
@Configuration//声明当前类是一个配置类
@ComponentScan("com.xmut.service") //开启注解扫描
@Import({MyBatisConfig.class,JdbcConfig.class}) //导入其他配置
@EnableTransactionManagement  //事务管理
public class SpringConfig {

    public DataSourceTransactionManager transactionManager(DataSource dataSource){
        DataSourceTransactionManager transactionManager=new DataSourceTransactionManager();
        //配置数据源
        transactionManager.setDataSource(dataSource);

        return transactionManager;

    }

}
