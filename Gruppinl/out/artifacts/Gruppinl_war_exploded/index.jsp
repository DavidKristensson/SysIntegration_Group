<%--
  Created by IntelliJ IDEA.
  User: linnm
  Date: 2020-05-15
  Time: 12:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
  <div id="wrapper">
    <h1>Projekt ansvariga: David, Lasse, Linn</h1>
      <p><i>kort förklaring av vårt projekt</i></p>
    <br />

    <div id="currentMeasures">
        <h2>Current:</h2>
        <br/>
            <script type="text/javascript">
                var websocket = new WebSocket('ws://localhost:8080')
                websocket.onmessage = function(event) {
                    var msg = JSON.parse(event.data);
                    var time = new Date(msg.date);
                    var timeStr = time.toLocaleTimeString();
                    var textarea = document.createElement("TEXTAREA");
                    var t = document.getElementById("currentMeasures").innerHTML = msg;
                    textarea.appendChild(t);
                    document.body.appendChild(textarea);
                }
            </script>
    </div>
      <div id="box">
      <div id="showHistory">
          <form action="/action_page.php">
              <label for="selectedDate">Date: </label>
              <input type="date" id="selectedDate" name="selectedDate">
              <input type="submit">
          </form></div>
          <br />
          <p id="demo"></p>
          <br />
          <br />

      </form>
      </div>
  </div>
  </div>
  </body>
</html>
