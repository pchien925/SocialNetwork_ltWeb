<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container mt-4">
    <!-- Post Details -->
    <h5 class="card-title" id="postTitle"></h5>
                <p class="card-text">
                    <small class="text-muted">
                        By <span id="postAuthor"></span> | <span>Publication Date : </span> <span id="postDate"></span>
                    </small>
                </p>
    <div id="postDetails" class="card mb-4">
        <div class="card-body">
            <!-- Add post title, author, and date -->

            <!-- Post content will be dynamically loaded here -->
            <div id="postContent">Post content goes here...</div>
        </div>
    </div>

    <!-- Comments Section -->
    <div>
        <h4>Comments</h4>
        <div id="commentsSection">
            <!-- Comments will be loaded here dynamically -->
        </div>

        <!-- Pagination for Comments -->
        <nav>
            <ul class="pagination" id="paginationLinks">
                <!-- Pagination links will be dynamically loaded here -->
            </ul>
        </nav>

        <!-- Add Comment Form -->
        <div class="mb-4">
            <textarea class="form-control" id="commentText" rows="3" placeholder="Write a comment..."></textarea>
            <button id="submitComment" class="btn btn-primary mt-2">Post Comment</button>
        </div>
    </div>
</div>

<script>
           const reportId = /*[[${reportId}]]*/ '';

           // Function to load the post details
           function loadPostDetails() {
               $.ajax({
                   url: `/api/mod/reports/${reportId}`,
                   type: 'GET',
                   success: function(response) {
                       if (response.status === 200) {
                        const report = response.data;
                    let postHtml = `
                        <img src="` + report.post.photo + `" class="card-img-top" alt="Post Image">
                        <div class="card-body">
                            <p class="card-text">`+ report.post.text + `</p>
                        </div>
                    `;

                    let postName =
                    `
                        <span> ` +report.post.user.username+ ` </span>
                    `

                    let postDate =
                                        `
                                            <span> ` +report.post.createdAt+ ` </span>
                                        `

                    $('#postDetails').html(postHtml);  // Inject post details into the HTML
          $('#postAuthor').html(postName);
          $('#postDate').html(postDate);
                }
            },
            error: function(err) {
                console.log('Error loading post details', err);
            }
        });
    }

    // Call the function after the page is loaded
    $(document).ready(function() {
        loadPostDetails();
    });
</script>

