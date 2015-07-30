$(function () {
    $("#tree").jstree({
        'plugins': ['ccrm', 'contextmenu'],
        'contextmenu': {
            'select_node': false,
            'items': function (node) {
                var items = {
                    'Add_Node': {
                        'separator_after': true,
                        'label': 'Добавить коммуникационный узел',
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {icon: "images/icn_node.png"}, "last", function (new_node) {
                                setTimeout(function () {
                                    inst.edit(new_node);
                                }, 0);
                            });
                        }
                    },
                    'Add_Device': {
                        'separator_after': true,
                        'label': 'Добавить устройство',
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {icon: "images/icn_device.png"}, "last", function (new_node) {
                                setTimeout(function () {
                                    inst.edit(new_node);
                                }, 0);
                            });
                        }
                    },
                    'Add_Tag': {
                        'separator_after': true,
                        'label': 'Добавить тэг',
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {icon: "images/icn_tag.png"}, "last", function (new_node) {
                                setTimeout(function () {
                                    inst.edit(new_node);
                                }, 0);
                            });
                        }
                    },
                    'Delete': {
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
                };
                var nodeType = node.id.replace(/[0-9]/g, '');
                var types = {
                    root: function () {
                        delete items.Add_Device;
                        delete items.Add_Tag;
                    },
                    node: function () {
                        delete  items.Add_Node;
                        delete  items.Add_Tag;
                    },
                    device: function () {
                        delete items.Add_Device;
                        delete items.Add_Node;
                    },
                    tag: function () {
                        delete items.Add_Device;
                        delete items.Add_Node;
                        delete items.Add_Tag;
                    }
                };
                types[nodeType]();
                return items;
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
        var type = data.node.id.replace(/[0-9]/g, '');
        var select = new TreeOperations();
        select.onClick(id, type);
    }).on('delete_node.jstree', function (event, data) {
        var id = data.node.data;
        var type = data.node.id.replace(/[0-9]/g, '');
        var deleteNode = new TreeOperations();
        deleteNode.onDelete(id, type, data);
    }).on('rename_node.jstree', function (event, data) {
        var nodeType = data.node.parent.replace(/[0-9]/g, '');
        var nodeId = data.node.parent.replace(/\D+/g, '');
        var name = data.node.text;
        var addNode = new TreeOperations(name);
        addNode.onAdd(nodeId, nodeType, data);

    });

    function TreeOperations(name) {
        this.name = name;
    }

    TreeOperations.prototype.onClick = function (id, type) {
        var types = {
            node: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getNode"}
                });
                resp.success(function (json) {
                    if (json.type === "rtu") {
                        addDeleteForm(getForm.getRtu());
                        $("input[name=nodename]").val(json.name);
                        $("select[name=modbustype]").val(json.type);
                        $("input[name=port]").val(json.rtuEntity.port);
                        $("select[name=baudrate]").val(json.rtuEntity.baudrate);
                        $("select[name=databits]").val(json.rtuEntity.databits);
                        $("input[name=parity]").val(json.rtuEntity.parity);
                        $("select[name=stopbits]").val(json.rtuEntity.stopbits);
                        $("input[name=retries]").val(json.rtuEntity.retries);
                        $("input[name=timeout]").val(json.rtuEntity.timeout);
                        $("input[name=period]").val(json.rtuEntity.period);
                    }
                    if (json.type === "tcp") {
                        addDeleteForm(getForm.getTcp());
                        $("input[name=nodename]").val(json.name);
                        $("select[name=modbustype]").val(json.type);
                        $("input[name=ip]").val(json.tcpEntity.ip);
                        $("input[name=port]").val(json.tcpEntity.port);
                        $("input[name=retries]").val(json.tcpEntity.retries);
                        $("input[name=timeout]").val(json.tcpEntity.timeout);
                        $("input[name=period]").val(json.tcpEntity.period);
                    }
                });
            },
            device: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getDevice"}
                });
                resp.success(function (json) {
                    addDeleteForm(getForm.getDevice());
                    $("input[name=devicename]").val(json.name);
                    $("input[name=slaveid]").val(json.slaveid);
                    $("input[name=startoffset]").val(json.startOffset);
                    $("input[name=counts]").val(json.counts);
                    $("select[name=regtype]").val(json.registerEntity.name);
                });
            },
            tag: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getTag"}
                });
                resp.success(function (json) {
                    //addDeleteForm(getForm.getTag());
                    getForm.getTag();
                    $("input[name=tagname]").val(json.name);
                    //$("input[name=realoffset]").val(json.realOffset);
                    //$("select[name=datatype]").val(json.datatypeEntity.name);

                });
            }
        };
        try {
            types[type]();
        } catch (e) {
            console.log(e.name);
            console.log(e.message);
        }
    };

    TreeOperations.prototype.onAdd = function (id, type, data) {
        var nodeName = this.name;
        var types = {
            root: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"action": "addNode", "nodeName": nodeName}
                });
                resp.success(function (json) {
                    data.instance.set_id(data.node, json.id);
                    data.node.data = json.data;
                    data.instance.refresh();
                });
            },
            node: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"action": "addDevice", "nodeName": nodeName, "id": id}
                });
                resp.success(function (json) {
                    data.instance.set_id(data.node, json.id);
                    data.node.data = json.data;
                    data.instance.refresh();
                });
            },
            device: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"action": "addTag", "nodeName": nodeName, "id": id}
                });
                resp.success(function (json) {
                    data.instance.set_id(data.node, json.id);
                    data.node.data = json.data;
                    data.instance.refresh();
                });
            }
        };
        types[type]();
    };

    TreeOperations.prototype.onDelete = function (id, type, data) {
        var types = {
            node: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    data: {"action": "deleteNode", "id": id}
                });
                resp.success(function (json) {
                    data.instance.refresh();
                });
            },
            device: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    data: {"action": "deleteDevice", "id": id}
                });
                resp.success(function (json) {
                    data.instance.refresh();
                });
            },
            tag: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    data: {"action": "deleteTag", "id": id}
                });
                resp.success(function (json) {
                    data.instance.refresh();
                });
            }
        };
        types[type]();
    };

    function treeCrudRequest(obj) {
        return $.ajax({
            url: obj.url,
            data: obj.data
        });
    }

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
                '<option value=""> - </option>' +
                '<option value="rtu">Rtu</option>' +
                '<option value="tcp">Tcp</option>' +
                '</select>' +
                '</label>' +

                '<label><span>Порт <span class="required">*</span></span>' +
                '<input type="text" name="port" class="input-field"/>' +
                '</label>' +

                '<label><span>Скорость <span class="required">*</span></span>' +
                '<select name="baudrate" class="select-field">' +
                '<option value=""> - </option>' +
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
                '<option value=""> - </option>' +
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
                '<select name="stopbits" class="select-field">' +
                '<option value=""> - </option>' +
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
                '<option value=""> - </option>' +
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
                '<option value=""> - </option>' +
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
            var elems = [];

            var $formElement = $('<form>');
            var $label = $('<label>');
            var $firstSpan = $('<span>');
            var $secondSpan = $('<span>');
            var $input = $('<input>');

            $firstSpan.text('Имя узла');
            $firstSpan.append($secondSpan.text('*').addClass('required'));
            $label.append($firstSpan);
            $input.attr({'type': 'text', 'name': 'tagname'}).addClass('input-field');
            $label.append($input);
            elems.push($label);
            $formElement.append(elems);

            /*$firstSpan.text('Смещение');
            $firstSpan.append($secondSpan.text('*').addClass('required'));
            $label.append($firstSpan);
            $input.attr({'type': 'text', 'name': 'realoffset'}).addClass('input-field');
            $label.append($input);
            elems.push($label);
            $formElement.append(elems);*/

            addDeleteForm($formElement);
        }
/*            return '<form>
<label><span>Имя узла<span class="required">*</span></span>' +
                '<input type="text" name="tagname" class="input-field">' +
                '</label>' +

                '<label><span>Смещение<span class="required">*</span></span>' +
                '<input type="text" name="realoffset" class="input-field">' +
                '</label>' +

                '<label><span>Тип даты <span class="required">*</span></span>' +
                '<select name="datatype" class="select-field">' +
                '<option value=""> - </option>' +
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
        }*/
    }
});


