package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bean.Friend;

public class FriendDao extends BaseDao{
	public ArrayList<Friend> findByName(String userId){
		String sql="SELECT * FROM [Friend] WHERE UserId=?";
		Friend friend;
		ArrayList<Friend> friends=new ArrayList<Friend>();
		try{
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()){
				friend=new Friend();
				friend.setUserId(userId);
				friend.setFriend(rst.getString("FriendId"));
				friends.add(friend);
			}
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}
		return friends;
	}
}
