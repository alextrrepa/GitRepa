$(function () {
    $("#tree").jstree({
        'plugins': ['ccrm', 'contextmenu'],
        'contextmenu': {
            'select_node': false,
            'items': function (node) {
                return {
                    "Add": {
                        'separator_after': true,
                        'label': 'Добавить узел',
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {/*icon: "images/icn_node.png"*/}, "last", function (new_node) {
                                setTimeout(function () {
                                    inst.edit(new_node);
                                }, 2);
                            });
                        },
                    },
                    "Delete": {
                        'label': "Удалить узел",
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            if (inst.is_selected(obj)) {
                                inst.delete_node(inst.get_selected());
                            }
                            else {
                                inst.delete_node(obj);
                            }
                        }
                    }
                }
            }
        },
        'core': {
            'check_callback': true,
            'themes': {
                "name": "default-dark"
            },
            'data': {
                'url': 'ModbusEdit.do?action=getAll'
            }
        }
    }).on('select_node.jstree', function (event, data) {
        var id = data.node.data;
        var nodeId = data.node.id;
        var type = nodeId.replace(/[0-9]/g, '');
        methods[type](id, type);
        //console.log(id);
        //console.log(nodeId);
        //console.log(type);
    }).on('delete_node.jstree', function (event, data) {
        /*if (data.node.parent === "root") {
            methods.onAdd({'nodeType': 'node', 'action': event.type, 'id': data.node.data});
        }
        if (data.node.parent === 'node') {
            methods.onAdd({'nodeType': 'device', 'action': event.type, 'id': data.node.data});
        }
        if (data.node.parent === 'device') {
            methods.onAdd({'nodeType': 'tag', 'action': event.type, 'id': data.node.data});
        }
        data.instance.refresh();*/

    }).on('rename_node.jstree', function (event, data) {
        var nodeType = data.node.parent.replace(/[0-9]/g, '');
        if (nodeType === "root") {
            methods.onAdd({'nodeType': 'node', 'action': event.type, 'nodeName': data.node.text}, data);
        }
        if (nodeType === 'node') {
            var nodeId = data.node.parent.replace(/\D+/g, '');
            methods.onAdd({'nodeType': 'device', 'action': event.type, 'nodeName': data.node.text, 'id': nodeId});
        }
        if (nodeType === 'device') {
            var devId = data.node.parent.replace(/\D+/g, '');
            methods.onAdd({'nodeType': 'tag', 'action': event.type, 'nodeName': data.node.text, 'id': devId});
        }
        //data.instance.refresh();
    });


    var methods = {
        node: function (id, type) {
            $.ajax({
                url: "ModbusTreeEdit.do",
                type: "GET",
                data: {"id": id, "type": type},
                success: function (data) {
                    if (data.type === "rtu") {
                        var html = getForm.getRtu();
                        addDeleteForm(html);
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
                        var html = getForm.getTcp();
                        addDeleteForm(html);
                        $("input[name=nodename]").val(data.name);
                        $("select[name=modbustype]").val(data.type);
                        $("input[name=ip]").val(data.tcpEntity.ip);
                        $("input[name=port]").val(data.tcpEntity.port);
                        $("input[name=retries]").val(data.tcpEntity.retries);
                        $("input[name=timeout]").val(data.tcpEntity.timeout);
                        $("input[name=period]").val(data.tcpEntity.period);
                    }
                }
            });
        },
        device: function (id, type) {
            $.ajax({
                url: "ModbusTreeEdit.do",
                type: "GET",
                data: {"id": id, "type": type},
                success: function (data) {
                    var html = getForm.getDevice();
                    addDeleteForm(html);
                    $("input[name=devicename]").val(data.name);
                    $("input[name=slaveid]").val(data.slaveid);
                    $("input[name=startoffset]").val(data.startOffset);
                    $("input[name=counts]").val(data.counts);
                    $("select[name=regtype]").val(data.registerEntity.name);
                }
            });
        },
        tag: function (id, type) {
            $.ajax({
                url: "ModbusTreeEdit.do",
                type: "GET",
                data: {"id": id, "type": type},
                success: function (data) {
                    var html = getForm.getTag();
                    addDeleteForm(html);
                    $("input[name=tagname]").val(data.name);
                    $("input[name=realoffset]").val(data.realOffset);
                    $("select[name=datatype]").val(data.datatypeEntity.name);
                }
            });
        },
        onAdd: function (obj, data) {
            $.ajax({
                url: 'ModbusTreeEdit.do',
                type: 'POST',
                data: obj,
                success: function (json) {
                    console.log(json.id);
                    //data.instance.set_id(data.node, json.id);
                }
            });
        }
    };

    function addDeleteForm(html) {
        var mparams = $('.form_style');
        mparams.empty();
        mparams.append(html)
    }

    var getForm = {
        getRtu: function () {
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
        },
        getTcp: function () {
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
        getDevice: function () {
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
        getTag: function () {
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
        }
    }
});


