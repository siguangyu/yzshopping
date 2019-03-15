package configration;


import com.lynu.yzshopping.interceptor.FrequencyInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: data-interface
 * @description: Mvc配置
 * @author: houyu
 * @create: 2018-12-06 16:34
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    FrequencyInterceptor frequencyInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(frequencyInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login");
        // registry.addInterceptor(frequencyInterceptor).addPathPatterns("/hello/**");
        registry.addInterceptor(frequencyInterceptor).addPathPatterns("/data-interface/**");
    }
}
