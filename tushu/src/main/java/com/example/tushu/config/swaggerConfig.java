package com.example.tushu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 //开启了swagger2
@EnableWebMvc
public class swaggerConfig implements WebMvcConfigurer {
    //配置了swagger的Docker的bean实例

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //包下的类，生成接口文档
                .build();
    }

    //配置Swagger信息
    private ApiInfo apiInfo() {
//        作者信息
        Contact contact = new Contact("grc", "https://www.baidu.com", "30123@qq.com");
        return new ApiInfoBuilder()
                .contact(contact)
                .title("图书售卖系统")
                .description("网上售卖图书")
                .termsOfServiceUrl("https://www.baidu.com")
                .version("1.0")
                .build();
    }
}