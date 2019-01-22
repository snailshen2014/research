package com.syj.velocity.servlet;

import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.view.servlet.VelocityViewServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author snailshen
 * @description ${description}
 * @create 2019-01-17 15:36
 */
public class MyVelocityViewServlet extends VelocityViewServlet {
    @Override
    protected Template handleRequest(HttpServletRequest request, HttpServletResponse response, Context ctx) {
        //往context容器存放变量
        ctx.put("fullName","snailshen");
        //也可往request中存值
        request.setAttribute("anotherName","snail");
        //forward到指定模版
        try {
            return getTemplate("test.vm");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
