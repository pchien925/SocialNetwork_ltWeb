<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm fixed-top"></nav>
<div class="d-flex bg-light">
    <div class="container my-4" style="max-width: 300px"></div>
    <div class="container my-4" style="max-width: 600px">
        <!-- Add post -->
        <div class="rounded-3 my-4 p-3 bg-white shadow-sm border">
            <div class="d-flex align-items-center">
                <img
                        src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"
                        alt="User Avatar"
                        class="rounded-circle me-3"
                        style="width: 45px; height: 45px; object-fit: cover"
                />
                <button
                        type="button"
                        class="btn btn-outline-secondary rounded-pill flex-grow-1 text-start"
                        data-bs-toggle="modal"
                        data-bs-target="#createPostModal"
                >
                    Bạn đang nghĩ gì thế?
                </button>
            </div>
        </div>
        <div id = "list-post">
        <!-- post -->
       <div class="card shadow-sm rounded-3 my-4">
<%--                        <!-- Header -->--%>
<%--                        <div--%>
<%--                                class="card-header d-flex align-items-center"--%>
<%--                                style="border: none"--%>
<%--                        >--%>
<%--                            <img--%>
<%--                                    src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"--%>
<%--                                    alt="User Avatar"--%>
<%--                                    class="rounded-circle me-3"--%>
<%--                                    style="width: 45px; height: 45px; object-fit: cover"--%>
<%--                            />--%>
<%--                            <div>--%>
<%--                                <div class="mb-0">--%>
<%--                                    <a href="" class="text-decoration-none">Tên người dùng</a>--%>
<%--                                </div>--%>
<%--                                <small class="text-muted">2 giờ trước</small>--%>
<%--                            </div>--%>
<%--                            <!-- Nút ba chấm -->--%>
<%--                            <div class="dropdown ms-auto">--%>
<%--                                <button--%>
<%--                            class="btn dropdown-toggle"--%>
<%--                            type="button"--%>
<%--                            id="dropdownMenuButton"--%>
<%--                            data-bs-toggle="dropdown"--%>
<%--                    >--%>
<%--                        report--%>
<%--                    </button>--%>
<%--                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">--%>
<%--                        <li>--%>
<%--                            <a class="dropdown-item" href="#">Giả mạo ảnh người khác</a>--%>
<%--                        </li>--%>
<%--                        <li><a class="dropdown-item" href="#">Hình ảnh nhạy cảm</a></li>--%>
<%--                        <li>--%>
<%--                            <a class="dropdown-item" href="#">Nội dung không lành mạnh</a>--%>
<%--                        </li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <!-- Nội dung bài đăng -->--%>
<%--            <div class="card-body">--%>
<%--                <p class="text-muted mb-3">--%>
<%--                    Đây là nội dung của bài đăng. Bạn có thể chia sẻ cảm xúc, hình ảnh--%>
<%--                    hoặc video tại đây. 🌟--%>
<%--                </p>--%>
<%--                <!-- Hình ảnh bài viết -->--%>
<%--                <div class="d-flex justify-content-center">--%>
<%--                    <img--%>
<%--                            src="https://hoanghamobile.com/tin-tuc/wp-content/uploads/2024/07/anh-thien-nhien-dep-3d-22.jpg"--%>
<%--                            alt="Post Image"--%>
<%--                            class="img-fluid rounded-3"--%>
<%--                            style="max-height: 350px; object-fit: contain"--%>
<%--                    />--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <!-- Interaction buttons -->--%>
<%--            <div--%>
<%--                    class="d-flex justify-content-between align-items-center py-2 px-3"--%>
<%--            >--%>
<%--                <div>--%>
<%--                    <a--%>
<%--                            data-bs-toggle="modal"--%>
<%--                            data-bs-target="#usersLikeModal"--%>
<%--                            class="text-decoration-none"--%>
<%--                    >--%>
<%--                        7,6K <i class="bi bi-hand-thumbs-up-fill"></i>--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--                <div>--%>
<%--                    <a class="text-decoration-none">--%>
<%--                        517 <i class="bi bi-chat-fill"></i>--%>
<%--                    </a>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <!-- Footer -->--%>
<%--            <div--%>
<%--                    class="card-footer d-flex justify-content-between align-items-center bg-white border-top border-bottom"--%>
<%--                    style="border: none"--%>
<%--            >--%>
<%--                <button--%>
<%--                        id="likeBtn"--%>
<%--                        class="btn btn-outline-primary flex-fill me-2 rounded-pill"--%>
<%--                >--%>
<%--                    <i class="bi bi-hand-thumbs-up-fill"></i> Thích--%>
<%--                </button>--%>
<%--                <button--%>
<%--                        class="btn btn-outline-primary flex-fill ms-2 rounded-pill"--%>
<%--                        data-bs-toggle="collapse"--%>
<%--                        data-bs-target="#commentBox1"--%>
<%--                >--%>
<%--                    <i class="bi bi-chat-fill"></i> Bình luận--%>
<%--                </button>--%>
<%--            </div>--%>
<%--            <div class="collapse" id="commentBox1">--%>
<%--                <div class="d-flex p-3" style="border-top: none">--%>
<%--                    <input--%>
<%--                            type="text"--%>
<%--                            class="form-control me-2"--%>
<%--                            placeholder="Viết bình luận công khai... "--%>
<%--                    />--%>
<%--                    <button class="btn btn-primary rounded-circle">--%>
<%--                        <i class="bi bi-send"></i>--%>
<%--                    </button>--%>
<%--                </div>--%>
<%--            </div>--%>
        </div>
        </div>
        <!-- Thanh phân trang -->
        <ul class="pagination justify-content-center" id="pagination">
