package xyz.chenmt.www.chenmtrides.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    //swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
//                .globalOperationParameters(new ArrayList<Parameter>() {
//                    private static final long serialVersionUID = 1L;
//
//                    {
//                        add(new ParameterBuilder().name("accessToken")
//                                .description("请求可能需要在HTTP header中加入token。\r\n请填写\"Bearer {token}\"")
//                                .modelRef(new ModelRef("string"))
//                                .parameterType("header")
//                                .required(false)
//                                .build());
//                    }
//                })
                .enable(swaggerEnabled)
                .select()
                .apis(RequestHandlerSelectors.basePackage("xyz.chenmt.www"))
                .paths(PathSelectors.any())
                .build().pathMapping("/");
    }

    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("redis学习")
                //描述
                .description("redis学习api")
                //创建人
                .contact(new Contact("chenmt", "", ""))
                //版本号
                .version("1.0")
                //描述
                .build();
    }

}
