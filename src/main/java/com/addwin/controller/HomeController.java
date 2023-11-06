package com.addwin.controller;

import com.addwin.dao.TodoDao;
import com.addwin.model.Todo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HomeController extends HttpServlet {
    private final TodoDao todoDao;

    public HomeController() {
        this.todoDao = new TodoDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        if(userId!=null){
            todoDao.deleteTodo(Integer.parseInt(userId));
        }
        doPost(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("home.jsp");
        String userId = req.getSession().getAttribute("userId").toString();

        String item = req.getParameter("item");
        if(item!=null && !item.trim().isEmpty()){
            todoDao.addTodo(Integer.parseInt(userId),item);
        }
        List<Todo> todos = todoDao.selectAllTodo(Integer.parseInt(userId));
        System.out.println(todos.size());
        req.setAttribute("todos",todos);
        dispatcher.forward(req, resp);
    }
}
