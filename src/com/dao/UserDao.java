package com.dao;

import java.sql.*;

import com.bean.User;

public class UserDao extends BaseDao {
    public User findByName(String name) {
        String sql = "SELECT * FROM [User] WHERE UserId=?";
        User user = new User();
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            ResultSet rst = pstmt.executeQuery();
            if (rst.next()) {
                user.setUserId(name);
                user.setPassword(rst.getString("Password"));
            } else
                return null;
        } catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
        return user;
    }
}
