;(function($) {
    $.fn.createTree = function(options) {
        $(this.selector + " " + "ul").hide();
        console.log(this.selector);

        $(this.selector + " " + "li").each(function () {
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
    };

    var methods = {
        init: function() {

        },
    }
})(jQuery);