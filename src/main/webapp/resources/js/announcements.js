$(document).ready(function() {

    $('#submitButton').on('click', function (e) {
        e.preventDefault();
        doSubmit();
    });

    $('#sortAnnounce #sortAnnounce2').sort(sortDescending).appendTo('#sortAnnounce');

    function doSubmit() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $('#createAnnouncementModal').modal('hide');

        var announcement = {
            "username": user.trim(),
            "title": $('#announceTitleModal').val().trim(),
            "message": $('#announceMessageModal').val(),
            "campus": $('#announceCampusModal').val().toUpperCase().toString()
        };

        console.log(announcement);
        $.ajax({
            type: 'POST',
                beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: api + '/announcements/add',
            data: JSON.stringify(announcement),
            success: function() {
                alert("Saved successfully");
                location.reload();
            },
            error: function() {
                alert("error saving announcement to DB");
            },
            contentType: "application/json; charset=utf-8"
        });
    }



    $('#createAnnouncement').on('click', function () {
        $('#createAnnouncementModal').modal('show');
    });
});

function sortDescending(a, b) {

    // console.log(burnabySort);
    var date1 = $(a).find("#announceDate").text();
    date1 = date1.split('-');

    date1 = new Date(date1[2], date1[1] - 1, date1[0]);
    var date2 = $(b).find("#announceDate").text();
    date2 = date2.split('-');

    date2 = new Date(date2[2], date2[1] - 1, date2[0]);

    return date1 < date2 ? 1 : -1;
};

if ($('.allOrNone').is(':checked')) {
    $('.campusFilter').prop("checked", true)
    $('.empty').hide();
}
else {
    $('.campusFilter').prop("checked", false);
}

function allOrNone(a) {
    var checkBox = document.querySelectorAll('input[type="checkbox"]');
    for (var i = 0; i < checkBox.length; i++) {
        if (checkBox[i] != a)
            checkBox[i].checked = a.checked;
    }
}