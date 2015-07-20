;(function ($) {

    function Tree(element, options) {
        this.element = element;
        this.options = $.extend({}, options);
    }

    function Factory() {
    }

    Factory.prototype = {
        createNodeForm: function (data) {
            return new treeMethods.NodeForm(data);
        },

        createDeviceForm: function (data) {
            return new treeMethods.DeviceForm(data);
        },

        createTagForm: function (data) {
            return new treeMethods.TagForm(data);
        }
    };

    var treeForms = {
        rtuHtml: function () {
            return '<form action="" method="post">' +
                '<label><span>Имя Узла<span class="required">*</span></span>' +
                '<input type="text" name="nodename" class="input-field"/>' +
                '</label>' +

                '<label><span>Тип modbus <span class="required">*</span></span>' +
                '<select name="modbustype" class="select-field">' +
                '<option value="rtu">Rtu</option>' +
                '<option value="tcp">Tcp</option>' +
                '</select>' +
                '</label>' +

                '<label><span>Порт <span class="required">*</span></span>' +
                '<input type="text" name="port" class="input-field"/>' +
                '</label>' +

                '<label><span>Скорость <span class="required">*</span></span>' +
                '<select name="baudrate" class="select-field">' +
                '<option value="1200">1200</option>' +
                '<option value="1800">1800</option>' +
                '<option value="2400">2400</option>' +
                '<option value="4800">4800</option>' +
                '<option value="9600">9600</option>' +
                '<option value="19200">19200</option>' +
                '<option value="38400">38400</option>' +
                '<option value="57600">57600</option>' +
                '<option value="115200">115200</option>' +
                '</select>' +
                '</label>' +

                '<label><span>Данные <span class="required">*</span></span>' +
                '<select name="databits" class="select-field">' +
                '<option value="5">5</option>' +
                '<option value="6">6</option>' +
                '<option value="7">7</option>' +
                '<option value="8">8</option>' +
                '</select>' +
                '</label>' +

                '<label><span>Четность <span class="required">*</span></span>' +
                '<input type="text" name="parity" class="input-field"/>' +
                '</label>' +

                '<label><span>Стоп биты <span class="required">*</span></span>' +
                '<select name="parity" class="select-field">' +
                '<option value="0">0</option>' +
                '<option value="1">1</option>' +
                '<option value="1.5">1.5</option>' +
                '<option value="2">2</option>' +
                '</select>' +
                '</label>' +

                '<label><span>Повторы при ошибке <span class="required">*</span></span>' +
                '<input type="text" name="retries" class="input-field">' +
                '</label>' +

                '<label><span>Время ответа <span class="required">*</span></span>' +
                '<input type="text" name="timeout" class="input-field">' +
                '</label>' +

                '<label><span>Период опроса<span class="required">*</span></span>' +
                '<input type="text" name="period" class="input-field">' +
                '</label>' +
                '<input type="submit" value="Сохранить"/>' +
                '</form>';
            //return html;
        },

        tcpHtml: function () {
            return '<form action="" method="post">' +
                '<label><span>Имя узла <span class="required">*</span></span>' +
                '<input type="text" name="nodename" class="input-field"/>' +
                '</label>' +

                '<label><span>Тип modbus <span class="required">*</span></span>' +
                '<select name="modbustype" class="select-field">' +
                '<option value="rtu">Rtu</option>' +
                '<option value="tcp">Tcp</option>' +
                '</select>' +
                '</label>' +

                '<label><span>IP <span class="required">*</span></span>' +
                '<input type="text" name="ip" class="input-field"/>' +
                '</label>' +

                '<label><span>Порт <span class="required">*</span></span>' +
                '<input type="text" name="port" class="input-field"/>' +
                '</label>' +

                '<label><span>Повторы при ошибке<span class="required">*</span></span>' +
                '<input type="text" name="retries" class="input-field"/>' +
                '</label>' +

                '<label><span>Время ответа<span class="required">*</span></span>' +
                '<input type="text" name="timeout" class="input-field"/>' +
                '</label>' +

                '<label><span>Период опроса<span class="required">*</span></span>' +
                '<input type="text" name="period" class="input-field"/>' +
                '</label>' +
                '<input type="submit" value="Сохранить"/>' +
                '</form>';
        },

        deviceHtml: function () {
            return '<form action="" method="post"><label><span>Имя узла <span class="required">*</span></span>' +
                '<input type="text" name="devicename" class="input-field"/>' +
                '</label>' +

                '<label><span>SlaveId <span class="required">*</span></span>' +
                '<input type="text" name="slaveid" class="input-field"/>' +
                '</label>' +

                '<label><span>Начальное смещение<span class="required">*</span></span>' +
                '<input type="text" name="startoffset" class="input-field"/>' +
                '</label>' +

                '<label><span>Количество<span class="required">*</span></span>' +
                '<input type="text" name="counts" class="input-field"/>' +
                '</label>' +

                '<label><span>Тип регистров <span class="required">*</span></span>' +
                '<select name="regtype" class="select-field">' +
                '<option value="COIL_STATUS">COIL_STATUS</option>' +
                '<option value="HOLDING_REGISTER">HOLDING_REGISTER</option>' +
                '<option value="INPUT_REGISTER">INPUT_REGISTER</option>' +
                '<option value="INPUT_STATUS">INPUT_STATUS</option>' +
                '</select>' +
                '</label>' +
                '<input type="submit" value="Сохранить"/>' +
                '</form>';
        },

        tagHtml: function () {
            return '<form><label><span>Имя узла<span class="required">*</span></span>' +
                '<input type="text" name="tagname" class="input-field">' +
                '</label>' +

                '<label><span>Смещение<span class="required">*</span></span>' +
                '<input type="text" name="realoffset" class="input-field">' +
                '</label>' +

                '<label><span>Тип даты <span class="required">*</span></span>' +
                '<select name="datatype" class="select-field">' +
                '<option value="BINARY">BINARY</option>' +
                '<option value="CHAR">CHAR</option>' +
                '<option value="EIGHT_BYTE_FLOAT">EIGHT_BYTE_FLOAT</option>' +
                '<option value="EIGHT_BYTE_FLOAT_SWAPPED">EIGHT_BYTE_FLOAT_SWAPPED</option>' +
                '<option value="EIGHT_BYTE_INT_SIGNED">EIGHT_BYTE_INT_SIGNED</option>' +
                '<option value="EIGHT_BYTE_INT_SIGNED_SWAPPED">EIGHT_BYTE_INT_SIGNED_SWAPPED</option>' +
                '<option value="EIGHT_BYTE_INT_UNSIGNED">EIGHT_BYTE_INT_UNSIGNED</option>' +
                '<option value="EIGHT_BYTE_INT_UNSIGNED_SWAPPED">EIGHT_BYTE_INT_UNSIGNED_SWAPPED</option>' +
                '<option value="FOUR_BYTE_BCD">FOUR_BYTE_BCD</option>' +
                '<option value="FOUR_BYTE_BCD_SWAPPED">FOUR_BYTE_BCD_SWAPPED</option>' +
                '<option value="FOUR_BYTE_FLOAT">FOUR_BYTE_FLOAT</option>' +
                '<option value="FOUR_BYTE_FLOAT_SWAPPED">FOUR_BYTE_FLOAT_SWAPPED</option>' +
                '<option value="FOUR_BYTE_INT_SIGNED">FOUR_BYTE_INT_SIGNED</option>' +
                '<option value="FOUR_BYTE_INT_SIGNED_SWAPPED">FOUR_BYTE_INT_SIGNED_SWAPPED</option>' +
                '<option value="FOUR_BYTE_INT_UNSIGNED">FOUR_BYTE_INT_UNSIGNED</option>' +
                '<option value="FOUR_BYTE_INT_UNSIGNED_SWAPPED">FOUR_BYTE_INT_UNSIGNED_SWAPPED</option>' +
                '<option value="TWO_BYTE_BCD">TWO_BYTE_BCD</option>' +
                '<option value="TWO_BYTE_INT_SIGNED">TWO_BYTE_INT_SIGNED</option>' +
                '<option value="TWO_BYTE_INT_UNSIGNED">TWO_BYTE_INT_UNSIGNED</option>' +
                '<option value="VARCHAR">VARCHAR</option>' +
                '</select>' +
                '</label>' +
                '<input type="submit" value="Сохранить"/>' +
                '</form>';
        },

        addDeleteForm: function (html) {
            var mparams = $('.form_style');
            mparams.empty();
            mparams.append(html);
        }
    };

    var treeMethods = {
        typeRequest: function (url, type) {
            $.ajax({
                url: url,
                dataType: "json",
                success: function (data) {
                    var factory = new Factory();
                    switch (type) {
                        case "node":
                            factory.createNodeForm(data);
                            break;
                        case "device":
                            factory.createDeviceForm(data);
                            break;
                        case "tag":
                            factory.createTagForm(data);
                            break;
                    }
                }
            });
        },
        crudRequest: function(obj, ulTarget) {
            console.log(obj);
            console.log(ulTarget);
            $.ajax({
                url: "ModbusTreeEdit.do",
                type: "POST",
                data: obj,
                dataType: "json",
                success: function(data) {
                    var returnedData = JSON.parse(data);
                    var currentList = $(ulTarget);
                    var div = $('<div>').addClass('tree_item fill_state_hover').css("display", "inline-block").text(returnedData.name);
                    var li = $('<li>').addClass('tree_item-li').attr({"data-nodeid": returnedData.id,
                    "data-nodetype": "device" });

                    var span = $('<span>').addClass('spanExpand');
                    $(li).append(span);

                    /*$(this.element.selector + " " + "li").each(function () {
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
                    });*/

                    $(li).append(div);
                    $(li).append('<ul id="tagList">');
                    $(currentList).append(li);

                    console.log(this);
                }
            });
        },

        NodeForm: function (data) {
            if (data.type === "rtu") {
                var html = treeForms.rtuHtml();
                treeForms.addDeleteForm(html);
                $("input[name=nodename]").val(data.name);
                $("select[name=modbustype]").val(data.type);
                $("input[name=port]").val(data.rtuEntity.port);
                $("select[name=baudrate]").val(data.rtuEntity.baudrate);
                $("select[name=databits]").val(data.rtuEntity.databits);
                $("input[name=parity]").val(data.rtuEntity.parity);
                $("select[name=stopbits]").val(data.rtuEntity.stopbits);
                $("input[name=retries]").val(data.rtuEntity.retries);
                $("input[name=timeout]").val(data.rtuEntity.timeout);
                $("input[name=period]").val(data.rtuEntity.period);
            }
            if (data.type === "tcp") {
                var html = treeForms.tcpHtml();
                treeForms.addDeleteForm(html);
                $("input[name=nodename]").val(data.name);
                $("select[name=modbustype]").val(data.type);
                $("input[name=ip]").val(data.tcpEntity.ip);
                $("input[name=port]").val(data.tcpEntity.port);
                $("input[name=retries]").val(data.tcpEntity.retries);
                $("input[name=timeout]").val(data.tcpEntity.timeout);
                $("input[name=period]").val(data.tcpEntity.period);
            }
        },

        DeviceForm: function (data) {
            var html = treeForms.deviceHtml();
            treeForms.addDeleteForm(html);
            $("input[name=devicename]").val(data.name);
            $("input[name=slaveid]").val(data.slaveid);
            $("input[name=startoffset]").val(data.startOffset);
            $("input[name=counts]").val(data.counts);
            $("select[name=regtype]").val(data.registerEntity.name);
        },

        TagForm: function (data) {
            var html = treeForms.tagHtml();
            treeForms.addDeleteForm(html);
            $("input[name=tagname]").val(data.name);
            $("input[name=realoffset]").val(data.realOffset);
            $("select[name=datatype]").val(data.datatypeEntity.name);
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
            $(this.element.selector + " " + "li").each(function () {
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

        onTagAdd: function (obj, ulTarget) {
            treeMethods.crudRequest(obj, ulTarget);
        },

        onTagDelete: function(obj) {
            treeMethods.crudRequest(obj);
        },

        onTagClick: function (node) {
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
        }
    };

    $.fn.createTree = function (options) {
        var $this = $(this);
        return new Tree($this, options);
    };
})(jQuery);