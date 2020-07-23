$('document').ready(function () {
    $('table #editButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
        //event.preventDefault();
        $.get(href, function (sale, status) {
            $('#idEdit').val(sale.id);
            $('#customernameEdit').val(sale.customername);
            $('#dateEdit').val(sale.date);
            $('#rateEdit').val(sale.rate);
            $('#quantityEdit').val(sale.quantity);
            $('#priceEdit').val(sale.price);

        });

        $('#editModal').modal();
    });
    $('table #deleteButton').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');

        $('#confirmdeletebutton').attr('href',href);
        $('#deletemodal').modal();
});
});