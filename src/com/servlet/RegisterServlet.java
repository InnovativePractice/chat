package com.servlet;

import com.bean.Friend;
import com.bean.User;
import com.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Administrator
 * @since 2016/5/8
 */
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // set encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 获取变量
        UserDao userDao = new UserDao();
        String id = request.getParameter("userid"),
                password = request.getParameter("password");
        User user = new User();
        user.setUserId(id);
        user.setPassword(password);

        // 检查重名
        if (userDao.findByName(id) != null) {
            // TODO: 2016/5/8 重名提示代码
            return;
        }

        // 添加用户
        if (userDao.add(user)) {
            request.getSession().setAttribute("friends", new ArrayList<Friend>());
            request.getSession().setAttribute("user", user);
            RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
            dispatcher.forward(request, response);
        } else {
            // TODO: 2016/5/8 注册失败提示代码
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
