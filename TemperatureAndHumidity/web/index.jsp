<%--
  Created by IntelliJ IDEA.
  User: linnm
  Date: 2020-05-19
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Temperature and humidity</title>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div class="info"><h1>Projekt av David, Lasse och Linn</h1>
</br>
<h2>Nuvarande temperatur och luftfuktighet:</h2>
<div class="currentvalueBOX"></div>
</div>
<script>
  var connection=new WebSocket("http://localhost:8080/websocket",'json');
  connection.onopen = function () {
    connection.send('Hello, Server!!');
  }
  connection.onmessage = function (event) {
    $('#currentvalueBOX').append(evt.data);
  }</script>
</br>
<div class="wrapper">
<button class="button" name="button">Visa historik</button>
</div>
</br>
<div class="div" id="div1"></div>

<script>
  $(document).ready(function() {
    $("button").click(function(){
    $.ajax({
      type: "GET",
      url: "http://localhost:8080/getDataFromDatabase",
      dataType: "xml",
      success: function(xml) {
        var items = parseXml(xml);
        var i;
        for (i = 0; i < items.length; i++)
        {
        doStuff(items[i]);}
      }
    });
  });

  function parseXml(xml) {
    var items = [];
    $(xml).find("item").each(function() {
      items.push({
        temperature: $(this).find("temperature").text(),
        humidity: $(this).find("humidity").text(),
        id: $(this).find("id").text()
      });
    });
    return items;
  }

  function doStuff(items) {
    $("#div1").append('id:' + items.id + '</br>');
  }});
</script>
</body>
</html>

