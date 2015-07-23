$(function () {
    $("#tree").jstree({
        'plugins' : ['types'],
        'core': {
            'themes': {
              "name": "default-dark"
            },
            'types': {
                "rootElem": {
                    "icon": {
                        "image" : "/icn_settings.png"
                    }
                }
            },
            'data': {
                'url': 'ModbusEdit.do?action=getAll'
            }
        }
    });
   /* var tree;
    /!* Create expandable tree *!/
    $.ajax({
        url: "ModbusEdit.do",
        type: "POST",
        data: {"action": "getAll"},
        dataType: "json",
        success: function (data) {
            tree = $(".tree").createTree(data);
            tree.init();
        }
    });

    $(".tree").on('click', 'li div', function () {
        $(this).toggleClass("fill_state_pressed");
        $("li div").not(this).removeClass("fill_state_pressed");

        var parent = $(this).parent()[0];
        var node = $(parent).data();
        tree.onTagClick(node);
    });

    /!* Context Menu Tree *!/
    $(".tree").contextmenu({
        beforeOpen: function (event, ui) {
            var parent = ui.target.parent()[0];
            var node = $(parent).data();
            switch (node.nodetype) {
                case "root":
                    $(".tree").contextmenu({
                        delegate: "li",
                        menu: [
                            {
                                title: "Добавить узел", cmd: "add", uiIcon: "ui-icon-plusthick",
                                children: [
                                    {title: "rtu", cmd: "subRtu"},
                                    {title: "tcp", cmd: "subTcp"}
                                ]
                            },
                        ]
                    });
                    break;
                case "node":
                    $(".tree").contextmenu({
                        delegate: "li",
                        menu: [
                            {title: "Добавить устройство", cmd: "add", uiIcon: "ui-icon-plusthick"},
                            {title: "Удалить узел", cmd: "delete", uiIcon: "ui-icon-trash"}
                        ]
                    });
                    break;
                case "device":
                    $(".tree").contextmenu({
                        delegate: "li",
                        menu: [
                            {title: "Добавить тэг", cmd: "add", uiIcon: "ui-icon-plusthick"},
                            {title: "Удалить устройство", cmd: "delete", uiIcon: "ui-icon-trash"}
                        ]
                    });
                    break;
                case "tag":
                    $(".tree").contextmenu({
                        delegate: "li",
                        menu: [
                            {title: "Удалить тэг", cmd: "delete", uiIcon: "ui-icon-trash"}
                        ]
                    });
                    break;
            }
        },
        select: function (event, ui) {
            var target = ui.target.parent()[0];
            var ulTarget = $(ui.target).next()[0];
            if (ui.cmd === "add") {
                tree.onTagAdd({
                    "nodeType": $(target).attr("data-nodetype"),
                    "id": $(target).attr("data-nodeid"),
                    "action": ui.cmd
                }, ulTarget);
            }
            if (ui.cmd === "subRtu") {
                //tree.onTagAdd({"nodeType": $(target).attr("data-nodetype"), "mtype": "rtu", "action": ui.cmd});
            }
            if (ui.cmd === "subTcp") {
                //tree.onTagAdd({"nodeType": $(target).attr("data-nodetype"), "mtype": "tcp", "action": ui.cmd});
            }
            if (ui.cmd === "delete") {
                /!*tree.onTagDelete({"nodeType": $(target).attr("data-nodetype"), "id":$(target).attr("data-nodeid"),
                    "action": ui.cmd});*!/
            }
        }
    });*/
});


