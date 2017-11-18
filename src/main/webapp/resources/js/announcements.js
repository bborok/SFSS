$(document).ready(function() {
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
        doSubmit();
    });

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



