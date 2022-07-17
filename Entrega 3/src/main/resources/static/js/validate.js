/**
 * Ativando validações básicas no front-end.
 */
 (function () {
    'use strict'
    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

// /**
//  * This will style all tables with id "#table_report_list"
//  * on it. This will be the pattern in the next version.
//  */
//  $(document).ready(function () {
//     $('#table-test').DataTable({
//         "bInfo": false,
//         "responsive": "true",
//         "paging": "true",
//         "pageLength": "5",
//         "lengthMenu": "[5, 10, 25, 50, 100]",
//         "order": "[[0, 'desc']]"
//     });
// });

/**
 * Estilizando as tabelas usando o jQuery.
 */
 $(document).ready(function() {
    $('#table-test').DataTable({
        "language": {
            "bInfo": false,
            "responsive": true,
            "paging": false,
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "zeroRecords": "Nenhum registro encontrado",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Nenhum registro disponível",
            "infoFiltered": "(filtrado de _MAX_ registros)",
            "search": "Buscar:",
            "paginate": {
                "first": "Primeira",
                "last": "Última",
                "next": "Próxima",
                "previous": "Anterior"
            }
        }
    });
})