var req;
var sendreq;
var friend = '';

function chooseFriend(self) {
    if (friend == self.innerHTML)
        isnew = 0;
    else {
        isnew = 1;
        if (friend != '') {
            chage = document.getElementById(escape(friend));
            if (chage != null)
                chage.style.backgroundColor = "#EFFFFF";
        }
    }
    friend = self.innerHTML;
    self.style.backgroundColor = "#FCEBA6";
    document.getElementById("friend").innerHTML = friend;
    askNewMessage(isnew);
}

function askNewMessage(isnew) {
    if (friend != '') {
        var url = "AskNewMessageServler?SenderId=" + escape(friend) + "&IsNew=" + isnew;
        if (window.XMLHttpRequest) {
            req = new XMLHttpRequest();
        }
        else if (window.ActiveXObject) {
            req = new ActiveXObject("Microsoft.XMLHTTP");
        }
        req.open("GET", url, true);
        req.onreadystatechange = function () {
            callback(isnew);
        };
        req.send(null);
    }
}

function callback(isnew) {
    if (req.readyState == 4 && req.status == 200) {
        var check = req.responseText;
        show(check, isnew);
    }
}

function show(str, isnew) {
    if (isnew == 0)
        document.getElementById("messagebox").innerHTML += str;
    else
        document.getElementById("messagebox").innerHTML = str;
}

function sendMessage(button) {
    var oEvent = window.event || arguments.callee.caller.arguments[0];
    if ((oEvent.keyCode == 13 && oEvent.ctrlKey) || button == "1") {
        if (document.getElementById("inputbox").value == "")
            alert("请输入消息");
        else {
            if (friend == "")
                alert("先选择好友");
            else {
                var message = document.getElementById("inputbox").value;
                var url = "SendMessageServlet?ReceiverId=" + escape(friend) + "&Message=" + message;
                if (window.XMLHttpRequest) {
                    sendreq = new XMLHttpRequest();
                }
                else if (window.ActiveXObject) {
                    sendreq = new ActiveXObject("Microsoft.XMLHTTP");
                }
                date = new Date();
                text = "<font style=\"color:#808080;font-size:10px;\">" + date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "</font><br/>";
                text += "<font style=\"color:#808080;font-size:10px;\">" + document.getElementById("userid").innerHTML + "</font><br/>";
                text += "<font>" + message + "</font><br/><br/>";
                document.getElementById("messagebox").innerHTML += text;
                sendreq.open("GET", url, true);
                sendreq.onreadystatechange = sendBack;
                sendreq.send(null);
                document.getElementById("inputbox").value = "";
            }
        }
    }
}

function sendBack() {
    if (sendreq.readyState == 4 && sendreq.status == 200) {
        var check = sendreq.responseText;
        if (check == "failed") {
            var show = "<font color='red'>信息发送失败</font>";
            document.getElementById("messagebox").innerHTML += show;
        }
    }
}

function fmovwe(self) {
    if (friend != self.innerHTML)
        self.style.backgroundColor = "#FCF0C1";
}

function fmout(self) {
    if (friend != self.innerHTML)
        self.style.backgroundColor = "#EFFFFF";
}

function start() {
    setInterval("askNewMessage(0)", "1000");
}