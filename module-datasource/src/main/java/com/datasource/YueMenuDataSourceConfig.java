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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * shanyou多数据源
 * @author Administrator
 * @Desc
 */
@Configuration
@MapperScan(basePackages = "com.mapper.yuemenu.mapper", sqlSessionFactoryRef = "YueMenuDataSourceFactory")
public class YueMenuDataSourceConfig {

    @Value("${spring.datasource.yuemenu.driver-class-name}")
    private String driverClassName;

    @Value("${spring.datasource.yuemenu.url}")
    private String url;

    @Value("${spring.datasource.yuemenu.username}")
    private String username;

    @Value("${spring.datasource.yuemenu.password}")
    private String password;

    @Bean(name = "YueMenuDataSource")
    public DataSource yueMenuDataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean(name = "YueMenuDataSourceFactory")
    public SqlSessionFactory yueMenuDataSourceFactory(@Qualifier(value = "YueMenuDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/yuemenu/*.xml"));// 设置mybatis的xml所在位置
        return bean.getObject();
    }

    @Bean(name = "YueMenuDataSourceTemplate")
    public SqlSessionTemplate yueMenuDataSourceTemplate(@Qualifier(value = "YueMenuDataSourceFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
}
