$(function() {
    initWebSocket();
    d3.xml("images/drawing.svg", "image/svg+xml", function(xml) {
        var importNode = document.importNode(xml.documentElement, true);
        var getDiv = document.getElementById("test");
        getDiv.appendChild(importNode);
        //
    });

    function initWebSocket() {
        var uri = "ws://localhost:8080/view/monitor";
        //writeLog("Connecting To " + uri);
        var websocket = new WebSocket(uri);
        websocket.onopen = function (evt) {
            //writeLog("Connected to Server !!! @@@");
        };
        websocket.onmessage = function (evt) {
            var json = JSON.parse(evt.data);
            d3.select("#test svg g").select("#text7687").text(json.Dev1.Tag1);
            d3.select("#test svg g").select("#text7722").text(json.Dev1.Tag2);
            d3.select("#test svg g").select("#text8663").text(json.Dev1.Tag3);
            //d3.select("#test svg g").select("text8667").text(json.Dev1.Tag4);
            //d3.select("#test svg g").select("text5454").text(json.Dev1.Tag5);
        };
        websocket.onclose = function (evt) {
            websocket.close();
        }
    }

/*    function writeLog(message) {
        document.getElementById("logger").value += message + "\n";
    }
    window.addEventListener("load", init, false);*/

});