///*
// * Copyright (c) 2006-2017, Yunnan Sanjiu Network technology Co., Ltd.
// *
// * All rights reserved.
// */
//package com.hui.user_service.common.config.mybatis;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.baomidou.mybatisplus.MybatisConfiguration;
//import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
//import com.baomidou.mybatisplus.entity.GlobalConfiguration;
//import com.baomidou.mybatisplus.enums.DBType;
//import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
//import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
//import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
//import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
//import com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS;
//import com.hui.user_service.common.utils.DateTimeUtils;
//import org.apache.ibatis.mapping.DatabaseIdProvider;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.ResourceLoader;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//import org.springframework.core.env.Environment;
//
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.TimeZone;
//
///**
// * Created by Lucare.Feng on 2017/2/24.
// */
//@EnableConfigurationProperties(MybatisProperties.class)
//@Configuration
//public class MybatisPlusConfig {
//
//    private final static Logger logger = LoggerFactory.getLogger(MybatisPlusConfig.class);
//
//    @Autowired
//    private DataSource dataSource;
//
//    @Autowired
//    private MybatisProperties properties;
//
//    @Autowired
//    private Environment environment;
//    private RelaxedPropertyResolver propertyResolver;
//
//    @Autowired
//    private ResourceLoader resourceLoader = new DefaultResourceLoader();
//
//    @Autowired(required = false)
//    private Interceptor[] interceptors;
//
//    @Autowired(required = false)
//    private DatabaseIdProvider databaseIdProvider;
//
//    /**
//     * mybatis-plus分页插件
//     */
//    @Bean
//    public PaginationInterceptor paginationInterceptor() {
//        return new PaginationInterceptor();
//    }
//
//    @Bean
//    public DruidDataSource druidDataSource() throws SQLException {
//        this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
//
//        System.out.println("====================注入druid!====================");
//        DruidDataSource datasource = new DruidDataSource();
//        datasource.setUrl(propertyResolver.getProperty("url"));
//        datasource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
//        datasource.setUsername(propertyResolver.getProperty("username"));
//        datasource.setPassword(propertyResolver.getProperty("password"));
//        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("initial-size")));
//        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("min-idle")));
//        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("max-wait")));
//        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("max-active")));
//        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("min-evictable-idle-time-millis")));
//        try {
//            datasource.setFilters(propertyResolver.getProperty("filters"));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return datasource;
//    }
//    /**
//     * MP 全局配置，更多内容进入类看注释
//     */
//    @Bean
//    public GlobalConfiguration getGlobalConfig() {
//        GlobalConfiguration globalConfig = new GlobalConfiguration(new LogicSqlInjector());
//        // ID 策略 AUTO->`0`("数据库ID自增")
//        // INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
//        globalConfig.setIdType(2);
//        globalConfig.setDbType(DBType.MYSQL.name());
//        globalConfig.setLogicNotDeleteValue("0");
//        globalConfig.setLogicDeleteValue("1");
//        globalConfig.setMetaObjectHandler(new MetaObjectHandler() {
//
//            @Override
//            public void insertFill(MetaObject metaObject) {
//                // Object createTime = getFieldValByName("createTime",
//                // metaObject);
//                // if (createTime == null) {
//
//                logger.debug("----------------------------------------------------------------------");
//                logger.debug("new date创建时间为:{}", new Date());
////                logger.debug("创建时间为:{}", Date.localDate());
//                logger.debug("----------------------------------------------------------------------");
//                // setFieldValByName("createTime", DateTimeUtils.localDate(),
//                // metaObject);
//
//                if (!DateTimeUtils.DEFAULT_TZ.equals(TimeZone.getDefault().getID())) {
//                    TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.DEFAULT_TZ));
//                    System.setProperty("user.timezone", DateTimeUtils.DEFAULT_TZ);
//                    logger.error("时区发生切换，已经自动切回默认时区GMT+8");
//                }
//
//                if (!DateTimeUtils.DEFAULT_TZ.equals(System.getProperty("user.timezone"))) {
//                    TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.DEFAULT_TZ));
//                    System.setProperty("user.timezone", DateTimeUtils.DEFAULT_TZ);
//                    logger.error("时区发生切换，已经自动切回默认时区GMT+8");
//                }
//
//                // }
//            }
//
//            @Override
//            public void updateFill(MetaObject metaObject) {
//                logger.debug("----------------------------------------------------------------------");
//                logger.debug("更新时间为:{}", DateTimeUtils.localDate());
//                logger.debug("new date更新时间为:{}", new Date());
//                logger.debug("----------------------------------------------------------------------");
//                // setFieldValByName("updateTime", DateTimeUtils.localDate(),
//                // metaObject);
//
//                if (!DateTimeUtils.DEFAULT_TZ.equals(TimeZone.getDefault().getID())) {
//                    TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.DEFAULT_TZ));
//                    System.setProperty("user.timezone", DateTimeUtils.DEFAULT_TZ);
//                    logger.error("时区发生切换，已经自动切回默认时区GMT+8");
//                }
//
//                if (!DateTimeUtils.DEFAULT_TZ.equals(System.getProperty("user.timezone"))) {
//                    TimeZone.setDefault(TimeZone.getTimeZone(DateTimeUtils.DEFAULT_TZ));
//                    System.setProperty("user.timezone", DateTimeUtils.DEFAULT_TZ);
//                    logger.error("时区发生切换，已经自动切回默认时区GMT+8");
//                }
//
//            }
//
//        });
//
//        return globalConfig;
//    }
//
//    /**
//     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源。不手动指定 配置文件和mybatis-boot的配置文件同步
//     *
//     * @return
//     */
////    @Bean
////    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
////        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
////        mybatisPlus.setDataSource(dataSource);
////        mybatisPlus.setVfs(SpringBootVFS.class);
////        if (StringUtils.hasText(this.properties.getConfigLocation())) {
////            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
////        }
////        mybatisPlus.setConfiguration(properties.getConfiguration());
////        if (!ObjectUtils.isEmpty(this.interceptors)) {
////            mybatisPlus.setPlugins(this.interceptors);
////        }
////
////        mybatisPlus.setGlobalConfig(getGlobalConfig());
////        MybatisConfiguration mc = new MybatisConfiguration();
////        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
////        mybatisPlus.setConfiguration(mc);
////        if (this.databaseIdProvider != null) {
////            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
////        }
////        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
////            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
////        }
////        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
////            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
////        }
////        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
////            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
////        }
////        return mybatisPlus;
////    }
//
//    @Bean
//    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
//        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
//        mybatisPlus.setDataSource(dataSource);
//        mybatisPlus.setVfs(SpringBootVFS.class);
//        if (StringUtils.hasText(this.properties.getConfigLocation())) {
//            mybatisPlus.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
//        }
//        mybatisPlus.setConfiguration(properties.getConfiguration());
//        if (!ObjectUtils.isEmpty(this.interceptors)) {
//            mybatisPlus.setPlugins(this.interceptors);
//        }
//        mybatisPlus.setGlobalConfig(getGlobalConfig());
//        MybatisConfiguration mc = new MybatisConfiguration();
//        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
//        mybatisPlus.setConfiguration(mc);
//        if (this.databaseIdProvider != null) {
//            mybatisPlus.setDatabaseIdProvider(this.databaseIdProvider);
//        }
//        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
//            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
//        }
//        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
//            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
//        }
//        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
//            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
//        }
//        return mybatisPlus;
//    }
//}
