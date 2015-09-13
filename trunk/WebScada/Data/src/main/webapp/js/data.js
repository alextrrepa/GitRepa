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

    /*$('#tableContainer').jtable({
        title: 'Demo',
        paging: true,
        pageSize: 5,
        actions: {
            listAction: 'Report.do?actions=hoursData'
        },
        fields: {
            h_id: {
                title: "H_id",
                width: '10%'
            },
            tag_id: {
                title: "Tag_id",
                width: '10%'
            },
            dtime: {
                title: 'Dtime',
                width: '40%',
                type: 'datetime',
                displayFormat: 'YYYY-MM-DD HH:MM:SS'
            },
            value: {
                title: 'Value',
                width: '40%'
            }
        }
    });
     $('#tableContainer').jtable('load');*/
});