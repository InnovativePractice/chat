package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.Friend;
import com.bean.User;
import com.dao.FriendDao;
import com.dao.UserDao;

/**
 * Servlet implementation class login
 */
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        User user = userDao.findByName(request.getParameter("UserId"));
        if (user != null && user.getPassword().equals(request.getParameter("Password"))) {
            ArrayList<Friend> friends = new FriendDao().findByName(user.getUserId());
            request.getSession().setAttribute("friends", friends);
            request.getSession().setAttribute("user", user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("chat.jsp");
            requestDispatcher.forward(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("failed.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
