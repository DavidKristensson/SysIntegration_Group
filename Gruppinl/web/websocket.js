
function showHistory() {
    var obj = JSON.parse('{"date":"2020-05-15: ", "temperature":"24,3 ", "humidity":"30 ", "luminosity":"69% "}');
    var x = document.createElement("TEXTAREA");
    var t = document.getElementById("demo").innerHTML = y + ": " + obj.temperature + obj.humidity + obj.luminosity ;
    x.appendChild(t);
    document.body.appendChild(x);
}

function getValuesByDate(date){
    return new Promises(function(resolve, reject){
    const sql = 'SELCET * FROM appointments where date=?';
    getPool().query(sql, [date], (err, results) =>
    {
        resolve(result[0]);
    })
})
}

function recievingDate() {
    const date = document.getElementById("myDate").value;
    getValuesByDate(date);
}
