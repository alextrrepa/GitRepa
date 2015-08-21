$(function() {
    initWebSocket();
    d3.xml("images/drawing.svg", "image/svg+xml", function(xml) {
        var importNode = document.importNode(xml.documentElement, true);
        var getDiv = document.getElementById("test");
        getDiv.appendChild(importNode);
        d3.select("#test svg g").select("#text7687").text(1.235);
    });

    function initWebSocket() {
        var uri = "ws://localhost:8080/view/monitor";
        //writeLog("Connecting To " + uri);
        var websocket = new WebSocket(uri);
        websocket.onopen = function (evt) {
            //writeLog("Connected to Server !!! @@@");
        };
        websocket.onmessage = function (evt) {
            $.each(evt.data, function(key, value) {
                console.log(key);
                console.log(value);
            });
            //writeLog("Received Message" + evt.data);
            //var json = JSON.parse(evt.data);

            //console.log(evt.data);

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