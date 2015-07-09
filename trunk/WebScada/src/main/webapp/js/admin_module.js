$(function() {
    /* Create expandable tree */
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

    /* Tree element  on click */
        $(".tree").on('click', 'li div', function() {
            $(this).toggleClass("fill_state_pressed");
            $(".tree li div").not(this).removeClass("fill_state_pressed");
            var parent = $(this).parent()[0];
            var node = $(parent).data();
            /*      if (node.nodetype === "node") {
             nodeRequest(node.nodeid, node.nodetype, node.mtype);
             }

             if (node.nodetype === "device") {
             deviceRequest(node.nodeid, node.nodetype);
             }

             if (node.nodetype === "tag") {
             tagRequest(node.nodeid, node.nodetype);
             }*/
        });

    /* Context Menu Tree */
    $(".tree").contextmenu({
        loadTheme: "Smoothness",
        delegate: "li",
        menu: [
            {title: "Добавить хрень", cmd: "add", uiIcon: "ui-icon-plusthick"},
            {title: "Удалить узел", cmd: "delete", uiIcon: "ui-icon-trash"}
        ],
        beforeOpen: function(event, ui) {
            var parent = ui.target.parent()[0];
            var node = $(parent).data();
            if (node.nodetype == "tag") {
                $(".tree").contextmenu("showEntry", "add", false);
            } else {
                $(".tree").contextmenu("showEntry", "delete", true);
                $(".tree").contextmenu("showEntry", "add", true);
            }
            switch (node.nodetype) {
                case "root":
                    $(".tree").contextmenu("setEntry", "add", {title: "Добавить коммуникационный узел",
                        uiIcon: "ui-icon-plusthick"});
                    break;
                case "node":
                    $(".tree").contextmenu("setEntry", "add", {title: "Добавить устройство",
                        uiIcon: "ui-icon-plusthick"});
                    break;
                case "device":
                    $(".tree").contextmenu("setEntry", "add", {title: "Добавить тэг",
                        uiIcon: "ui-icon-plusthick"});
                    break;
            }
        },
        select: function(event, ui) {
            var target = ui.target.parent()[0];
            var node = $(target).data().nodetype;
            if (ui.cmd === "add") {
                if (node === "root") {
                    treeElement.addRoot();
                }
                /*if ($(target).data().nodetype === "root") {
                    console.log($("li[data-nodetype=root] > ul").has("ul"));
                    var html =
                    "<li class='tree_item-li' data-nodetype='node'><span class='spanExpand collapsed'></span>" +
                    "<div class='tree_item fill_state_hover' style='display: inline-block'>NodeTest</div></li>";
                    $("li[data-nodetype=root] > ul").append(html);
                } else
                if ($(target).data().nodetype === "node") {
                    var id = $(target).data().nodeid;
                    var html =
                        "<li class='tree_item-li' data-nodetype='device'><span class='spanExpand collapsed'></span>" +
                        "<div class='tree_item fill_state_hover' style='display: inline-block'>DeviceTest</div></li>";
                    $("li[data-nodetype=node][data-nodeid=" + id + "] > ul:first").append(html);
                }
                if ($(target).data().nodetype === "device") {
                    var id = $(target).data().nodeid;
                    var html =
                        "<li class='tree_item-li' data-nodetype='tag'><span class='spanExpand collapsed'></span>" +
                        "<div class='tree_item fill_state_hover' style='display: inline-block'>TagTest</div></li>";
                    $("li[data-nodetype=device][data-nodeid=" + id + "] > ul:first").append(html);
                }*/
            }
        }
    });

    var treeElement = {
        addRoot: function() {
            var ulsize = $("li[data-nodetype=root]").has("ul").size();
            if (ulsize === 1) {
                var html =   "<li class='tree_item-li' data-nodetype='device'>" +
                    "<div class='tree_item fill_state_hover' style='display: inline-block'>NodeTest</div></li>";
                $("li[data-nodetype=root] > ul").append(html);
            }
        }
    };

    function nodeRequest(id, type, mtype) {
        $.ajax({
            url: "ModbusTreeEdit.do?type=" + type + "&" + "mtype=" + mtype + "&" + "id=" + id,
            dataType: "json",
            success: nodeParams
        });
    }

    function deviceRequest(id,type) {
        $.ajax({
            url: "ModbusTreeEdit.do?type="+type + "&" + "id=" +id,
            success: deviceParams
        });
    }

    function tagRequest(id,type) {
        $.ajax({
            url: "ModbusTreeEdit.do?type="+type + "&" + "id=" +id,
            success: tagParams
        });
    }

    var nodeParams = function(resp) {
        if (resp.modbustype === "rtu") {
            addDeleteForm(rtuHtml());
            $("input[name=nodename]").val(resp.nodename);
            $("select[name=modbustype]").val(resp.modbustype);
            $("input[name=port]").val(resp.port);
            $("select[name=baudrate]").val(resp.baudrate);
            $("select[name=databits]").val(resp.databits);
            $("input[name=parity]").val(resp.parity);
            $("select[name=stopbits]").val(resp.stopbits);
            $("input[name=retries]").val(resp.retries);
            $("input[name=timeout]").val(resp.timeout);
            $("input[name=period]").val(resp.period);
        }
        if (resp.modbustype === "tcp") {
            addDeleteForm(tcpHtml());
            $("input[name=nodename]").val(resp.nodename);
            $("select[name=modbustype]").val(resp.modbustype);
            $("input[name=ip]").val(resp.ip);
            $("input[name=port]").val(resp.port);
            $("input[name=retries]").val(resp.retries);
            $("input[name=timeout]").val(resp.timeout);
            $("input[name=period]").val(resp.period);
        }
    };

    var deviceParams = function(resp) {
        addDeleteForm(deviceHtml());
        $("input[name=devicename]").val(resp.devicename);
        $("input[name=slaveid]").val(resp.slaveid);
        $("input[name=startoffset]").val(resp.startoffset);
        $("input[name=counts]").val(resp.counts);
        $("select[name=regtype]").val(resp.regtype);
    };

    var tagParams = function (resp) {
        addDeleteForm(tagHtml());
        $("input[name=tagname]").val(resp.tagname);
        $("input[name=realoffset]").val(resp.realoffset);
        $("select[name=datatype]").val(resp.datatype);
    };

    var rtuHtml = function() {
      return '<form action="" method="post">' +
          '<label><span>Имя Узла<span class="required">*</span></span>'+
          '<input type="text" name="nodename" class="input-field"/>'+
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
          '<input type="submit" value="Сохранить"/>'+
          '</form>';
        //return html;
    };

    var tcpHtml = function() {
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
    };

    var deviceHtml = function() {
        return '<form action="" method="post"><label><span>Имя узла <span class="required">*</span></span>' +
            '<input type="text" name="devicename" class="input-field"/>' +
            '</label>' +

            '<label><span>SlaveId <span class="required">*</span></span>' +
            '<input type="text" name="slaveid" class="input-field"/>' +
            '</label>' +

            '<label><span>Начальное смещение<span class="required">*</span></span>' +
            '<input type="text" name="startoffset" class="input-field"/>' +
            '</label>'+

            '<label><span>Количество<span class="required">*</span></span>' +
            '<input type="text" name="counts" class="input-field"/>' +
            '</label>'+

            '<label><span>Тип регистров <span class="required">*</span></span>' +
            '<select name="regtype" class="select-field">' +
            '<option value="COIL_STATUS">COIL_STATUS</option>' +
            '<option value="HOLDING_REGISTER">HOLDING_REGISTER</option>' +
            '<option value="INPUT_REGISTER">INPUT_REGISTER</option>' +
            '<option value="INPUT_STATUS">INPUT_STATUS</option>' +
            '</select>' +
            '</label>' +
            '<input type="submit" value="Сохранить"/>' +
            '</form>'
            ;
    };

    var tagHtml = function () {
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
            '<input type="submit" value="Сохранить"/>'+
            '</form>';
    };

    var addDeleteForm = function(html) {
        var mparams = $('.form_style');
        mparams.empty();
        mparams.append(html);
    };
});


