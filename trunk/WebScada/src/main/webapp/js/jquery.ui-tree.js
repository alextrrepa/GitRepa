;(function ($) {

    function Tree(element, options) {
        this.element = element;
        this.options = $.extend({}, options);
    }

    function Factory() {}

    Factory.prototype = {
        createNodeForm: function() {
            return new treeMethods.NodeForm();
        },

        createDeviceForm: function() {
            return new treeMethods.DeviceForm();
        },

        createTagForm: function() {
            return new treeMethods.TagForm();
        }
    };

    var treeMethods = {
        typeRequest: function(url, type) {
            $.ajax({
             url: url,
             dataType: "json",
             success: function(data) {
                 var factory = new Factory();
                 switch (type) {
                     case "node":
                         factory.createNodeForm();
                         break;
                     case "device":
                         factory.createDeviceForm();
                         break;
                     case "tag":
                         factory.createTagForm();
                 }
                 console.log(data);
             }
             });
        },

        NodeForm: function() {
            console.log("Node form");
        },

        DeviceForm: function() {
            console.log("Device form");
        },

        TagForm: function() {
            console.log("Tag form");
        }
    };

    Tree.prototype = {
        init: function () {
            var nodeList = $('<ul id="nodelist">');
            $(this.element.selector + " " + "li").append(nodeList);

            $.each(this.options, function (key, value) {
                var nodeDivElement = $('<div class="tree_item fill_state_hover" style="display: inline-block"/>').
                    text(value.name);
                var nodeLiElement = $('<li class="tree_item-li">').attr({
                    "data-nodeid": value.id,
                    "data-nodetype": "node", "data-mtype": value.type
                });
                nodeLiElement.append(nodeDivElement);
                nodeList.append(nodeLiElement);

                var deviceList = $('<ul id="devicelist">');
                nodeLiElement.append(deviceList);

                $.each(value.deviceEntity, function (key, value) {
                    var devDivElement = $('<div class="tree_item fill_state_hover" style="display: inline-block"/>').
                        text(value.name);
                    var devLiElement = $('<li class="tree_item-li">').attr({
                        "data-nodeid": value.id,
                        "data-nodetype": "device"
                    });
                    devLiElement.append(devDivElement);
                    deviceList.append(devLiElement);

                    var tagList = $('<ul id="tagList">');
                    devLiElement.append(tagList);
                    $.each(value.tagEntities, function (key, value) {
                        var tagDivElement = $('<div class="tree_item fill_state_hover" style="display: inline-block"/>').
                            text(value.name);
                        var tagLiElement = $('<li class="tree_item-li">').attr({
                            "data-nodeid": value.id,
                            "data-nodetype": "tag"
                        });
                        tagLiElement.append(tagDivElement);
                        tagList.append(tagLiElement);
                    })
                });
            });

            $(this.element.selector + " " + "ul").hide();
            $(this.element.selector + " " + "li").each(function() {
                var handleSpan = $("<span></span>");
                handleSpan.addClass("spanExpand");
                handleSpan.prependTo(this);
                if ($(this).has("ul").size() > 0) {
                    handleSpan.addClass("collapsed");
                    handleSpan.click(function () {
                        var clicked = $(this);
                        clicked.toggleClass("collapsed expanded");
                        clicked.siblings("ul").toggle();
                    });
                }
            });
        },

        onTagAdd: function() {

        },

        onTagClick: function(node) {
            switch (node.nodetype) {
                case "node":
                    var url = "ModbusTreeEdit.do?type=" + node.nodetype + "&" +
                        "mtype=" + node.mtype + "&" + "id=" + node.nodeid;
                    treeMethods.typeRequest(url, node.nodetype);
                    break;
                case "device":
                    var url = "ModbusTreeEdit.do?type=" + node.nodetype + "&"
                        + "id=" + node.nodeid;
                    treeMethods.typeRequest(url, node.nodetype);
                    break;
                case "tag":
                    var url = "ModbusTreeEdit.do?type=" + node.nodetype + "&" +
                        "id=" + node.nodeid;
                    treeMethods.typeRequest(url, node.nodetype);
                    break;
            }
            /*$(".tree").on('click', 'li div', function () {
                $(this).toggleClass("fill_state_pressed");
                $("li div").not(this).removeClass("fill_state_pressed");

                var parent = $(this).parent()[0];
                var node = $(parent).data();
                console.log(node.nodetype);
            });*/
        }
    };

    $.fn.createTree = function (options) {
        var $this = $(this);
        return new Tree($this, options);
    };
})(jQuery);