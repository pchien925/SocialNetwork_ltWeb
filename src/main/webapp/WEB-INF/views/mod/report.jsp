<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include Bootstrap CSS -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

<h1 class="text-center my-4">Reports Data</h1>

<!-- Table to display reports -->
<div class="container">
    <table class="table table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th scope="col">Report ID</th>
                <th scope="col">Extra Information</th>
                <th scope="col">Post ID</th>
                <th scope="col">Post Text</th>
                <th scope="col">Post User Info</th>
                <th scope="col">Report Reason</th>
                <th scope="col">Actions</th>
            </tr>
        </thead>
        <tbody id="report-table">
            <!-- Table rows will be inserted here via JavaScript -->
        </tbody>
    </table>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $.ajax({
            url: "http://localhost:8888/api/mod/reports/all", // Full URL to your API
            method: "GET",
            dataType: "json", // Ensure the response is expected as JSON
            success: function(response) {
                console.log("API Response: ", response);
                if (response.status === 200 && response.data) {
                    let reports = response.data; // Assuming `response.data` is an array
                    console.log("Report Data: ", reports);

                    // Clear the container first before injecting new data
                    let reportHtml = '';
                    reports.forEach(function(report) {
                        reportHtml += `
                            <tr>
                                <td>` + report.id + `</td>
                                <td>` + report.extraInformation + `</td>
                                <td>` + report.post.id + `</td>
                                <td>` + report.post.text + `</td>
                                <td>` + report.post.user.username + `</td>
                                <td>` + report.reportReason.reason + `</td>
                                <td>
                                    <a href="post-report/` + report.id + `" class="btn btn-success btn-sm">View Post</a>
                                </td>
                            </tr>
                        `;
                    });

                    $("#report-table").html(reportHtml); // Inject data into the page
                } else {
                    console.log("Error: Data not found or status not 200");
                }
            },
            error: function(xhr, status, error) {
                console.error("AJAX Error: " + error);
                console.error("XHR Response: ", xhr.responseText); // Log the full response for debugging
            }
        });
    });
</script>
