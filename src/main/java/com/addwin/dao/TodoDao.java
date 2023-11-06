package com.addwin.dao;

import com.addwin.db.DbConnection;
import com.addwin.model.Todo;
import com.addwin.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private final Connection connection;

    private  final String INSERT_TODO = "INSERT INTO todo(userid,item) VALUES (?,?);";
    private final String SELECT_ALL = "SELECT id,item,userid FROM todo WHERE userid=?;";

    private final String DELETE_TODO = "DELETE FROM todo WHERE id=?;";

    public TodoDao() {
        this.connection = DbConnection.getconnection();
    }

    public List<Todo> selectAllTodo(int userId){
        List <Todo> todos = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
            statement.setInt(1,userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Todo todo = new Todo();
                todo.setUserId(rs.getInt("userid"));
                todo.setTodo(rs.getString("item"));
                todo.setId(rs.getInt("id"));
                System.out.println("in");
                todos.add(todo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }
    public void addTodo(int userId,String todo){
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_TODO);
            statement.setInt(1,userId);
            statement.setString(2,todo);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodo(int userId){
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_TODO);
            statement.setInt(1,userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
