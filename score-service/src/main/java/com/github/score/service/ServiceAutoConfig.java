package com.github.score.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author haipeng_lin
 * @Mailbox haipeng_lin@163.com
 * @Date 2024/5/25 9:50
 * @Description 业务自动配置
 */

@Configuration
@ComponentScan("com.github.score.service")
//@MapperScan(basePackages = {)
public class ServiceAutoConfig {
}

