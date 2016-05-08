<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.bean.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>聊天室</title>
</head>
<style type="text/css">
    .gradient {
        filter: progid:DXImageTransform.Microsoft.gradient(GradientType=1, startColorstr='#b1c3e7', endColorstr='#56a4d5');
        -ms-filter: "progid: DXImageTransform.Microsoft.gradient(GradientType = 1, startColorstr = '#b1c3e7', endColorstr = '#56a4d5')";
        background-image: -moz-linear-gradient(left, #b1c3e7, #56a4d5);
        background-image: -ms-linear-gradient(left, #b1c3e7, #56a4d5);
        background-image: -o-linear-gradient(left, #b1c3e7, #56a4d5);
        background-image: -webkit-gradient(linear, left top, right top, from(#b1c3e7), to(#56a4d5));
        background-image: -webkit-linear-gradient(left, #b1c3e7, #56a4d5);
        background-image: linear-gradient(left, #b1c3e7, #56a4d5);
    }
</style>
<script type="text/javascript" language="javascript" charset="gbk" src="/chat/js/chat.js"></script>
<body onload="start()">
<div style="display:none" id="userid"><%=((User) (session.getAttribute("user"))).getUserId() %>
</div>
<div style="position:fixed; top:0; left: 0;width: 10%; height: 100%;border-style:solid;border-color:#808080;border-width:1px;background-color:#EFFFFF;">
    <h3 style="background-color:#EFFFFF;">好友列表</h3>
    <%
        ArrayList<Friend> friends = (ArrayList<Friend>) session.getAttribute("friends");
        for (int i = 0; i < friends.size(); i++) {
    %>
    <div id="<%=friends.get(i).getFriend() %>" onclick="chooseFriend(this)" onmouseover="fmovwe(this)"
         onmouseout="fmout(this)" style="cursor:default;height:30px;"><%=friends.get(i).getFriend() %>
    </div>
    <%
        }
    %>
</div>
<div class="gradient"
     style="position:fixed;top:0; left: 10%;width: 90%; height: 80%;border-style:solid; auto; border-color:#808080;border-width:1px;">
    <h1 id="friend"></h1>
    <div id="messagebox"
         style="text-align:left;position:absolute;top:20%; left: 2%;width: 96%; height: 80%;OVERFLOW-Y:auto;"></div>
</div>
<div class="gradient"
     style="position:fixed;top:80%; left: 10%;width: 90%; height: 20%;border-style:solid;border-color:#808080;border-width:1px;text-align:right;">
    <textarea id="inputbox" onkeydown="sendMessage(0)" class="gradient"
              style="top:0%; left: 0%;width: 100%; height: 80%;OVERFLOW-Y: auto;border-style:none"></textarea>
    <button type="button" id="sendMessageButton" onclick="sendMessage(1)">发送</button>
</div>
</body>
</html>