package com.example.servlet_filter_listener;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
@WebFilter("/*")
public class MyFiter implements Filter {
    @Override
    public void init(FilterConfig filterConfig){
        System.out.println("MyFilter>>>init");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("MyFilter>>>doFilter");
        try {
            chain.doFilter(request,response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void destroy() {
        System.out.println("MyFilter>>>destroy");
    }
}
