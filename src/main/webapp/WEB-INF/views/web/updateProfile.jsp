<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<div class="d-flex bg-light">
    <div class="container my-4" style="max-width: 600px">
        <h2 class="text-center">Thông tin người dùng</h2>

        <!-- Form to update user information -->
        <form id="updateUserForm" enctype="multipart/form-data">
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required>
            </div>

            <div class="mb-3">
                <label for="dateOfBirth" class="form-label">Date of Birth</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
            </div>

            <div class="mb-3">
                <label for="gender" class="form-label">Gender</label>
                <select class="form-select" id="gender" name="gender" required>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="phone" class="form-label">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="avatar" class="form-label">Avatar</label>
                <input type="file" class="form-control" id="avatar" name="avatar"
                       accept="image/*,video/*"
                       onchange="handleFileUpload(event)">
                <div id="avatarPreview" class="mt-2 mb-2">
                    <img id="avatarImage" src="" alt="Current Avatar" class="img-thumbnail rounded-circle me-3"
                         style="max-width: 250px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                </div>

            </div>

            <div class="mb-3">
                <label for="description" class="form-label">Description</label>
                <textarea class="form-control" id="description" name="description" rows="3"></textarea>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </div>
        </form>

        <!-- Success/Error Message -->
        <div id="message" class="mt-3" style="display: none;">
            <div class="alert alert-success" role="alert" id="successMessage"></div>
            <div class="alert alert-danger" role="alert" id="errorMessage"></div>
        </div>
    </div>
</div>

<script>
    let imgUrl = '';
    const handleFileUpload = async (event) => {
        const file = event.target.files[0];
        console.log('File selected:', file);
        const formData = new FormData();
        formData.append('file', file);

        try {
            const res = await $.ajax({
                url: '/api/medias/upload',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function (response) {
                    alert('File uploaded successfully!');
                    imgUrl = response.data.url;
                    $('#avatarImage').attr('src', response.data.url).show();
                },
                error: function (error) {
                    console.error('Error uploading file:', error);
                }
            });
        }
        catch (error) {
            console.error('Error reading file:', error);
        }
    }


    const fetchUser = async () => {
        try {
            const res = await $.ajax({
                url: '/api/profiles/my-profile',
                type: 'GET',
                success: function (response) {
                    console.log(response.data);
                    $('#firstName').val(response.data.firstName);
                    $('#lastName').val(response.data.lastName);
                    $('#dateOfBirth').val(response.data.dateOfBirth);
                    $('#gender').val(response.data.gender);
                    $('#phone').val(response.data.phone);
                    $('#email').val(response.data.email);
                    $('#description').val(response.data.description);
                    imgUrl = response.data.avatar;
                    if (response.data.avatar) {
                        $('#avatarImage').attr('src', response.data.avatar).show();
                    }
                },
                error: function (error) {
                    console.error('Error fetching user data:', error);
                }
            });
        } catch (error) {
            console.error('Fetch user failed:', error);
        }
    }

    const updateUserProfile = async (formData) => {
        try {
            const userProfileData = {
                firstName: $('#firstName').val(),
                lastName: $('#lastName').val(),
                dateOfBirth: $('#dateOfBirth').val(),
                gender: $('#gender').val(),
                phone: $('#phone').val(),
                email: $('#email').val(),
                avatar: imgUrl,
                description: $('#description').val()
            };
            console.log('formData:', formData);
            const res = await $.ajax({
                url: '/api/profiles/my-profile',
                type: 'PUT',
                data: JSON.stringify(userProfileData),
                contentType: 'application/json',
                success: function (data) {
                    console.log("data: ", data);
                    $('#successMessage').text('Profile updated successfully!');
                    $('#message').show();
                },
                error: function (error) {
                    $('#errorMessage').text('Error updating profile. Please try again.');
                    $('#message').show();
                }
            });
        } catch (error) {
            console.error('Update user failed:', error);
        }
    }

    $(document).ready(function() {
        fetchUser();  // Fetch user data on page load

        $('#updateUserForm').on('submit', function (e) {
            e.preventDefault();
            const formData = new FormData(this);  // Create FormData object with form data
            updateUserProfile(formData);  // Send the data via AJAX
        });
    });
</script>