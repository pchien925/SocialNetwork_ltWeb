<div class="container mt-4">
    <!-- Nút mở modal -->
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#createPostModal">
        Create New Post
    </button>
</div>

<!-- Modal (đã có từ trước) -->
<div class="modal fade" id="createPostModal" tabindex="-1" aria-labelledby="createPostModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createPostModalLabel">Create New Post</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Form for Post Creation -->
                <form id="createPostForm">
                    <div class="mb-3">
                        <label for="postText" class="form-label">Post Text</label>
                        <textarea class="form-control" id="postText" rows="3" required></textarea>
                    </div>
                    <div class="mb-3">
                        <label for="postPhoto" class="form-label">Photo URL</label>
                        <input type="text" class="form-control" id="postPhoto" />
                    </div>
                </form>
                <button type="button" class="btn btn-primary" onclick="submitPost()">Submit</button>
            </div>
        </div>
    </div>
</div>
<script>
    function submitPost() {
        const postText = $('#postText').val();
        const postPhoto = $('#postPhoto').val();
        const userId = '<%= session.getAttribute("userId") %>';// Lấy từ session
        console.log("userId: " + userId);

        // Kiểm tra userId trước khi gửi request
        if (!userId) {
            alert('User ID is missing. Please log in again.');
            return;
        }

        const postData = {
            text: postText,
            photo: postPhoto,
            userId: parseInt(userId)  // Gửi userId lên server
        };

        $.ajax({
            url: '/api/post/create',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(postData),
            success: function(response) {
                alert('Post created successfully!');
                $('#createPostModal').modal('hide');
                location.reload();
            },
            error: function(xhr) {
                alert('Error: ' + xhr.responseText);
            }
        });
    }


</script>