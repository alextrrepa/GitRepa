$(function () {
    $('#tableContainer').jtable({
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
    $('#tableContainer').jtable('load');
});