package com.lynu.yzshopping.util;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 自定义返回json 让为null的值和时间戳的值能有正确的格式 不然fastjson会过滤空值和返回默认的时间戳
 * //参考：https://www.jianshu.com/p/45682cd30ca2
 */
public class FastJsonHttpMessageConverterEx extends FastJsonHttpMessageConverter {
    public FastJsonHttpMessageConverterEx() {
        // 配置 fastjson 特性
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");    // 自定义时间格式
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue); // 正常转换 null 值
        this.setFastJsonConfig(fastJsonConfig);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }

}
