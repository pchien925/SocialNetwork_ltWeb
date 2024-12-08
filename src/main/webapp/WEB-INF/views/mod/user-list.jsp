<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-4">
        <h1 class="text-center text-primary">User List</h1>
        <div class="table-responsive">
            <table id="userTable" class="table table-bordered table-hover table-striped">
                <thead class="table-primary text-uppercase">
                    <tr>
                        <th>#</th>
                        <th>Full Name</th>
                        <th>Date of Birth</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Username</th>
                        <th>Status</th>
                        <th>Actions</th> <!-- New Action Column -->
                    </tr>
                </thead>

                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <!-- jQuery and Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script  type="text/javascript">
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8888/api/mod/list-users", // Full URL to your API
        method: "GET",
        dataType: "json",
        success: function(response) {
            console.log("API Response: ", response);
            if (response.status === 200 && response.data) {
                let users = response.data;
                console.log("User Data: ", users);

                let userlist = '';
                users.forEach(function(user) {
                    userlist += `
                <tr>
                    <td>` + user.id + `</td>
                    <td>` + user.firstName + ` ` + user.lastName + `</td>
                    <td>` + user.dateOfBirth + `</td>
                    <td>` + user.gender + `</td>
                    <td>` + user.phone + `</td>
                    <td>` + user.email + `</td>
                    <td>` + user.username + `</td>
                    <td><span class="status ` + (user.isActive ? 'active">Actived' : 'inactive">Inactive') + `</span></td>
                    <td>
                        <button class="btn btn-danger btn-sm delete-user" data-id="` + user.id + `">Delete</button>

                    </td>
                </tr>
                    `;
                });

                $("#userTable tbody").html(userlist);

                // Add click event for delete buttons
                $(".delete-user").click(function() {
                    const userId = $(this).data("id");
                    if (confirm("Are you sure you want to delete this user?")) {
                        $.ajax({
                            url: "http://localhost:8888/api/mod/delete-user/" + userId,
                            method: "DELETE",
                            success: function(deleteResponse) {
                                if (deleteResponse === "User deleted successfully.") {
                                    alert("User deleted successfully!");
                                    location.reload(); // Refresh the table
                                } else {
                                    alert("Failed to delete user.");
                                }
                            },
                            error: function(xhr, status, error) {
                                console.error("AJAX Error: " + error);
                                console.error("XHR Response: ", xhr.responseText);
                                alert("An error occurred while deleting the user.");
                            }
                        });
                    }
                });
            } else {
                console.log("Error: Data not found or status not 200");
            }
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error: " + error);
            console.error("XHR Response: ", xhr.responseText);
        }
    });
});

</script>
</body>
</html>
