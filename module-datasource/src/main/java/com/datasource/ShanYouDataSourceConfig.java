package com.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * shanyou多数据源
 * @author Administrator
 * @Desc
 */
@Configuration
@MapperScan(basePackages = "com.mapper.shanyou.mapper", sqlSessionFactoryRef = "ShanYouDataSourceFactory")
public class ShanYouDataSourceConfig {

    @Value("${spring.datasource.shanyou.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.shanyou.url}")
    private String url;

    @Value("${spring.datasource.shanyou.username}")
    private String username;

    @Value("${spring.datasource.shanyou.password}")
    private String password;

    @Bean(name = "ShanYouDataSource")
    // 表示这个数据源是默认数据源
    @Primary//这个一定要加，如果两个数据源都没有@Primary会报错
    public DataSource shanYouDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean(name = "ShanYouDataSourceFactory")
    // 表示这个数据源是默认数据源
    @Primary//这个一定要加，如果两个数据源都没有@Primary会报错
    public SqlSessionFactory shanYouDataSourceFactory(@Qualifier(value = "ShanYouDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/shanyou/*.xml"));// 设置mybatis的xml所在位置
        return bean.getObject();
    }

    @Bean(name = "ShanYouDataSourceTemplate")
    // 表示这个数据源是默认数据源
    @Primary//这个一定要加，如果两个数据源都没有@Primary会报错
    public SqlSessionTemplate shanYouDataSourceTemplate(@Qualifier(value = "ShanYouDataSourceFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
