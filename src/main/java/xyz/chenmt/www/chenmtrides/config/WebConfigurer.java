package xyz.chenmt.www.chenmtrides.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xyz.chenmt.www.chenmtrides.interceptor.SystemLogInterceptor;

/**
 * @program chenmt-rides
 * @description:
 * @author: chenmet
 * @create: 2019/08/08 09:54
 */
@Configuration
public class WebConfigurer  implements WebMvcConfigurer {

    @Autowired
    SystemLogInterceptor systemLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(systemLogInterceptor).addPathPatterns("/**");
    }

}
