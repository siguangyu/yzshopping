package com.lynu.yzshopping.interceptor;




import com.alibaba.fastjson.JSONObject;
import com.lynu.yzshopping.mybatis.entity.Result;
import com.lynu.yzshopping.mybatis.entity.VisitLog;
import com.lynu.yzshopping.service.VisitLogServer;
import com.lynu.yzshopping.util.MvcUtil;
import com.lynu.yzshopping.util.ResultHandle;
import com.lynu.yzshopping.util.SimpleTimeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: data-interface
 * @description: 频率控制拦截器
 * @author: houyu
 * @create: 2018-12-06 16:30
 */
@Component
public class FrequencyInterceptor implements HandlerInterceptor {

    @Autowired
    private VisitLogServer visitLogServer;

    private SimpleTimeCache simpleTimeCache = new SimpleTimeCache(200, 3000);// 设置3秒内不能重新访问

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("-----------preHandle-----------");

        String ipAddress = MvcUtil.getIpAddress(request);                                                   // IP
        String uri = request.getRequestURI();                                                               // 资源名称
        String browser = request.getHeader("User-Agent");                                            // 浏览器类型
        String paramString = JSONObject.toJSONString(request.getParameterMap());                            // 获取请求参数

       /* if(simpleTimeCache.get(ipAddress) != null){                                                           // 5秒内有记录,不允许访问
            MvcUtil.responseReturnData(response, JSONObject.toJSONString(ResultHandle.getFrequentlyResult()));    // 返回提示
            visitLogServer.save(new VisitLog(ipAddress, uri, paramString, browser, Result.Code.FREQUENTLY));
            simpleTimeCache.put(ipAddress, "");                                                               // 更新访问的时间
            return false;
        }*/

        simpleTimeCache.put(ipAddress, "");                                                                   // 更新访问的时间
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.println("-----------postHandle-----------");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        System.out.println("-----------afterCompletion-----------");

        String ipAddress = MvcUtil.getIpAddress(request);                                                           // IP
        String uri = request.getRequestURI();                                                                       // 资源名称
        String browser = request.getHeader("User-Agent");                                                    // 浏览器类型
        String paramString = JSONObject.toJSONString(request.getParameterMap());                                    // 获取请求参数

        if (ex == null){
            visitLogServer.save(new VisitLog(ipAddress, uri, paramString, browser, Result.Code.SUCCESS));           // 存入数据库
        }else {
            visitLogServer.save(new VisitLog(ipAddress, uri, paramString, browser, Result.Code.SERVER_ERROR));
            // MvcUtil.responseReturnData(response, JSONObject.toJSONString(ResultHandle.getServerErrorResult()));  // 返回提示
        }
    }

}
