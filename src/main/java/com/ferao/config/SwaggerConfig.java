package com.ferao.config;/*
 * @author Ferao
 * @date
 * @discription
 */
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
/**
 * 访问地址：localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2  //开启swagger2
public class SwaggerConfig {
    //配置swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev", "debug");
        //通过environment.acceptsProfiles判断是否处于自己设定的环境中
        boolean flag = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Ferao-group")
                .apiInfo(apiInfo())
                .enable(flag)
                .select()
                //配置要扫描的接口
                .apis(RequestHandlerSelectors.basePackage("com.ferao.controller"))
                .paths(PathSelectors.ant("/users/**"))
                .build();
    }

    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("group1");
    }

    //配置swagger信息=apiInfo
    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("Ferao", "https://blog.csdn.net/qq_21561501", "928971634@qq.com");

        return new ApiInfo(
                "Ferao的swaggerAPI文档",
                "11",
                "v1.0",
                "https://blog.csdn.net/qq_21561501",
                contact,
                "Apache2.0",
                "https://blog.csdn.net/qq_21561501",
                new ArrayList()
        );
    }
}
