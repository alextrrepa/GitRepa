$(function () {
    $('.menu-icon').click(function () {
        if ($('#navigator').css("left") == "-250px") {
            $('#navigator').animate({left: '0px'}, 350);
            $('.menu-icon').animate({left: '250px'}, 350);
            $('.menu-text').animate({left: '300px'}, 350).empty().text("Закрыть");
        }
        else {
            $('#navigator').animate({left: '-250px'}, 350);
            $(this).animate({left: '0px'}, 350);
            $('.menu-text').animate({left: '50px'}, 350).empty().text("Меню");

        }
    });
    $('.menu-icon').click(function () {
        $(this).toggleClass("on");
    });

    initWebSocket();
    d3.xml("images/drawing.svg", "image/svg+xml", function (xml) {
        var importNode = document.importNode(xml.documentElement, true);
        //var getDiv = $('.svg').get();
        var getDiv = document.getElementById("svg");
        getDiv.appendChild(importNode);
    });

    function initWebSocket() {
        var uri = "ws://localhost:8080/view/monitor";
        //writeLog("Connecting To " + uri);
        var websocket = new WebSocket(uri);
        websocket.onopen = function (evt) {
            //writeLog("Connected to Server !!! @@@");
        };
        websocket.onmessage = function (evt) {
            var json = $.parseJSON(evt.data);
            $.each(json, function (k, v) {
                $.each(v, function (key, value) {
                    d3.select("#test svg g").select("#" + key).text(value);
                });
            });
            //d3.select("#test svg g").select("#text7687").text(json.Dev1.Tag1);
        };
        websocket.onclose = function (evt) {
            websocket.close();
        }
    }
});