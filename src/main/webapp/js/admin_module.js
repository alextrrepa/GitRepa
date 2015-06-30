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
        var type = $(this).parent()[0];
        var nodetype = $(type).data();
        console.log(nodetype);
    });
});