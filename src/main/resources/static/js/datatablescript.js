$(document).ready(function() {
    var table = $('#datatable').DataTable( {
        responsive: true
    } );

    new $.fn.dataTable.FixedHeader( table );
} );