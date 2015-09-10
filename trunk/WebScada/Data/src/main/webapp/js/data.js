$(function () {


    $('#tableContainer').jtable({
        title: 'Demo',
        actions: {
            listAction: '/Report.do?actions=hoursData'
        },
        fields: {
            hid: {
                title: "H_id",
                width: '10%'
            },
            tagid: {
                title: "Tag_id",
                width: '10%'
            },
            dtime: {
                title: 'Dtime',
                width: '40%'
            },
            value: {
                title: 'Value',
                width: '40%'
            }
        }
    });
});