$(document).ready(function() {
    $("#action tbody tr").each(function(i, tr){
        $(this).find("td button[name=increaseTotal]").on('click',function(){
            var input = $(this).closest("tr").find("input");
            var result = parseInt(input.val())+1;
            if(parseInt(input.val()) >= 0) {
                $(this).closest("tr").find("input").val(result);
            }
        });

        $(this).find("td button[name=decreaseTotal]").on('click',function(){
            var input = $(this).closest("tr").find("input");
            var result = parseInt(input.val())-1;
            if(parseInt(input.val()) > 0) {
                $(this).closest("tr").find("input").val(result);
            }
        });
    });
});