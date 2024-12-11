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

            <!-- Delete Post Button -->
            <button id="deletePostBtn" class="btn btn-danger mt-2">Delete Post</button>
        </div>
    </div>
    <button id="deletePostBtn" class="btn btn-danger mt-2">Delete Post</button>
    <button id="keepPost" class="btn btn-success">Keep Post</button>
<script>
           const reportId = /*[[${reportId}]]*/ ''; // Report ID is passed to this page

           // Function to load the post details
           function loadPostDetails() {
               $.ajax({
                   url: `/api/mod/reports/${reportId}`,
                   type: 'GET',
                   success: function(response) {
                       if (response.status === 200) {
                           const report = response.data;
                           const postId = report.post.id;  // Get the post ID from the report
                           const reportId1 = report.id;
                           let postHtml = `
                               <img src="` + report.post.photo + `" class="card-img-top" alt="Post Image">
                               <div class="card-body">
                                   <p class="card-text">`+ report.post.text + `</p>
                               </div>
                           `;

                           let postName = `
                               <span> ` + report.post.user.username + ` </span>
                           `;

                           let postDate = `
                               <span> ` + report.post.createdAt + ` </span>
                           `;

                           $('#postDetails').html(postHtml);  // Inject post details into the HTML
                           $('#postAuthor').html(postName);
                           $('#postDate').html(postDate);

                           // Handle delete post after loading details
                           $('#deletePostBtn').click(function() {
                               if (confirm('Are you sure you want to delete this post?')) {
                                   deletePost(postId, reportId1);
                               }
                           });
                       }
                   },
                   error: function(err) {
                       console.log('Error loading post details', err);
                   }
               });
           }

           // Function to handle post deletion
          function deletePost(postId, reportId1) {
              const data = {
                  reportId: reportId1,
                  postId: postId
              };

              console.log('Data being sent to the server:', data);  // Add logging to verify data

              $.ajax({
                  url: '/api/mod/reports/delete',
                  type: 'POST',
                  contentType: 'application/json',
                  data: JSON.stringify(data),
                  success: function(response) {
                      console.log(response);  // Add logging for server response
                      if (response.status === 200) {
                          alert('Post deleted successfully');
                          window.location.href = '/mod/report'; // Redirect after delete
                      } else {
                          alert('Error deleting post');
                      }
                  },
                  error: function(err) {
                      console.log('Error deleting post', err);
                      alert('Error deleting post');
                  }
              });
          }



          function keepPost() {
        $.ajax({
            url: `/api/mod/reports/keep?reportId=${reportId}`,
            type: 'GET',
            success: function(response) {
                alert('Post kept successfully!');
                location.reload();
            },
            error: function(err) {
                console.log('Error keeping post', err);
                alert('Failed to keep post. Please try again.');
            }
        });
    }

           // Call the function after the page is loaded
           $(document).ready(function() {
               loadPostDetails();
           });
           $('#keepPost').click(function() {
            keepPost();
        });
</script>
