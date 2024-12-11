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

    <!-- Pagination controls -->
    <div id="pagination-controls" class="text-center">
        <!-- Pagination buttons will be inserted here -->
    </div>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
    let currentPage = 0; // Track the current page
    let totalPages = 1;  // Track the total number of pages

    // Function to load reports for a specific page
  function loadReports(page) {
      console.log(`Loading page: ` +page+ ``); // Log để kiểm tra

      $.ajax({
          url: `http://localhost:8888/api/mod/reports/all?page=` +page+ `&size=5`, // Đường dẫn API
          method: "GET",
          dataType: "json",
          success: function(response) {
              console.log("API Response:", response); // Xem cấu trúc dữ liệu trả về

              if (response.status === 200 && response.data) {
                  const data = response.data;

                  // Gán giá trị từ API
                  currentPage = data.currentPage;
                  totalPages = data.totalPages;

                  console.log(`Current Page: ` +currentPage+ `, Total Pages: ` +totalPages+ ``); // Log kiểm tra

                  // Tạo bảng báo cáo
                  let reportHtml = '';
                  data.reports.forEach(function(report) {
                      reportHtml += `
                          <tr>
                              <td>` +report.id+ `</td>
                              <td>` +report.extraInformation+ `</td>
                              <td>` +report.post.id+ `</td>
                              <td>` +report.post.text+ `</td>
                              <td>` +report.post.user.username+ `</td>
                              <td>` +report.reportReason.reason+ `</td>
                              <td>
                                  <a href="post-report/ ` +report.id+ `" class="btn btn-success btn-sm">View Post</a>
                              </td>
                          </tr>
                      `;
                  });

                  // Hiển thị báo cáo
                  $("#report-table").html(reportHtml);

                  // Cập nhật phân trang
                  updatePaginationControls();
              } else {
                  console.log("Error: Invalid response or status not 200");
              }
          },
          error: function(xhr, status, error) {
              console.error("AJAX Error:", error);
              console.error("XHR Response:", xhr.responseText);
          }
      });
  }


function updatePaginationControls() {
    let paginationHtml = '';


    for (let i = 0; i < totalPages; i++) {
        if (i === currentPage) {
            paginationHtml += `<button class="btn btn-secondary" disabled>` +i+ `</button>`;
        } else {
            paginationHtml += `<button class="btn btn-primary" onclick="loadReports(` +i+ `)">` +i + `</button>`;
        }
    }




    console.log("Pagination HTML:", paginationHtml); // Debugging
    $("#pagination-controls").html(paginationHtml);
}


   // Initial load on page load
   $(document).ready(function() {
       loadReports(currentPage); // Load first page by default
   });


</script>
