$(function () {
    $('.menu-icon').click(function () {
        if ($('#navigator').css("left") == "-250px") {
            $('#navigator').animate({left: '0px'}, 350);
            $('.menu-icon').animate({left: '250px'}, 350);
            $('.menu-text').animate({left: '300px'}, 350).empty().text("Закрыть");
        }
        else {
            $('#navigator').animate({left: '-250px'}, 350);
            $(this).animate({left: '0px'}, 350);
            $('.menu-text').animate({left: '50px'}, 350).empty().text("Меню");
        }
    });
    $('.menu-icon').click(function () {
        $(this).toggleClass("on");
    });

    $('#datetimepicker1').datetimepicker({
        lang: 'ru',
        format: 'Y-m-d H:i'
    });
    $('#datetimepicker2').datetimepicker({
        lang: 'ru',
        format: 'Y-m-d H:i'
    });
});