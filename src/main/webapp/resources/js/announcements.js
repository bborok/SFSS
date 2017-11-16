$(document).ready(function() {
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
        doSubmit();
    });



    function doSubmit() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var user = $('#username').html();

        $('#createAnnouncementModal').modal('hide');

        var announcement = {
            "user": user,
            "title": $('#announceTitleModal').val(),
            "message": $('#announceMessageModal').val(),
            "date": $('#announceDateModal').val(),
            "campus": ($('#announceCampusModal').val().toUpperCase())
        };

        console.log(announcement);
        $.ajax({
            type: 'POST',
                beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: api + '/add',
            data: JSON.stringify(announcement),
            success: function(data) {
                alert("Saved successfully");
                location.reload();
            },
            error: function(e) {
                alert("error saving announcement to DB");
            },
            dataType: "json",
                contentType: "application/json; charset=utf-8"
        });
    }

    $('#createAnnouncement').on('click', function () {
        $('#createAnnouncementModal').modal('show');
    });
});



