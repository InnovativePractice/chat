package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Message;
import com.bean.User;
import com.dao.MessageDao;


public class AskNewMessageServler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date lastUpdataTime = (Date) request.getSession().getAttribute("lastUpdataTime");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html");
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            if (lastUpdataTime == null || request.getParameter("IsNew").equals("1")) {
                lastUpdataTime = new Date((new java.util.Date()).getTime() - (long) (48 * 60 * 60 * 1000));
            }
            ArrayList<Message> messages = (new MessageDao()).findAfterTime(lastUpdataTime, ((User) (request.getSession().getAttribute("user"))).getUserId(), request.getParameter("SenderId"));
            Message message = new Message();
            if (messages != null && messages.size() != 0) {
                for (Message message1 : messages) {
                    message = message1;
                    out.write("<font style=\"color:#808080;font-size:10px;\">" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(message.getTime()) + "</font><br/>");
                    out.write("<font style=\"color:#808080;font-size:10px;\">" + message.getSenderId() + "</font><br/>");
                    out.write("<font style=\"background-color:CDD7E2\">" + message.getMessage() + "</font><br/><br/>");
                }
                lastUpdataTime = message.getTime();
            }
            request.getSession().setAttribute("lastUpdataTime", lastUpdataTime);
        }
    }

}
