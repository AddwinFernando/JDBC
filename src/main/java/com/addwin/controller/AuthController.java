package com.addwin.controller;

import com.addwin.dao.UserDao;
import com.addwin.db.DbConnection;
import com.addwin.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthController extends HttpServlet {
    UserDao userdao;
    public AuthController() {
      userdao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usename = req.getParameter("username");
        String password = req.getParameter("password");

        User loggedinUser = userdao.loginUser(usename,password);

        if(loggedinUser != null){
            HttpSession session = req.getSession();
            session.setAttribute("userId",loggedinUser.getId());
            RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
            dispatcher.forward(req,resp);
        } else {
            req.setAttribute("error",true);
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
            dispatcher.forward(req,resp);
        }

    }
}
