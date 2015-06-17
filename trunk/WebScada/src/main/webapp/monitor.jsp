<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Мониторинг</title>
  <script>
    var websocket;
    function init(){
      var uri = "ws://localhost:8080/webscada/monitor";
      writeLog("Connecting To " + uri);
      websocket = new WebSocket(uri);
      websocket.onopen = function(evt){
        writeLog("Connected to Server !!! @@@");
      };
      websocket.onmessage = function(evt){
        writeLog("Received Message" + evt.data);
      };
      websocket.onclose = function(evt){
        websocket.close();
      }
    }

    function writeLog(message){
      document.getElementById("logger").value += message + "\n";
    }
    window.addEventListener("load", init, false);
  </script>
</head>
<br>
   <textarea id="logger" name="log" cols="40" rows="10"></textarea></br>
   <p><a href="index.jsp">На главную</a></p>
</body>
</html>
