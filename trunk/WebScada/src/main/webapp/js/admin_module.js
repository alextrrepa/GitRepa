$(function () {
    $("#tree").jstree({
        'plugins': ['ccrm', 'contextmenu'],
        'contextmenu': {
            'select_node': false,
            'items': function (node) {
                var items = {
                    'Add_RtuNode': {
                        'separator_after': true,
                        'label': 'Добавить узел RTU',
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {icon: "images/icn_node.png", data: "rtu"}, "last", function (new_node) {
                                setTimeout(function () {
                                    inst.edit(new_node);
                                }, 0);
                            });
                        }
                    },
                    'Add_TcpNode': {
                        'separator_after': true,
                        'label': 'Добавить узел TCP',
                        'action': function(data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.create_node(obj, {icon: "images/icn_node.png", data: "tcp"}, "last", function (new_node) {
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
                    },
                    'Rename': {
                        'label': "Переименовать узел",
                        'action': function (data) {
                            var inst = $.jstree.reference(data.reference),
                                obj = inst.get_node(data.reference);
                            inst.edit(obj);
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
                        delete items.Add_RtuNode;
                        delete items.Add_TcpNode;
                        delete  items.Add_Tag;
                    },
                    device: function () {
                        delete items.Add_Device;
                        delete items.Add_RtuNode;
                        delete items.Add_TcpNode;
                    },
                    tag: function () {
                        delete items.Add_Device;
                        delete items.Add_RtuNode;
                        delete items.Add_TcpNode;
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
    }).on('select_node.jstree', function(event, data) {
        var id = data.node.data;
        var type = data.node.id.replace(/[0-9]/g, '');
        var select = new TreeOperations();
        select.onNodeClick(id, type);
    }).on('delete_node.jstree', function(event, data) {
        var id = data.node.data;
        var type = data.node.id.replace(/[0-9]/g, '');
        var deleteNode = new TreeOperations();
        deleteNode.onDelete(id, type, data);
    }).on('create_node.jstree', function(event, data) {
        var nodeType = data.node.parent.replace(/[0-9]/g, '');
        var nodeId = data.node.parent.replace(/\D+/g, '');
        var name = data.node.text;
        var mtype = data.node.data;

        var addNode = new TreeOperations(name);
        addNode.onAdd(nodeId, nodeType, data, mtype);
    }).on('rename_node.jstree', function(event, data) {
        var id = data.node.data;
        var type = data.node.id.replace(/[0-9]/g, '');
        var name = data.node.text;
        var renameNode = new TreeOperations();
        renameNode.onRename(id, type, name);
    });

    function TreeOperations(name) {
        this.name = name;
    }

    TreeOperations.prototype.onNodeClick = function (id, type) {
        var types = {
            node: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getNode"}
                });
                resp.success(function (json) {
                    switch (json.type) {
                        case 'rtu':
                            var rForm = forms();
                            rForm.rtuForm(type, id);
                            $("input[name=nodename]").attr("readonly", "readonly");
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
                            break;
                        case 'tcp':
                            var tForm = forms();
                            tForm.tcpForm(type, id);
                            $("input[name=nodename]").attr("readonly", "readonly");
                            $("input[name=nodename]").val(json.name);
                            $("select[name=modbustype]").val(json.type);
                            $("input[name=ip]").val(json.tcpEntity.ip);
                            $("input[name=port]").val(json.tcpEntity.port);
                            $("input[name=retries]").val(json.tcpEntity.retries);
                            $("input[name=timeout]").val(json.tcpEntity.timeout);
                            $("input[name=period]").val(json.tcpEntity.period);
                            break;
                        default :
                            var dForm = forms();
                            dForm.defForm(type, id);
                            $("input[name=nodename]").attr("readonly", "readonly");
                            $("input[name=nodename]").val(json.name);
                            $("select[name=modbustype]").val(json.type);
                            break;
                    }
                });
            },
            device: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getDevice"}
                });
                resp.success(function (json) {
                    var params = [
                        {labelName: "Имя узла", inputName: "devicename"},
                        {labelName: "SlaveId", inputName: "slaveid"},
                        {labelName: "Начальное смещение", inputName: "startoffset"},
                        {labelName: "Количество", inputName: "counts"},
                        {labelName: "Тип регистров", selectName: "regtype", options:{
                            '-': '',
                            COIL_STATUS: 1,
                            HOLDING_REGISTER: 3,
                            INPUT_REGISTER: 4,
                            INPUT_STATUS: 2
                        }}
                    ];
                    createForm(params, type, id);
                    $("input[name=devicename]").attr("readonly", "readonly");
                    $("input[name=devicename]").val(json.name);
                    $("input[name=slaveid]").val(json.slaveid);
                    $("input[name=startoffset]").val(json.startOffset);
                    $("input[name=counts]").val(json.counts);
                    $("select[name=regtype]").val(json.registerEntity.value);
                });
            },
            tag: function () {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "action": "getTag"}
                });
                resp.success(function (json) {
                    var params = [
                        {labelName: "Имя узла", inputName: "tagname"},
                        {labelName: "Смещение", inputName: "realoffset"},
                        {labelName: "Тип даты", selectName: "datatype", options:{
                            '-': '',
                            BINARY: 1,
                            CHAR: 18,
                            EIGHT_BYTE_FLOAT: 14,
                            EIGHT_BYTE_FLOAT_SWAPPED: 15,
                            EIGHT_BYTE_INT_SIGNED: 11,
                            EIGHT_BYTE_INT_SIGNED_SWAPPED: 13,
                            EIGHT_BYTE_INT_UNSIGNED: 10,
                            EIGHT_BYTE_INT_UNSIGNED_SWAPPED: 12,
                            FOUR_BYTE_BCD: 17,
                            FOUR_BYTE_BCD_SWAPPED: 20,
                            FOUR_BYTE_FLOAT: 8,
                            FOUR_BYTE_FLOAT_SWAPPED: 9,
                            FOUR_BYTE_INT_SIGNED: 5,
                            FOUR_BYTE_INT_SIGNED_SWAPPED: 7,
                            FOUR_BYTE_INT_UNSIGNED: 4,
                            FOUR_BYTE_INT_UNSIGNED_SWAPPED: 6,
                            TWO_BYTE_BCD: 16,
                            TWO_BYTE_INT_SIGNED: 3,
                            TWO_BYTE_INT_UNSIGNED: 2,
                            VARCHAR: 19
                        }}
                    ];
                    createForm(params, type, id);
                    $("input[name=tagname]").attr("readonly", "readonly");
                    $("input[name=tagname]").val(json.name);
                    $("input[name=realoffset]").val(json.realOffset);
                    $("select[name=datatype]").val(json.datatypeEntity.value);
                });
            }
        };
        try {
            types[type]();
        } catch (e) {
            addDeleteForm();
            alertError($('.form_style'), "Нет формы");
        }
    };

    TreeOperations.prototype.onAdd = function (id, type, data, mtype) {
        var nodeName = this.name;
        var types = {
            root: function () {
                if (mtype === 'rtu') {
                    var resp = treeCrudRequest({
                        "url": "ModbusEdit.do",
                        "data": {"action": "addRtuNode", "nodeName": nodeName, "mtype": mtype}
                    });
                    resp.success(function (json) {
                        console.log(type);
                        console.log(json);
                        data.instance.set_id(data.node, json.id);
                        data.node.data = json.data;
                        data.instance.refresh();
                    });
                }
                if (mtype === 'tcp') {
                    var resp = treeCrudRequest({
                        "url": "ModbusEdit.do",
                        "data": {"action": "addTcpNode", "nodeName": nodeName, "mtype": mtype}
                    });
                    resp.success(function (json) {
                        console.log(type);
                        console.log(json);
                        data.instance.set_id(data.node, json.id);
                        data.node.data = json.data;
                        data.instance.refresh();
                    });
                }
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

    TreeOperations.prototype.onUpdate = function(obj) {
        var types = {
            node: function() {
                var resp = treeCrudRequest(obj);
                resp.success(function(json) {
                    if (json.type === "rtu") {
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
            device: function() {
                var resp = treeCrudRequest(obj);
                resp.success(function(json) {
                    $("input[name=devicename]").val(json.name);
                    $("input[name=slaveid]").val(json.slaveid);
                    $("input[name=startoffset]").val(json.startOffset);
                    $("input[name=counts]").val(json.counts);
                    $("select[name=regtype]").val(json.registerEntity.value);
                });
            },
            tag: function() {
                var resp = treeCrudRequest(obj);
                resp.success(function(json) {
                    $("input[name=tagname]").val(json.name);
                    $("input[name=realoffset]").val(json.realOffset);
                    $("select[name=datatype]").val(json.datatypeEntity.value);
                });
            }
        };
        try {
            types[obj.data.type]();
        }catch(e) {
            addDeleteForm();
            alertError($('.form_style'), "Ошибка ввода" + " " + e.message);
        }
    };

    TreeOperations.prototype.onRename = function(id, type, text) {
        var types = {
            node: function() {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "text": text, "action": "rename"}
                });
                resp.success(function(json) {
                    $("input[name=nodename]").val(json);
                });
            },
            device: function() {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "text": text, "action": "rename"}
                });
                resp.success(function(json) {
                    $("input[name=devicename]").val(json);
                });
            },
            tag: function() {
                var resp = treeCrudRequest({
                    "url": "ModbusEdit.do",
                    "data": {"id": id, "type": type, "text": text, "action": "rename"}
                });
                resp.success(function(json) {
                    $("input[name=tagname]").val(json);
                });
            }
        };
        try {
            types[type]();
        } catch(e) {
            addDeleteForm();
            alertError($('.form_style'), "Ошибка" + e.message);
        }
    };

    function treeCrudRequest(obj) {
        return $.ajax({
            url: obj.url,
            data: obj.data,
            beforeSend: function() {
                var $r = $('.form_style');
                $r.addClass("loading");
            },
            complete: function() {
                var $r = $(".form_style");
                $r.removeClass("loading");
            },
            error: function(xhr, ajaxOptions, thrownError) {
                addDeleteForm();
                alertError($('.form_style'), "Ошибка Ajax запроса :" + " " + thrownError);
            }
        });
    }

    function addDeleteForm(html) {
        var mparams = $('.form_style');
        mparams.empty();
        mparams.append(html);
    }

    function alertError(selector, text) {
        var $div = $('<div>');
        var $h4 = $('<h4>');
        $h4.addClass("error_loading").text(text);
        $div.append($h4);
        var $selector = selector;
        $selector.append($div);
    }

    var forms = function() {
        function createForm (params, type, id) {
            var $formElement = $('<form>');
            $formElement.attr({'type': type});
            $formElement.attr({'id': id});
            params.forEach(function(item) {
                var $label = $('<label>');
                var $firstSpan = $('<span>'),
                    $secondSpan = $('<span>');
                var $input = $('<input>');
                var $select = $('<select>');

                $firstSpan.text(item.labelName);
                $firstSpan.append($secondSpan.text('*').addClass('required'));
                $label.append($firstSpan);
                $formElement.append($label);

                if (item.hasOwnProperty('selectName')) {
                    $select.attr({'name': item.selectName}).addClass('select-field');
                    var opArray = item.options;
                    var op = [];
                    $.each(opArray, function(key, value) {
                        var $option = $('<option>');
                        $option.attr({'value': value}).text(key);
                        op.push($option);
                    });
                    $label.append($select.append(op));
                }
                if (item.hasOwnProperty('button')) {
                    $formElement.append($('<input>').attr({'type': 'button', 'value': 'Сохранить'}));
                }
                if (item.hasOwnProperty('inputName')) {
                    $input.attr({'type': 'text', 'name': item.inputName}).addClass('input-field');
                    $label.append($input);
                }
            });
            //$formElement.append($('<input>').attr({'type': 'button', 'value': 'Сохранить'}));
            addDeleteForm($formElement);
        }
        function defCreateForm(params, type, id) {
            var $formElement = $('form');
            $formElement.attr({'type': type});
            $formElement.attr({'id': id});
            params.forEach(function(item) {
                var $label = $('<label>');
                var $firstSpan = $('<span>'),
                    $secondSpan = $('<span>');
                var $input = $('<input>');
                var $select = $('<select>');

                $firstSpan.text(item.labelName);
                //$firstSpan.append($secondSpan.text('*').addClass('required'));
                //$label.append($firstSpan);
                //$formElement.append($label);

                if (item.hasOwnProperty('selectName')) {
                    $firstSpan.append($secondSpan.text('*').addClass('required'));
                    $label.append($firstSpan);
                    $formElement.append($label);

                    $select.attr({'name': item.selectName}).addClass('select-field');
                    var opArray = item.options;
                    var op = [];
                    $.each(opArray, function(key, value) {
                        var $option = $('<option>');
                        $option.attr({'value': value}).text(key);
                        op.push($option);
                    });
                    $label.append($select.append(op));
                }
                if (item.hasOwnProperty('button')) {
                    $formElement.append($('<input>').attr({'type': 'button', 'value': item.button}));
                }
                if (item.hasOwnProperty('inputName')) {
                    $firstSpan.append($secondSpan.text('*').addClass('required'));
                    $label.append($firstSpan);
                    $formElement.append($label);

                    $input.attr({'type': 'text', 'name': item.inputName}).addClass('input-field');
                    $label.append($input);
                }
            });
        }
        return {
            rtuForm: function(id, type) {
                var params = [
                    {labelName: "Имя узла", inputName: "nodename"},
                    {labelName: "Тип modbus", selectName: "modbustype", options:{
                        '-': '',
                        RTU: 'rtu',
                        TCP: 'tcp'
                    }},
                    {labelName: "Порт", inputName: "port"},
                    {labelName: "Скорость", selectName: "baudrate", options:{
                        '-': '',
                        1200: 1200,
                        1800: 1800,
                        2400: 2400,
                        4800: 4800,
                        9600: 9600,
                        19200: 19200,
                        38400: 38400,
                        57600: 57600,
                        115200: 115200
                    }},
                    {labelName: "Данные", selectName: "databits", options:{
                        '-': '',
                        5: 5,
                        6: 6,
                        7: 7,
                        8:8
                    }},
                    {labelName: "Четность", inputName: "parity"},
                    {labelName: "Стоп биты", selectName: "stopbits", options:{
                        '-': '',
                        0: 0,
                        1: 1,
                        1.5: 1.5,
                        2:2
                    }},
                    {labelName: "Повторы при ошибке", inputName: "retries"},
                    {labelName: "Время ответа", inputName: "timeout"},
                    {labelName: "Период опроса", inputName: "period"},
                    {button: "Сохранить"}
                ];
                createForm(params, id, type);
            },
            tcpForm: function(id, type) {
                var params = [
                    {labelName: "Имя узла", inputName: "nodename"},
                    {labelName: "Тип modbus", selectName: "modbustype", options:{
                        '-': '',
                        RTU: 'rtu',
                        TCP: 'tcp'
                    }},
                    {labelName: "IP адрес", inputName: "ip"},
                    {labelName: "Порт", inputName: "port"},
                    {labelName: "Повторы при ошибке", inputName: "retries"},
                    {labelName: "Время ответа", inputName: "timeout"},
                    {labelName: "Период опроса", inputName: "period"}
                ];
                createForm(id, type);
            },
            deviceForm: function() {
            },
            tagForm: function() {
            },
            defForm: function(id, type) {
                var params = [
                    {labelName: "Имя узла", inputName: "nodename"},
                    {labelName: "Тип modbus", selectName: "modbustype", options:{
                        '-': '',
                        RTU: 'rtu',
                        TCP: 'tcp'
                    }},
                ];
                createForm(params, id, type);
                $('.form_style select.select-field').change(function(){
                    if ($(this).val() === 'rtu') {
                        var params = [
                            {labelName: "Порт", inputName: "port"},
                            {labelName: "Скорость", selectName: "baudrate", options:{
                                1200: 1200,
                                1800: 1800,
                                2400: 2400,
                                4800: 4800,
                                9600: 9600,
                                19200: 19200,
                                38400: 38400,
                                57600: 57600,
                                115200: 115200
                            }},
                            {labelName: "Данные", selectName: "databits", options:{
                                5: 5,
                                6: 6,
                                7: 7,
                                8: 8
                            }},
                            {labelName: "Четность", inputName: "parity"},
                            {labelName: "Стоп биты", selectName: "stopbits", options:{
                                0: 0,
                                1: 1,
                                1.5: 1.5,
                                2:2
                            }},
                            {labelName: "Повторы при ошибке", inputName: "retries"},
                            {labelName: "Время ответа", inputName: "timeout"},
                            {labelName: "Период опроса", inputName: "period"},
                            {button: "Сохранить"}
                        ];
                        $(this).parent().nextAll().remove();
                        defCreateForm(params, id, type);
                    }
                    if ($(this).val() === 'tcp') {
                        var params = [
                            {labelName: "IP адрес", inputName: "ip"},
                            {labelName: "Порт", inputName: "port"},
                            {labelName: "Повторы при ошибке", inputName: "retries"},
                            {labelName: "Время ответа", inputName: "timeout"},
                            {labelName: "Период опроса", inputName: "period"},
                            {button: "Сохранить"}
                        ];
                        $(this).parent().nextAll().remove();
                        defCreateForm(params, id, type);
                    }
                });

            }
        }
    };

    $('.form_style').on('click', 'input[type="button"]', function() {
        var formAttr = $('form').attr('type');
        var id = $('form').attr('id');
        var values = {};
        var d = {};
        d.id = id;
        $.each($('form').serializeArray(), function(i, field) {
            d[field.name] = field.value;
        });
        d.type = formAttr;
        d.action = 'update';
        values.url = 'ModbusEdit.do';
        values.data = d;
        var updateParams = new TreeOperations();
        updateParams.onUpdate(values);
    });


});