package com.xmut.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author
 * @date: 2023/4/25
 **/
@WebFilter("/*") //拦截过滤所有的请求
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //乱码处理
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
