<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<div class="container mt-4">
    <!-- Post Details -->
    <div id="postDetails" class="card mb-4">
        <!-- Post content will be dynamically loaded here -->
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
    const postId = 1;  // Example post ID (You can pass this dynamically from the server)
    let currentPage = 0;  // To handle pagination for comments

    // Function to load post details
    function loadPostDetails() {
        $.ajax({
            url: `/api/posts/1`,
            type: 'GET',
            success: function(response) {
                if (response.status === 200) {
                    const post = response.data;  // Post data
                    let postHtml = `
                        <img src="` + post.imageUrl + `" class="card-img-top" alt="Post Image">
                        <div class="card-body">
                            <p class="card-text">`+ post.text + `</p>
                        </div>
                    `;
                    $('#postDetails').html(postHtml);  // Inject post details into the HTML
                }
            },
            error: function(err) {
                console.log('Error loading post details', err);
            }
        });
    }

    // Function to load comments with pagination
    function loadComments(page) {
        $.ajax({
            url: `/api/comments/post/1`,
            type: 'GET',
            data: {
                page: page,
                size: 5,
                sortBy: 'createdAt'
            },
            success: function(response) {
                if (response.status === 200) {
                    const comments = response.data.content; // Comments data
                    let commentsHtml = '';
                    comments.forEach(comment => {
                        commentsHtml += `
                            <div class="card mb-2">
                                <div class="card-body">
                                    <p><strong>${comment.username}</strong> <span class="text-muted">${comment.createdAt}</span></p>
                                    <p>${comment.text}</p>
                                </div>
                            </div>
                        `;
                    });
                    $('#commentsSection').html(commentsHtml);

                    // Pagination
                    let paginationHtml = '';
                    const totalPages = response.data.totalPages;
                    for (let i = 0; i < totalPages; i++) {
                        paginationHtml += `
                            <li class="page-item ${i === page ? 'active' : ''}">
                                <a class="page-link" href="#" onclick="loadComments(${i})">${i + 1}</a>
                            </li>
                        `;
                    }
                    $('#paginationLinks').html(paginationHtml);
                }
            },
            error: function(err) {
                console.log('Error loading comments', err);
            }
        });
    }

    // Submit a new comment
    $('#submitComment').click(function() {
        const commentText = $('#commentText').val();

        if (commentText.trim()) {
            $.ajax({
                url: '/api/comments',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify({
                    text: commentText,
                    postId: postId,
                    userId: 1  // For now, assuming the user ID is 1 (this can be dynamic)
                }),
                success: function(response) {
                    if (response.status === 201) {
                        $('#commentText').val('');  // Clear textarea
                        loadComments(currentPage);  // Reload comments after adding
                    }
                },
                error: function(err) {
                    console.log('Error submitting comment', err);
                }
            });
        }
    });

    // Initial load of post details and comments when the page is loaded
    $(document).ready(function() {
        loadPostDetails(postId);
        loadComments(currentPage);
    });
</script>
