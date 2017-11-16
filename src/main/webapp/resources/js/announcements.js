$(document).ready(function() {
    $('#submitButton').on('click', function (e) {
        e.preventDefault();
        doSubmit();
    });

    function doSubmit() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var user = ($('#username').html()).toString();

        $('#createAnnouncementModal').modal('hide');

        var announcement = {
            "username": user,
            "title": ($('#announceTitleModal').val()).toString(),
            "message": ($('#announceMessageModal').val().toString()),
            "date": $('#announceDateModal').val(),
            "campus": ($('#announceCampusModal').val().toUpperCase()).toString()
        };

        console.log(announcement);
        $.ajax({
            type: 'POST',
                beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: api + '/add',
            data: JSON.stringify(announcement),
            success: function() {
                alert("Saved successfully");
                location.reload();
            },
            error: function() {
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



