
function showHistory() {
    var obj = JSON.parse('{"date":"2020-05-15: ", "temperature":"24,3 ", "humidity":"30 ", "luminosity":"69% "}');
    var x = document.createElement("TEXTAREA");
    var t = document.getElementById("demo").innerHTML = obj.date + obj.temperature + obj.humidity + obj.luminosity ;
    x.appendChild(t);
    document.body.appendChild(x);
}

function currentMeasures(){

}

function recievingDate() {
    var y = document.getElementById("myDate").value;
    var obj = JSON.parse('{"date":"2020-05-15: ", "temperature":"24,3 ", "humidity":"30 ", "luminosity":"69% "}');
    var x = document.createElement("TEXTAREA");
    var t = document.getElementById("demo").innerHTML = y + ": " + obj.temperature + obj.humidity + obj.luminosity ;
    x.appendChild(t);
    document.body.appendChild(x);
}