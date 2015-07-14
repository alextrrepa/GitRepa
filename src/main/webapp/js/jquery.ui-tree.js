;(function($) {
    $.fn.createTree = function(options) {

        methods.init();

        $(this.selector + " " + "ul").hide();

        $(this.selector + " " + "li").each(function () {

            if($(this).has("ul").size() > 0) {
                var handleSpan = $("<span></span>");
                handleSpan.addClass("spanExpand");
                handleSpan.prependTo(this);

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
            $.ajax({
                url: "ModbusEdit.do",
                type: "POST",
                data: {"action": "getAll"},
                dataType: "json",
                success: function(data) {
                    var json = JSON.parse(JSON.stringify(data));
                    console.log(json);
                }
            });
        }
    }
})(jQuery);