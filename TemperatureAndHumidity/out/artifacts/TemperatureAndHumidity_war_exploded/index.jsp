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
<div class="box"></div></div>
<script>
  var connection=new WebSocket("http://localhost:8080/TemperatureAndHumidity_war_exploded/",'json');
connection.onopen = function () {
  connection.send('Hello, Server!!');
}
connection.onmessage = function (event) {
  $('#box').append(evt.data);
}</script>
</br>
<div class="wrapper">
<button class="button" name="button">Visa historik</button>
</div>
</br>
<div class="div" id="div1"></div>

<script>
  $(document).ready(function(){
    $("button").click(function(){
      $.ajax({
        type : 'POST',
        url: "http://ip.jsontest.com/?callback=showMyIP",
        success: function(result){
          $("#div1").html(result);
        }});
    });
  });
</script>
</body>
</html>

