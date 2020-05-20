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
<h2>Current temperature and humidity:</h2>
<div class="currentvalueBOX" id="div2"></div>

<script>
  setInterval(function() {
    $(document).ready(function() {
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/current",
        dataType: 'xml',
        success: function (result) {
          $("#div2").html("<small>Temperature:</small> " + result.getElementsByTagName("temperature")[0].childNodes[0].nodeValue + '</br>'+
                  "<small>Humidity:</small> " + result.getElementsByTagName("humidity")[0].childNodes[0].nodeValue + '</br>');
        }
      });
    });
  }, 1000);
</script>
</div>

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
        date: $(this).find("createdDate").text(),
        id: $(this).find("id").text()
      });
    });
    return items;
  }

  function doStuff(items) {
    $("#div1").append('<li>'+ 'Date: ' + items.date + ' ' + '</br>Temperature:' + items.temperature + ' ' + '</br>Humidity: ' + items.humidity +'</li>' + '</br>');
  }});
</script>
</body>
</html>

