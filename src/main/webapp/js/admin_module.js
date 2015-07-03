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
    var treeLi = $(".tree li div");
    treeLi.click(function() {
        $(this).toggleClass("fill_state_pressed");
        treeLi.not(this).removeClass("fill_state_pressed");
        var parent = $(this).parent()[0];
        var node = $(parent).data();

        if (node.nodetype === "node") {
            ajaxRequest(node.nodeid, node.nodetype);
        }

        if (node.nodetype === "device") {
            ajaxRequest(node.nodeid, node.nodetype);
        }

        if (node.nodetype === "tag") {
            ajaxRequest(node.nodeid, node.nodetype);
        }
    });

    function ajaxRequest(id,type) {
        $.ajax({
            url: "ModbusTreeEdit.do?type="+type + "&" + "id=" +id
        });
    }
});
