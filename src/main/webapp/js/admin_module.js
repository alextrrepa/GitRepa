$(function () {

    /* Create expandable tree */
    $.ajax({
        url: "ModbusEdit.do",
        type: "POST",
        data: {"action": "getAll"},
        dataType: "json",
        success: function (data) {
            var tree = $(".tree").createTree(data);
            tree.init();
        }
    });

    $(".tree").on('click', 'li div', function () {
        $(this).toggleClass("fill_state_pressed");
        $("li div").not(this).removeClass("fill_state_pressed");

        var parent = $(this).parent()[0];
        var node = $(parent).data();

        var tree = $(".tree").createTree();
        tree.onTagClick(node);
    });

    /* Context Menu Tree */
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
            if (ui.cmd === "add") {
                var tree = $(".tree").createTree();
                tree.onTagAdd();

                //treeElement.addNode({"nodeType": $(target).attr("data-nodetype"),
                //    "id": $(target).attr("data-nodeid")});
            }
            if (ui.cmd === "subRtu") {
                //treeElement.addNode({"nodeType": $(target).attr("data-nodetype"), "type": "rtu",
                //    "id": $(target).attr("data-nodeid")});
            }
            if (ui.cmd === "subTcp") {
                //treeElement.addNode({"nodeType": $(target).attr("data-nodetype"), "type": "tcp",
                //    "id": $(target).attr("data-nodeid")});
            }
            /*if (ui.cmd === "delete") {
             treeElement.deleteNode(target);
             }*/
        }
    });
});


