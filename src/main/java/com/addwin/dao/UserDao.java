package com.addwin.dao;

import com.addwin.db.DbConnection;
import com.addwin.model.User;

import java.sql.*;

public class UserDao {
    private final Connection connection;

    private final String LOGIN_QUERY = "SELECT id,username,password FROM auth WHERE password=? AND username=?; ";
    private  final String INSERT_USER = "INSERT INTO auth(id,username,password) VALUES (?,?,?);";

    public UserDao() {
        connection = DbConnection.getconnection();
    }


    public User loginUser(String usename, String password) {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(LOGIN_QUERY);
            statement.setString(1, usename);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public void addUser(String username, String password) {
        try {
            int id = (int) (Math.random()*100);
            PreparedStatement statement = connection.prepareStatement(INSERT_USER);
            statement.setInt(1,id);
            statement.setString(2,username);
            statement.setString(3,password);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
