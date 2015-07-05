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
            nodeRequest(node.nodeid, node.nodetype, node.mtype);
        }

        if (node.nodetype === "device") {
            otherNodeRequest(node.nodeid, node.nodetype);
        }

        if (node.nodetype === "tag") {
            otherNodeRequest(node.nodeid, node.nodetype);
        }
    });

    function otherNodeRequest(id,type) {
        $.ajax({
            url: "ModbusTreeEdit.do?type="+type + "&" + "id=" +id,
            success: function(result) {
                console.log(result);
            }
        });
    }

    function nodeRequest(id, type, mtype) {
        $.ajax({
            url: "ModbusTreeEdit.do?type="+type + "&" + "mtype=" + mtype + "&" + "id=" +id,
            success: function(result) {
                console.log(result);
            }
        });
    }
});
