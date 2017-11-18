$userJQ = jQuery.noConflict(false);

$userJQ(document).ready(function () {

    $('#removeButton').on('click', function (e) {
        e.preventDefault();
        doRemove();
    });
    
    $('#editButton').on('click', function (e) {
        e.preventDefault();
        doEdit();
    });

    $('#userModal')
        .bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            fields: {
                username: {
                    validators: {
                        notEmpty: {
                            message: 'Username is required and cannot be empty.'
                        }
                    }
                },
                studentNumber: {
                    validators: {
                        notEmpty: {
                            message: 'Student number is required and cannot be empty.'
                        },
                        integer: {
                            message: 'Not a valid student number.'
                        }
                    }
                },
                name: {
                    validators: {
                        notEmpty: {
                            message: 'Name is required and cannot be empty.'
                        }
                    }
                },
                email : {
                    validators: {
                        notEmpty: {
                            message: 'Alternate email is required and cannot be empty'
                        },
                        emailAddress: {
                            message: 'Not a valid email address'
                        }
                    }
                },
                phoneNumber: {
                    validators: {
                        notEmpty: {
                            message: 'Phone number is required and cannot be empty.'
                        },
                        phone: {
                            country: 'US',
                            message: 'Please enter a valid phone number.'
                        }
                    }
                },
                role: {
                    validators: {
                        notEmpty: {
                            message: 'Role is required'
                        }
                    }
                },
                preferredCampus: {
                    validators: {
                        notEmpty: {
                            message: 'Please select a campus.'
                        }
                    }
                },
                callSign: {
                    validators: {
                        notEmpty: {
                            message: 'Callsign is required and cannot be empty..'
                        },
                        regexp: {
                            regexp: /^[A-Z0-9]+$/,
                            message: 'The title can only consist of capital letters and numbers'
                        }
                    }
                }
            }
        })
        .on('change', '[name="campus"]', function () {
            $('#userModal').bootstrapValidator('revalidatefield', 'campus');
        })
        .on('success.form.bv', function (e) {
            e.preventDefault();

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var phone  = $('#userPhoneNumber').val().replace(/^\D+/g,"");

            var campus = '';
            if ($('#campusBurnaby').prop('checked', true)) {
                campus = 'BURNABY';
            } else if ($('#campusSurrey').prop('checked', true)) {
                campus = 'SURREY';
            } else {
                campus = 'VANCOUVER';
            }

            var user = {
                "username": $('#username').val().toLowerCase(),
                "studentNumber": $('#studentNumber').val(),
                "name": $('#userFullName').val(),
                "email": $('#userEmail').val(),
                "phoneNumber": phone,
                "role": $('#userRole').val(),
                "preferredCampus": campus,
                "callSign": $('#userCallsign').val(),
                "training": [],
                "isDeactivated": false
            };

            $.ajax({
                type: 'POST',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                url: api + '/add',
                data: JSON.stringify(user),
                success: function () {
                    alert("Saved successfully");
                    location.reload();
                },
                error: function () {
                    alert('Error saving shift to DB');
                },
                // dataType: "json",
                contentType: "application/json"
            });
        });

    function doRemove() {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        var username = $('.tab-content:visible').attr('id');

        $.ajax({
            type: 'POST',
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            url: api + '/remove',
            data: username,
            success: function () {
                alert("removed");
                location.reload();
            },
            error: function () {
                alert('Error removing from DB');
            },
            // dataType: "json",
            contentType: "application/json"
        });
    }
    
    function doEdit() {
        var username = $('.tab-content:visible').attr('id');
        var user = users[username];

        $('#userModal')
            .find('[id="myModalLabel1"]').html('<b>Edit User</b>').end()
            .find('[id="username"]').val(user['username']).prop('disabled', true).end()
            .find('[id="studentNumber"]').val(user['studentNumber']).end()
            .find('[id="userFullName"]').val(user['name']).end()
            .find('[id="userEmail"]').val(user['email']).end()
            .find('[id="userPhoneNumber"]').val(user['phoneNumber']).end()
            .find('[id="userRole"]').val(user['role']).end()
            .find('#' + user['preferredCampus']).prop('checked', true).end()
            .find('[id="userCallsign"]').val(user['callSign']).end()
        .modal('show');
    }
});