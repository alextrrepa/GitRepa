$(function () {
    $('#tableContainer').jtable({
        title: 'Table of people',
        fields: {
            name: {
                title: "Name",
                width: '40%'
            },
            age: {
                title: 'Age',
                width: '20%'
            },
            birth: {
                title: 'Birth',
                width: '40%'
            }
        }
    });
});