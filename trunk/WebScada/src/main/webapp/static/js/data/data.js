$(function () {
    $('#datetimepicker1').datetimepicker({
        lang: 'ru',
        format: 'Y-m-d H:i'
    });

    $('#datetimepicker2').datetimepicker({
        lang: 'ru',
        format: 'Y-m-d H:i'
    });

    var startdate = $('#datetimepicker1').val();
    var enddate = $('#datetimepicker2').val();
    var datatype = $("#datatype").val();
    var datatypeText = $("#datatype option:selected").text();

    $("#okbutton").click(function (event) {
        $(".col-lg-9 h2").text(datatypeText);
        $.ajax({
            url: 'data',
            data: {"startdatetime": startdate, "enddatetime": enddate, "datatype": datatype, "action": "viewing"},
            dataType: 'json',
            success: function (json) {
                console.log(json);
                $.each(json, function (index, element) {
                    //$("#datatable").append('<th>' + element.columnName + '</th>');
                    //console.log(element.columnName);
                    $.each(element.hourEntities, function (key, value) {
                        //console.log(value.dtime);
                        //console.log(value.value);
                    });
                });
            }
        });
    });
});