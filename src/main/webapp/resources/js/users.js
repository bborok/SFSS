$(document).ready(function () {

    var tab = '';

    $('#licenseExpire').datetimepicker({
        format: 'YYYY/MM/DD',
        useCurrent: true,
    });

    $('#certExpire').datetimepicker({
        format: 'YYYY/MM/DD',
        useCurrent: true,
    });

    $('#languages').multiselect({
        includeSelectAllOption: true,
        maxHeight: 250,
        dropUp: true
    });
    $('#languages').multiselect('select', 'English');

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
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: 'Only alphanumeric characters are allowed.'
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
                altPhoneNumber: {
                    validators: {
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
                },
                licenseClass: {
                    validators: {
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: 'Not a valid license class'
                        }
                    }
                },
                licenseExpire: {
                    validators: {
                        date: {
                            format: 'YYYY/MM/DD',
                            message: 'Not a valid date'
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

            var phone  = $('#userPhoneNumber').val().replace(/\D+/g,"");
            var altPhone  = $('#userAltPhoneNumber').val().replace(/\D+/g,"");

            var campus = $('input[name="campus"]:checked').attr('id');

            var dateFormat = 'YYYY-MM-DD';
            var expireDate = $('#licenseExpire').data("DateTimePicker").date().format(dateFormat);

            var languagesObj = $('#languages option:selected').map(function(a, item){return item.value;});
            var languages = languagesObj.toArray();

            var user = {
                "username": $('#username').val().toLowerCase(),
                "studentNumber": $('#studentNumber').val(),
                "name": $('#userFullName').val(),
                "email": $('#userEmail').val(),
                "phoneNumber": phone,
                "altPhoneNumber": altPhone,
                "role": $('#userRole').val(),
                "preferredCampus": campus,
                "callSign": $('#userCallsign').val(),
                "driversLicenseLevel": $('#licenseClass').val(),
                "driversLicenseExpirationDate": expireDate,
                "languages": languages,
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

    $('#certForm')
        .bootstrapValidator({
            message: 'This value is not valid',
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            fields: {
                certName: {
                    validators: {
                        notEmpty: {
                            message: 'Certificate name is required'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z\s]+$/,
                            message: 'Not a valid certificate name'
                        }
                    }
                },
                certLevel: {
                    validators: {
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: 'Certificate level must be numeric'
                        }
                    }
                },
                certID: {
                    validators: {
                        notEmpty: {
                            message: 'Certificate ID is required'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9]+$/,
                            message: 'Not a valid id'
                        }
                    }
                },
                certExpire: {
                    validators: {
                        notEmpty: {
                            message: 'Expiration date is required'
                        },
                        date: {
                            format: 'YYYY/MM/DD',
                            message: 'Not a valid date'
                        }
                    }
                }
            }
        })
        .on('success.form.bv', function (e) {
            e.preventDefault();

            var token = $("meta[name='_csrf']").attr("content");
            var header = $("meta[name='_csrf_header']").attr("content");

            var dateFormat = 'YYYY-MM-DD';
            var expireDate = $('#certExpire').data("DateTimePicker").date().format(dateFormat);

            var certificate = {
                "name": $('#certName').val(),
                "level": $('#certLevel').val(),
                "id": $('#certID').val(),
                "expirationDate": expireDate
            };

            $.ajax({
                type: 'POST',
                beforeSend: function (xhr) {
                    xhr.setRequestHeader(header, token);
                },
                url: api + '/addCertificate',
                data: JSON.stringify(certificate),
                success: function () {
                    alert("Saved successfully");
                    location.reload();
                },
                error: function () {
                    alert('Error saving certificate to DB');
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

        var altNum = user['altPhoneNumber'];
        if (altNum === '0') {
            altNum = '';
        }

        const [year, month, day] = user['licenseExpire'].split('-');
        $('#licenseExpire').data("DateTimePicker").date(new Date(year, month-1, day));


        $('#userModal')
            .find('[id="myModalLabel1"]').html('<b>Edit User</b>').end()
            .find('[id="username"]').val(user['username']).prop('disabled', true).end()
            .find('[id="studentNumber"]').val(user['studentNumber']).end()
            .find('[id="userFullName"]').val(user['name']).end()
            .find('[id="userEmail"]').val(user['email']).end()
            .find('[id="userPhoneNumber"]').val(user['phoneNumber']).end()
            .find('[id="userAltPhoneNumber"]').val(altNum).end()
            .find('[id="userRole"]').val(user['role']).end()
            .find('#' + user['preferredCampus']).prop('checked', true).end()
            .find('[id="userCallsign"]').val(user['callSign']).end()
            .find('[id="licenseClass"]').val(user['licenseClass']).end()
        .modal('show');
    }
});