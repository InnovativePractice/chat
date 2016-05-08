package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Message;
import com.bean.User;
import com.dao.MessageDao;


public class SendMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Message message = new Message();
        PrintWriter out = response.getWriter();
        try {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            message.setMessage(request.getParameter("Message"));
            message.setReceiverId(request.getParameter("ReceiverId"));
            message.setSenderId(((User) request.getSession().getAttribute("user")).getUserId());
            message.setTime(new Date((new java.util.Date()).getTime()));
            (new MessageDao()).save(message);
            out.println("success");
        } finally {
            out.close();
        }
    }

}
