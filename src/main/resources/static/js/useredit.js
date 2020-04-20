$('document').ready(function () {
    $('table #editButton').on('click',function (event) {
        event.preventDefault();
        var href=$(this).attr('href');

        $.get(href,function (user,status) {
            $('#idEdit').val(user.id);
            $('#fnameEdit').val(user.fname);
            $('#mnameEdit').val(user.mname);
            $('#lnameEdit').val(user.lname);
            $('#emailEdit').val(user.email);
            $('#phoneEdit').val(user.phone);
            //$('#dataimageEdit').val(user.dataimage);
        });

        $('#editModal').modal();
    });
});