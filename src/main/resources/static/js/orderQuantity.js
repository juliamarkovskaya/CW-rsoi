    src = "https://code.jquery.com/jquery-3.4.1.slim.min.js"
    integrity = "sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
    crossOrigin = "anonymous"
$(document).ready(function (){
    $(".minusButton").on("click", function (evt){
        evt.preventDefault();
        idBook = $(this).attr("pid");
        qtyInput = $("#orderQuantity" + idBook);

        newQty = parseInt(qtyInput.val()) - 1;
        if (newQty > 0) qtyInput.val(newQty);
    });

    $(".plusButton").on("click", function (evt){
        evt.preventDefault();
        idBook = $(this).attr("pid");
        qtyInput = $("#orderQuantity" + idBook);

        newQty = parseInt(qtyInput.val()) + 1;
        if (newQty > 0) qtyInput.val(newQty);
    });
});
