package com.dao;

import java.sql.*;

import com.bean.User;

@SuppressWarnings("SqlResolve")
public class UserDao extends BaseDao {
    /**
     * 根据用户ID查找用户对象
     *
     * @param name 用户ID
     * @return ID对应的用户对象
     */
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

    /**
     * 添加一个新用户
     *
     * @param user 要添加的用户
     * @return 添加是否成功
     */
    public boolean add(User user) {
        String sql = "INSERT INTO [User] VALUES(?,?)";
        try {
            Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            int c = pstmt.executeUpdate();
            return c != 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除一个用户
     *
     * @param userId 用户id
     * @return 删除是否成功
     */
    public boolean remove(String userId) {
        String sql = "DELETE FROM [User] WHERE UserId = ?";
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, userId);
                int c = pstmt.executeUpdate();
                return c != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
