<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<style>
    body {
        background-color: #f4f7fb;
        font-family: "Arial", sans-serif;
    }
    .post-container {
        max-width: 600px;
        margin: 30px auto;
        background-color: white;
        border-radius: 15px;
        box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        transition: transform 0.3s ease-in-out;
    }
    .post-header {
        display: flex;
        align-items: center;
        padding: 15px;
    }
    .post-header img {
        width: 55px;
        height: 55px;
        border-radius: 50%;
        margin-right: 15px;
        object-fit: cover;
    }
    .post-header a {
        text-decoration: none;
    }
    .post-header a:hover {
        text-decoration: underline;
    }
    .post-body {
        padding: 15px;
        background-color: #fff;
    }
    .post-body p {
        margin-bottom: 15px;
        font-size: 16px;
        color: #444;
    }
    .post-img {
        width: 100%;
        height: 350px;
        object-fit: contain;
        border-radius: 15px;
        transition: transform 0.3s ease;
    }
    .post-img:hover {
        transform: scale(1.05);
    }
    .post-footer {
        padding: 15px;
        display: flex;
        justify-content: space-between;
        align-items: center;
        border: 1px solid #e1e1e1;
    }
    .post-footer button {
        background-color: #fff;
        color: #1877f2;
        font-weight: bold;
        border: 1px solid #1877f2;
        border-radius: 25px;
        padding: 8px 20px;
        cursor: pointer;
        transition: background-color 0.3s ease, color 0.3s ease;
        flex: 1; /* Make the buttons fill available space */
        margin: 0 5px;
    }
    .post-footer button:hover {
        background-color: #1877f2;
        color: #fff;
    }
    .post-footer button:active {
        transform: scale(0.95);
    }
    .reactions {
        margin-left: 15px;
        margin-right: 15px;
        display: flex;
        justify-content: space-between;
    }
</style>
<div class="posts-container">
    <!-- post -->
    <div class="post-container">
        <!-- Header -->
        <div class="post-header">
        <%--user-data--%>
        </div>

        <!-- Nội dung bài đăng -->
        <div class="post-body">
        <%--post-data--%>
        </div>

        <!-- Interaction buttons -->
        <div class="reactions">
            <div id="like-count">
<%--                <a href=""> 7,6K lượt thích</a>--%>
            </div>
            <div id="comment-count">
<%--                <a href=""> 517 bình luận</a>--%>
            </div>
        </div>

        <!-- Footer -->
        <div class="post-footer">
            <button id="likeBtn">Thích</button>
            <button data-bs-toggle="collapse" data-bs-target="#commentBox">
                Bình luận
            </button>
            <!-- Comment Box (ẩn/hiện bằng collapse) -->
        </div>
        <div class="collapse" id="commentBox">
            <div class="d-flex p-3 border-top">
                <input
                        type="text"
                        class="form-control"
                        placeholder="Viết bình luận công khai... "
                />
                <button class="btn rounded-circle">
                    <i class="bi bi-send"></i>
                </button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function fetchPost(postId){
        $.ajax({
            url: "http://localhost:8888/api/posts/" + postId,
            method: "GET",
            dataType: "json",
            success: function(response){
                console.log(response.data);
                renderPost(response.data);
                renderUserInfo(response.data.user);
                fetchLike(postId);
                fetchComment(postId);
            },
            error: function (){
                alert("Có lỗi xảy ra khi tải bài viết!");
            }
        })
    }

    function renderPost(data) {
        const post = $('.post-body');
        const postHTML = `
        <p>
                ` + data.text + `
            </p>
            <!-- Hình ảnh bài viết -->
            <img
                    src="` + data.photo + `"
                    alt="Post Image"
                    class="post-img"
            />
        `;
        post.append(postHTML);
    }

    function renderUserInfo(user) {
        const post = $('.post-header');
        const postHTML = `
         <img
                    src="`+ user.avatar + `"
                    alt="User Avatar"
                    class="avatar-img"
            />
            <div>
                <div class="mb-0">
                    <a href="">`+ user.username + `</a>
                </div>
                <small class="text-muted">2g trước</small>
            </div>
        `;
        post.append(postHTML);
    }

    function fetchLike(postId) {
        $.ajax({
            url: "http://localhost:8888/api/likes/post/" + postId + "/count",
            method: "GET",
            dataType: "json",
            success: function(response){
                console.log(response.data);
                renderLike(response.data);
            },
            error: function (){
                alert("Có lỗi xảy ra khi tải danh sách người thích!");
            }
        })
    }

    function renderLike(data) {
        const reactions = $('#like-count');
        const reactionsHTML = `
        <a href=""> `+ data +` lượt thích</a>
        `;
        reactions.append(reactionsHTML);
    }

    function fetchComment(postId) {
        $.ajax({
            url: "http://localhost:8888/api/comments/post/" + postId + "/count",
            method: "GET",
            dataType: "json",
            success: function(response){
                console.log(response.data);
                renderComment(response.data);
            },
            error: function (){
                alert("Có lỗi xảy ra khi tải danh sách người bình luận!");
            }
        })
    }

    function renderComment(data) {
        const reactions = $('#comment-count');
        const reactionsHTML = `
        <a href=""> `+ data +` bình luận</a>
        `;
        reactions.append(reactionsHTML);
    }

    $(document).ready(function() {
        const postId = 1;  // Giả sử postId là 1
        fetchPost(postId);
    });
</script>