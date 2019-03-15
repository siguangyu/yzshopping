package com.lynu.yzshopping.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: data-interface
 * @description:
 * @author: houyu
 * @create: 2018-12-06 16:16
 */
public class MvcUtil {

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return (ip == null || ip.length() == 0 ) ? request.getRemoteAddr() : ip;
    }


    /**
     * 返回数据
     * @throws IOException
     */
    public static void responseReturnData(HttpServletResponse response, String data) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=" + "UTF-8");
        response.getWriter().write(data);
    }




    /**--------------------------------------------------------------------------------------*/
    private static class SingletonHolder {
        private static final MvcUtil INSTANCE = new MvcUtil();
    }

    public static MvcUtil get() {
        return SingletonHolder.INSTANCE;
    }
    /**--------------------------------------------------------------------------------------*/

}
