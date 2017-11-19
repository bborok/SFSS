
function sortDescending(a, b) {
    var date1 = $(a).find("#announceDate").text();
    date1 = date1.split('-');

    date1 = new Date(date1[2], date1[1] - 1, date1[0]);
    var date2 = $(b).find("#announceDate").text();
    date2 = date2.split('-');

    date2 = new Date(date2[2], date2[1] - 1, date2[0]);

    return date1 < date2 ? 1 : -1;
};


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
            // dataType: "json",
                contentType: "application/json; charset=utf-8"
        });
    }

    $('#createAnnouncement').on('click', function () {
        $('#createAnnouncementModal').modal('show');
    });
});