<%--            <li class="page-item">--%>
<%--                <a class="page-link" href="#">Previous</a>--%>
<%--            </li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">1</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">2</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">3</a></li>--%>
<%--            <li class="page-item"><a class="page-link" href="#">Next</a></li>--%>
        </ul>
    </div>
    <div class="container my-4" style="max-width: 300px"></div>
</div>
<script type="text/javascript">
    function fetchPost(page = 1){
        $.ajax({
            url: "http://localhost:8888/api/posts/all?page=" + page + "&size=1",
            method: "GET",
            dataType: "json",
            success: function(response){
                console.log(response.data);
                renderListPosts(response.data.content);
                updatePagination(response.data, page);
            },
            error: function (){
                alert("Có lỗi xảy ra khi tải bài viết!");
            }
        })
    }

    function renderListPosts(posts) {
        const postContainer = $('#list-post');
        postContainer.html(``);

        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.classList.add('card', 'shadow-sm', 'rounded-3', 'my-4');
            postElement.innerHTML = `
                        <!-- Header -->
                        <div
                                class="card-header d-flex align-items-center"
                                style="border: none"
                        >
                            <img
                                    src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"
                                    alt="User Avatar"
                                    class="rounded-circle me-3"
                                    style="width: 45px; height: 45px; object-fit: cover"
                            />
                            <div>
                                <div class="mb-0">
                                    <a href="" class="text-decoration-none">Tên người dùng</a>
                                </div>
                                <small class="text-muted">2 giờ trước</small>
                            </div>
                            <!-- Nút ba chấm -->
                            <div class="dropdown ms-auto">
                                <button
                            class="btn dropdown-toggle"
                            type="button"
                            id="dropdownMenuButton"
                            data-bs-toggle="dropdown"
                    >
                        report
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li>
                            <a class="dropdown-item" href="#">Giả mạo ảnh người khác</a>
                        </li>
                        <li><a class="dropdown-item" href="#">Hình ảnh nhạy cảm</a></li>
                        <li>
                            <a class="dropdown-item" href="#">Nội dung không lành mạnh</a>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- Nội dung bài đăng -->
            <div class="card-body">
                <p class="text-muted mb-3">
                    `+ post.text + `
                </p>
                <!-- Hình ảnh bài viết -->
                <div class="d-flex justify-content-center">
                    <img
                            src="`+ post.photo + `"
                            alt="Post Image"
                            class="img-fluid rounded-3"
                            style="max-height: 350px; object-fit: contain"
                    />
                </div>
            </div>

            <!-- Interaction buttons -->
            <div
                    class="d-flex justify-content-between align-items-center py-2 px-3"
            >
                <div>
                    <a
                            data-bs-toggle="modal"
                            data-bs-target="#usersLikeModal"
                            class="text-decoration-none"
                    >
                        7,6K <i class="bi bi-hand-thumbs-up-fill"></i>
                    </a>
                </div>
                <div>
                    <a class="text-decoration-none">
                        517 <i class="bi bi-chat-fill"></i>
                    </a>
                </div>
            </div>

            <!-- Footer -->
            <div
                    class="card-footer d-flex justify-content-between align-items-center bg-white border-top border-bottom"
                    style="border: none"
            >
                <button
                        id="likeBtn"
                        class="btn btn-outline-primary flex-fill me-2 rounded-pill"
                >
                    <i class="bi bi-hand-thumbs-up-fill"></i> Thích
                </button>
                <button
                        class="btn btn-outline-primary flex-fill ms-2 rounded-pill"
                        data-bs-toggle="collapse"
                        data-bs-target="#commentBox1"
                >
                    <i class="bi bi-chat-fill"></i> Bình luận
                </button>
            </div>
            <div class="collapse" id="commentBox1">
                <div class="d-flex p-3" style="border-top: none">
                    <input
                            type="text"
                            class="form-control me-2"
                            placeholder="Viết bình luận công khai... "
                    />
                    <button class="btn btn-primary rounded-circle">
                        <i class="bi bi-send"></i>
                    </button>
                </div>
            </div>
            `;
            postContainer.append(postElement);
            }
        )
    }

    function updatePagination(data, currentPage){
        const pagination = $('#pagination');
        pagination.html(``);
        for (let i = 1; i <= data.totalPages; i++) {
            const pageItem = document.createElement('li');
            pageItem.classList.add('page-item');

            if (i === currentPage) {
                pageItem.classList.add('active');
            }
            const pageLink = document.createElement('a');
            pageLink.classList.add('page-link');
            pageLink.href = '#';
            pageLink.innerText = i;

            pageLink.addEventListener('click', function(event) {
                event.preventDefault();
                fetchPost(i);  // Gọi lại API với trang mới
            });
            pageItem.append(pageLink);
            pagination.append(pageItem);
        }

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