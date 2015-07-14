;(function($) {
    //var methods = {
       /* init: function(selector) {
           return $.ajax({
                url: "ModbusEdit.do",
                type: "POST",
                data: {"action": "getAll"},
                dataType: "json",
                success: function(data) {
                    buildTree(data, selector);
                }
            });
        }*/
/*
        buildTree: function(data, selector) {
            //$.each(data, function(key, value) {
            //    console.log(value.name);

                //$(selector[0]).append("<p>Helloooo</p>");

                /!*
                 $.each(value.deviceEntity, function (key, value) {
                 console.log(value.name);
                 $.each(value.tagEntities, function  (key, value) {
                 console.log(value.name);
                 });
                 });
                 *!/
            //});
        }
*/
    //};

    var buildTree = function (selector) {
        /*$.ajax({
            url: "ModbusEdit.do",
            type: "POST",
            data: {"action": "getAll"},
            dataType: "json",
            success: function(data) {
                var items = [];

                $(".tree li").append("<ul>");
                $.each(data, function(key, value) {
                    items.push($('<li class="tree_item-li">'));
                    items.push($('<div class="tree_item fill_state_hover" style="display: inline-block"/>').text(value.name));
                    items.push($('</li>'));
                });
                $(".tree li").append("</ul>");
                $(".tree li ul").append(items);
            }
        });*/
    };

    $.fn.createTree = function(options) {
        var items = [];

        $(".tree li").append("<ul>");
        $.each(options, function(key, value) {
            items.push($('<li class="tree_item-li">'));
            items.push($('<div class="tree_item fill_state_hover" style="display: inline-block"/>').text(value.name));
            items.push($('</li>'));
        });
        $(".tree li").append("</ul>");
        $(".tree li ul").append(items);


        $(".tree ul").hide();
        $(".tree li").each(function () {
            var handleSpan = $("<span></span>");
            handleSpan.addClass("spanExpand");
            handleSpan.prependTo(this);

            if($(this).has("ul").size() > 0) {
                handleSpan.addClass("collapsed");
                handleSpan.click(function() {
                    var clicked = $(this);
                    clicked.toggleClass("collapsed expanded");
                    clicked.siblings("ul").toggle();
                });
            }
        });

        $(this.selector).on('click', 'li div', function() {
            $(this).toggleClass("fill_state_pressed");
            $("li div").not(this).removeClass("fill_state_pressed");
            var parent = $(this).parent()[0];
            var node = $(parent).data();

            switch (node.nodetype) {
                case "node":
                    //nodeRequest(node.nodeid, node.nodetype, node.mtype);
                    break;
                case "device":
                    //deviceRequest(node.nodeid, node.nodetype);
                    break;
                case "tag":
                    //tagRequest(node.nodeid, node.nodetype);
                    break;
            }
        });
    };


})(jQuery);