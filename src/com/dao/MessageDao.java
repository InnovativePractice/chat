package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.bean.Message;

public class MessageDao extends BaseDao{
	public ArrayList<Message> findAfterTime(Date time,String receiverId,String senderId){
		String sql="SELECT * FROM [Message] WHERE Time>? AND ReceiverId=? AND SenderId=?";
		Message message;
		ArrayList<Message> messages=new ArrayList<Message>();
		try{
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(time));
			pstmt.setString(2, receiverId);
			pstmt.setString(3, senderId);
			ResultSet rst=pstmt.executeQuery();
			while(rst.next()){
				message=new Message();
				message.setReceiverId(receiverId);
				message.setSenderId(rst.getString("SenderId"));
				message.setMessageId(Integer.parseInt(rst.getString("MessageId")));
				message.setTime(new Date((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rst.getString("Time")).getTime()));
				message.setMessage(rst.getString("Message"));
				messages.add(message);
			}
		}catch(SQLException se){
			se.printStackTrace();
			return null;
		}catch(ParseException pe){
			pe.printStackTrace();
			return null;
		}
		return messages;
	}
	public void save(Message message){
		String sql="INSERT INTO [Message] (SenderId,ReceiverId,Time,Message) VALUES(?,?,?,?)";
		try{
			Connection conn=dataSource.getConnection();
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, message.getSenderId());
			pstmt.setString(2, message.getReceiverId());
			pstmt.setString(3, (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(message.getTime()));
			pstmt.setString(4, message.getMessage());
			pstmt.executeUpdate();
		}catch(SQLException se){
			se.printStackTrace();
		}
	}
}
