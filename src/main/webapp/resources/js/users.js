$(document).ready(function () {
    $('#submitButton').on('click', function (e) {
        // We don't want this to act as a link so cancel the link action
        e.preventDefault();

        doSubmit();
    });

    function doSubmit() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $("#userModal").modal('hide');

        var campus = '';
        if ($('#campusBurnaby').prop('checked', true)) {
            campus = 'BURNABY';
        } else if ($('#campusSurrey').prop('checked', true)) {
            campus = 'SURREY';
        } else {
            campus = 'VANCOUVER';
        }

        var user = {
            "username": $('#username').val(),
            "studentNumber": $('#studentNumber').val(),
            "name": $('#userFullName').val(),
            "email": $('#userEmail').val(),
            "phoneNumber": $('#userPhoneNumber').val(),
            "role": $('#userRole').val(),
            "preferredCampus": campus,
            "callSign": $('#userCallsign').val(),
            "training": [],
            "isDeactivated": false
        };

        $.ajax({
            type: 'POST',
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: api + '/add',
            data: JSON.stringify(user),
            success: function (data) {
                alert("Saved successfully");
                location.reload();
            },
            error: function (e) {
                alert('Error saving shift to DB');
            },
            dataType: "json",
            contentType: "application/json"
        });
    }

});