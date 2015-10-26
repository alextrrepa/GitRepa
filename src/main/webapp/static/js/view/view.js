$(function () {
    initWebSocket();
    d3.xml("../static/images/drawing.svg", "image/svg+xml", function (xml) {
        var importNode = document.importNode(xml.documentElement, true);
        var getDiv = document.getElementById("svgElement");
        getDiv.appendChild(importNode);
    });

    function initWebSocket() {
        var svgId = [];
        var resp = ajaxRequest();
        resp.success(function (json) {
            $.each(json, function (key, value) {
                console.log(key + " " + value);
                svgId.push("#" + value);
            })
        });
        var uri = "ws://localhost:8080/webscada/monitor";
        var websocket = new WebSocket(uri);

        websocket.onopen = function (evt) {
        };
        websocket.onmessage = function (evt) {
            var json = $.parseJSON(evt.data);
            $.each(json, function (k, v) {
                $.each(v, function (key, value) {
                    d3.select("#svgElement svg g").select("#" + key).text(value);
                });
            });
        };
        websocket.onclose = function (evt) {
            websocket.close();
            for (var key in svgId) {
                d3.select("#svgElement svg g").select(svgId[key]).style("fill", 'red').text(0.00);
            }
        }
    }

    function ajaxRequest() {
        return $.ajax({
            url: "../TagNames.do"
        });
    }
});