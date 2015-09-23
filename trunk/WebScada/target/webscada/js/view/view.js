$(function () {
    var pushy = $('.pushy'), //menu css class
        body = $('body'),
        container = $('#container'), //container css class
        push = $('.push'), //css class to add pushy capability
        siteOverlay = $('.site-overlay'), //site overlay
        header = $('.header'),
        pushyClass = "pushy-left pushy-open", //menu position & menu open class
        pushyActiveClass = "pushy-active", //css class to toggle site overlay
        containerClass = "container-push", //container open class
        pushClass = "push-push", //css class to add pushy capability
        headerClass = "header-push",
        menuBtn = $('.menu-btn, .pushy a'), //css classes to toggle the menu
        menuSpeed = 200, //jQuery fallback menu speed
        menuWidth = pushy.width() + "px"; //jQuery fallback menu width

    function togglePushy() {
        body.toggleClass(pushyActiveClass); //toggle site overlay
        header.toggleClass(headerClass);
        pushy.toggleClass(pushyClass);
        container.toggleClass(containerClass);
        push.toggleClass(pushClass); //css class to add pushy capability
    }

    function openPushyFallback() {
        body.addClass(pushyActiveClass);
        pushy.animate({left: "0px"}, menuSpeed);
        container.animate({left: menuWidth}, menuSpeed);
        push.animate({left: menuWidth}, menuSpeed); //css class to add pushy capability
        header.animate({left: menuWidth}, menuSpeed);
    }

    function closePushyFallback() {
        body.removeClass(pushyActiveClass);
        pushy.animate({left: "-" + menuWidth}, menuSpeed);
        container.animate({left: "0px"}, menuSpeed);
        push.animate({left: "0px"}, menuSpeed); //css class to add pushy capability
        header.animate({left: "0px"}, menuSpeed);
    }

    //checks if 3d transforms are supported removing the modernizr dependency
    cssTransforms3d = (function csstransforms3d() {
        var el = document.createElement('p'),
            supported = false,
            transforms = {
                'webkitTransform': '-webkit-transform',
                'OTransform': '-o-transform',
                'msTransform': '-ms-transform',
                'MozTransform': '-moz-transform',
                'transform': 'transform'
            };

        // Add it to the body to get the computed style
        document.body.insertBefore(el, null);

        for (var t in transforms) {
            if (el.style[t] !== undefined) {
                el.style[t] = 'translate3d(1px,1px,1px)';
                supported = window.getComputedStyle(el).getPropertyValue(transforms[t]);
            }
        }

        document.body.removeChild(el);

        return (supported !== undefined && supported.length > 0 && supported !== "none");
    })();

    if (cssTransforms3d) {
        //toggle menu
        menuBtn.click(function () {
            togglePushy();
        });
        //close menu when clicking site overlay
        siteOverlay.click(function () {
            togglePushy();
        });
    } else {
        //jQuery fallback
        pushy.css({left: "-" + menuWidth}); //hide menu by default
        container.css({"overflow-x": "hidden"}); //fixes IE scrollbar issue

        //keep track of menu state (open/close)
        var state = true;

        //toggle menu
        menuBtn.click(function () {
            if (state) {
                openPushyFallback();
                state = false;
            } else {
                closePushyFallback();
                state = true;
            }
        });

        //close menu when clicking site overlay
        siteOverlay.click(function () {
            if (state) {
                openPushyFallback();
                state = false;
            } else {
                closePushyFallback();
                state = true;
            }
        });
    }

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
        var uri = "ws://localhost:8080/webscada/monitor";
        var websocket = new WebSocket(uri);

        websocket.onopen = function (evt) {};
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