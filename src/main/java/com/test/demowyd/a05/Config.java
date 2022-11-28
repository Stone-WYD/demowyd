package com.test.demowyd.a05;

import com.alibaba.druid.pool.DruidDataSource;
import com.test.demowyd.a05.component.Bean2;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.test.demowyd.a05.component")
public class Config {

    @Bean
    public Bean1 bean1(){return new Bean1();}

    public Bean2 bean2(){
        return new Bean2();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource){
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }

    @Bean(initMethod = "init")
    public DruidDataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:8888/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }
}
