<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- Trigger Button -->
<button
        type="button"
        class="btn btn-outline-secondary rounded-pill"
        data-bs-toggle="modal"
        data-bs-target="#createPostModal"
>
    Bạn đang nghĩ gì thế?
</button>

<!-- Modal -->
<div
        class="modal fade"
        id="createPostModal"
        tabindex="-1"
        aria-labelledby="createPostModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createPostModalLabel">Tạo bài viết</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <!-- User Information -->
                <div class="d-flex align-items-center mb-3">
                    <img
                            src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"
                            alt="User Avatar"
                            class="rounded-circle"
                            style="width: 40px; height: 40px; object-fit: cover"
                    />
                    <div class="ms-3"><strong>Phạm Chiến</strong><br /></div>
                </div>
                <!-- Text input for post -->
                <textarea
                        id="postContent"
                        class="form-control"
                        rows="3"
                        placeholder="Chiến ơi, bạn đang nghĩ gì thế?"
                        aria-label="Post content"
                ></textarea>

                <!-- Image/video add section -->
                <div class="mt-3 text-center border p-3 rounded">
                    <!-- File upload input -->
                    <form id="postForm" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="fileInput" class="form-label">Upload File</label>
                            <input type="file" class="form-control" id="fileInput" onchange="handleFileUpload(event)">
                        </div>
                        <div id="filePreview" style="display:none;" class="mb-3">
                            <h5>Preview:</h5>
                            <img id="previewImage" style="display:none;" class="img-fluid" />
                            <video id="previewVideo" style="display:none;" class="img-fluid" controls></video>
                            <span id="fileName"></span>
                        </div>
                    </form>
                </div>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    Đóng
                </button>
                <button
                        type="button"
                        class="btn btn-primary"
                        onclick="submitPost()"
                >
                    Đăng
                </button>
            </div>
        </div>
    </div>
</div>
<script>
    function handleFileUpload(event) {
        const file = event.target.files[0];
        if (file) {
            const fileName = file.name;
            document.getElementById("fileName").textContent = fileName;

            // Display preview based on file type
            const filePreview = document.getElementById("filePreview");
            const previewImage = document.getElementById("previewImage");
            const previewVideo = document.getElementById("previewVideo");

            if (file.type.startsWith("image/")) {
                previewImage.src = URL.createObjectURL(file);
                previewImage.style.display = "block";
                previewVideo.style.display = "none";
            } else if (file.type.startsWith("video/")) {
                previewVideo.src = URL.createObjectURL(file);
                previewVideo.style.display = "block";
                previewImage.style.display = "none";
            }

            filePreview.style.display = "block";
        }
    }

    function submitPost() {
        const postContent = document.getElementById("postContent").value;
        const fileInput = document.getElementById("fileInput");

        // Example of handling post data and file upload (AJAX or form submission can be added)
        console.log("Post content:", postContent);
        console.log("Uploaded file:", fileInput.files[0]);

        // Clear modal content after submit
        document.getElementById("postContent").value = "";
        document.getElementById("fileInput").value = "";
        document.getElementById("fileName").textContent = "";
        document.getElementById("filePreview").style.display = "none";
    }
</script>