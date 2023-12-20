function readFile(file, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", file, true);
    rawFile.onreadystatechange = function() {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}

readFile("myMap/jsonData/config_card.json", function(text) {
  config = JSON.parse(text);

  for(var i=0;i<7;i++) {
    var colorArray = document.getElementById(Object.keys(config[0])[i]);
    colorArray.style.backgroundColor = Object.values(config[0])[i];
   }
});


