package com.bean;

import java.io.Serializable;
import java.sql.Date;

public class Message implements Serializable{
	private int MessageId;
	public int getMessageId() {
		return MessageId;
	}
	public void setMessageId(int messageId) {
		MessageId = messageId;
	}
	public String getSenderId() {
		return SenderId;
	}
	public void setSenderId(String senderId) {
		SenderId = senderId;
	}
	public String getReceiverId() {
		return ReceiverId;
	}
	public void setReceiverId(String receiverId) {
		ReceiverId = receiverId;
	}
	public Date getTime() {
		return Time;
	}
	public void setTime(Date time) {
		Time = time;
	}
	private String SenderId;
	private String ReceiverId;
	private Date Time;
	private String Message;
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	
}
