package com.xmut.interceptor;

import com.xmut.pojo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author
 * @date: 2023/4/25
 **/
public class ResourceInterceptor implements HandlerInterceptor {
    //任意角色都能访问的路径
    private List<String> urls;
    public ResourceInterceptor(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取请求的路径
        String requestURI = request.getRequestURI();

        //对用户登录的相关请求，放行
        if (requestURI.indexOf("login")>0){
            return true;
        }

        //如果用户已经登陆直接放行
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        //如果用户是已登录状态，判断访问的资源是否有权限
        if (user!=null){
                //如果是管理员，放行
                if ("ADMIN".equals(user.getRole())){
                    return true;//如果是管理员，放行
                }
                // //如果是普通用户
                // else{
                //        //如果不是管理员，普通用户需要判断请求是否能访问
                //         for (String url:urls){
                //             //访问的资源不是管理员权限的资源，放行
                //             System.out.println("url=="+url);
                //             if (requestURI.indexOf(url)>0){
                //                 return true;
                //             }
                //         }
                //     //如果没有操作权限，则需要跳转到登陆页面
                //     request.setAttribute("msg","您没有权限，请用管理员登陆");//提示信息
                //     request.getRequestDispatcher("/admin/login.jsp").forward(request,response);//内部页面跳转
                //     return false;
                // }
            return true;

        }

        //如果不是登陆相关的功能，则需要跳转到登陆页面
        request.setAttribute("msg","您还未登录，请先登陆");//提示信息
        request.getRequestDispatcher("/admin/login.jsp").forward(request,response);//内部页面跳转
        return false;
    }
}
