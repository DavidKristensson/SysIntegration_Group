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
    <script src="websocket.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">
  </head>
  <body>
  <div id="wrapper">
    <h1>Projekt ansvariga: David, Lasse, Linn</h1>
      <p><i>kort förklaring av vårt projekt</i></p>
    <br />

    <div id="currentMeasures">
        <h2>Current:
        <br/>
            <br/>
        Temp: 23,4
        <br/>
        Humidity: 30
        <br/>
        Luminosity: 69%</h2>
    </div>
      <div id="showHistory">

      <form id="showHistoryTables">
          <input type="date" id="myDate" value="2014-02-09">
          <div class="button" OnClick="recievingDate()">Show historic meassures</a> </div>
          <br />
          <p id="demo"></p>
          <br />
          <br />
      </form>
  </div>
  </div>
  </body>
</html>
