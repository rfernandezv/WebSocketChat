var host = "ws://"+location.host+"/WebSocketChat/chat";
var wSocket = new WebSocket(host);
var browserSupport = ("WebSocket" in window) ? true : false;

wSocket.onmessage = onMessage;    

 function onMessage(evt) { 
    var received_msg = evt.data;
    document.getElementById('chatForm:serverMsg').value = received_msg + "\n"+document.getElementById('chatForm:serverMsg').value;
};

wSocket.onclose = function ()
{
};

function addMsg() {
    wSocket.send(document.getElementById('chatForm:msg').value);
    document.getElementById('chatForm:msg').value="";
}
