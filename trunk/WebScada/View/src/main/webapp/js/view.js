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
        var getDiv = document.getElementById("image");
        getDiv.appendChild(importNode);
    });

    function initWebSocket() {
        var svgId = [];
        var resp = ajaxRequest();
        resp.success(function (json) {
            $.each(json, function (key, value) {
                svgId.push("#" + value);
            })
        });
        var uri = "ws://localhost:8080/view/monitor";
        var websocket = new WebSocket(uri);
        websocket.onopen = function (evt) {
        };
        websocket.onmessage = function (evt) {
            var json = $.parseJSON(evt.data);
            $.each(json, function (k, v) {
                $.each(v, function (key, value) {
                    d3.select("#image svg g").select("#" + key).text(value);
                });
            });
        };
        websocket.onclose = function (evt) {
            websocket.close();
            for (var key in svgId) {
                //d3.select("#image svg g").select(svgId[key]).text(0.00);
                d3.select("#image svg g").select(svgId[key]).style("fill", 'red').text(0.00);
            }
        }
    }

    function ajaxRequest() {
        return $.ajax({
            url: "TagNames.do"
        });
    }
});