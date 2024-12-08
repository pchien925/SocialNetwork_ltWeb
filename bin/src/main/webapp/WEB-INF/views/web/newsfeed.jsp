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
        <!-- list post -->
        <div id="list-post">
            <!-- post -->
        </div>
    </div>
    <div class="container my-4" style="max-width: 300px"></div>
</div>
<!-- Add Post Modal -->
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
                        class="form-control"
                        rows="3"
                        placeholder="Chiến ơi, bạn đang nghĩ gì thế?"
                        aria-label="Post content"
                ></textarea>

                <!-- Image/video add section -->
                <div class="mt-3 text-center border p-3 rounded">
                    <!-- File upload input -->
                    <input
                            type="file"
                            class="form-control"
                            id="fileInput"
                            accept="image/*,video/*"
                            style="display: none"
                            onchange="handleFileUpload(event)"
                    />
                    <label for="fileInput" class="btn btn-light">
                        <i class="bi bi-plus-circle"></i> Thêm ảnh/video
                    </label>
                    <!-- Display file name (if any) -->
                    <div id="fileName" class="mt-2"></div>
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
                <button type="button" class="btn btn-primary">Đăng</button>
            </div>
        </div>
    </div>
</div>
<!-- Users Like Modal -->
<div
        class="modal fade"
        id="usersLikeModal"
        tabindex="-1"
        aria-labelledby="userModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-scrollable h-75">
        <div class="modal-content">
            <div class="modal-header">
                <!-- Reactions section -->
                <div class="d-flex justify-content-between align-items-center">
                    <div class="text-primary">
                        <i class="bi bi-hand-thumbs-up-fill"></i>
                    </div>
                </div>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <!-- User list replaced with divs -->
                <div class="list-users-like">
<%--                    <div--%>
<%--                            class="d-flex justify-content-between align-items-center my-2"--%>
<%--                    >--%>
<%--                        <div class="d-flex align-items-center">--%>
<%--                            <img--%>
<%--                                    src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"--%>
<%--                                    alt="User Avatar"--%>
<%--                                    class="rounded-circle me-3"--%>
<%--                                    style="width: 35px; height: 35px; object-fit: cover"--%>
<%--                            />--%>
<%--                            <a href="" class="text-decoration-none text-dark"--%>
<%--                            >Tên người dùng</a--%>
<%--                            >--%>
<%--                        </div>--%>
<%--                        <button class="btn btn-outline-primary btn-sm">--%>
<%--                            <i class="bi bi-person-plus"></i> Thêm bạn bè--%>
<%--                        </button>--%>
<%--                    </div>--%>
                    <!-- Add more users as needed -->
                </div>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <button id="loadMoreLikesBtn" class="btn btn-link">Load More</button>
            </div>
        </div>
    </div>
</div>
<!-- Users Comment Modal -->
<div
        class="modal fade"
        id="usersCommentModal"
        tabindex="-1"
        aria-labelledby="commentModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog modal-dialog-scrollable modal-lg">
        <div class="modal-content">
            <div class="modal-header position-sticky top-0">
                <h5 class="modal-title" id="commentModalLabel">Bình luận</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <div id="commentList"></div>
                <div class="d-flex mb-2">
                    <button id="loadMoreCommentsBtn" class="btn btn-link">
                        Xem thêm bình luận
                    </button>
                </div>
            </div>
            <div class="modal-footer w-100 position-sticky bottom-0">
                <div class="d-flex w-100 align-items-center">
                    <img
                            src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"
                            alt="User Avatar"
                            class="rounded-circle me-3"
                            style="width: 45px; height: 45px; object-fit: cover"
                    />
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
        </div>
    </div>
</div>
<script type="text/javascript">
    let likePage = 1;
    let replyPage = 1;
    let commentPage = 1;
    let currentPage = 1;
    let isLoading = false;

    async function fetchPosts() {
        if (isLoading) return;

        isLoading = true;
        try {
            const response = await $.ajax({
                url: "http://localhost:8888/api/posts/all?page=" + currentPage + "&sortBy=id",
                method: "GET",
                dataType: "json"
            });
            console.log(response.data);
            if(currentPage === response.data.totalPages) {
                $(window).off('scroll', checkScrollPosition);
            }
            console.log("Tải bài viết thành công!");
            await renderListPosts(response.data.content);
            currentPage++;
        } catch (error) {
            alert("Có lỗi xảy ra khi tải bài viết!");
        } finally {
            isLoading = false;
        }
    }

    async function fetchCommentsAndLikes(postId) {
        try {
            const [commentResponse, likeResponse] = await Promise.all([
                $.ajax({
                    url: "http://localhost:8888/api/comments/post/" + postId + "/count",
                    method: "GET",
                    dataType: "json"
                }),
                $.ajax({
                    url: "http://localhost:8888/api/likes/post/" + postId + "/count",
                    method: "GET",
                    dataType: "json"
                })
            ]);
            return {
                countComment: commentResponse.data,
                countLike: likeResponse.data
            };
        } catch (error) {
            console.error("Lỗi khi lấy dữ liệu bình luận hoặc like", error);
            return { countComment: 0, countLike: 0 };
        }
    }

    async function checkLiked(postId) {
        try {
            const response = await $.ajax({
                url: "http://localhost:8888/api/likes/post/" + postId + "/is-liked" ,
                method: "GET",
                dataType: "json"
            });
            console.log("isLike: " + response.data);
            return response.data;
        } catch (error) {
            console.error("Lỗi khi kiểm tra like", error);
            return false;
        }
    }

    async function renderListPosts(posts) {
        const postContainer = $('#list-post');
        for (let post of posts) {
            const { countComment, countLike } = await fetchCommentsAndLikes(post.id);
            const isLiked = await checkLiked(post.id);
            const buttonClass = isLiked ? 'btn-primary' : 'btn-outline-primary';
            const postElement = $('<div>', {
                class: 'card shadow-sm rounded-3 my-4',
                id: `post-` + post.id + ``,
                html:
                `
                <!-- Header -->
                <div class="card-header d-flex align-items-center" style="border: none">
                    <img src="` + post.user.avatar + `" alt="User Avatar" class="rounded-circle me-3" style="width: 45px; height: 45px; object-fit: cover"/>
                    <div>
                        <div class="mb-0">
                            <a href="" class="text-decoration-none">` + post.user.username + `</a>
                        </div>
                        <small class="text-muted">` + post.created + `</small>
                    </div>
                    <!-- Nút ba chấm -->
                    <div class="dropdown ms-auto">
                        <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown">
                            report
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <li><a class="dropdown-item" href="#">Giả mạo ảnh người khác</a></li>
                            <li><a class="dropdown-item" href="#">Hình ảnh nhạy cảm</a></li>
                            <li><a class="dropdown-item" href="#">Nội dung không lành mạnh</a></li>
                        </ul>
                    </div>
                </div>

                <!-- Nội dung bài đăng -->
                <div class="card-body">
                    <p class="text-muted mb-3">` + post.text + `</p>
                    <div class="d-flex justify-content-center">
                        <img src="` + post.photo + `" alt="Post Image" class="img-fluid rounded-3" style="max-height: 350px; object-fit: contain"/>
                    </div>
                </div>

                <!-- Interaction buttons -->
                <div class="d-flex justify-content-between align-items-center py-2 px-3">
                    <div>
                        <a data-bs-toggle="modal" data-bs-target="#usersLikeModal" class="text-decoration-none" onclick="fetchLikes(` + post.id + `)">
                            <span id="countLike-` + post.id + `">` + countLike + `</span> <i class="bi bi-hand-thumbs-up-fill"></i>
                        </a>
                    </div>
                    <div>
                        <a data-bs-toggle="modal" data-bs-target="#usersCommentModal" class="text-decoration-none" onclick="fetchComments(` + post.id + `)">
                           <span id="countComment-` + post.id + `">` + countComment + `</span>  <i class="bi bi-chat-fill"></i>
                        </a>
                    </div>
                </div>

                <!-- Footer -->
                <div class="card-footer d-flex justify-content-between align-items-center bg-white border-top border-bottom" style="border: none">
                    <button id="likeBtn-` + post.id + `" class="btn `+ buttonClass + ` flex-fill me-2 rounded-pill" >
                        <i class="bi bi-hand-thumbs-up-fill"></i> Thích
                    </button>
                    <button class="btn btn-outline-primary flex-fill ms-2 rounded-pill" data-bs-toggle="collapse" data-bs-target="#commentBox-` + post.id + `">
                        <i class="bi bi-chat-fill"></i> Bình luận
                    </button>
                </div>
                <div class="collapse" id="commentBox-` + post.id + `">
                    <div class="d-flex p-3" style="border-top: none">
                        <input type="text" class="form-control me-2" name="commentText" placeholder="Viết bình luận công khai... "/>
                        <button id="sendComment-` + post.id + `" class="btn btn-primary rounded-circle">
                            <i class="bi bi-send"></i>
                        </button>
                    </div>
                </div>
            `});
            postContainer.append(postElement);
        }
        console.log('Tất cả bài viết đã được tải và hiển thị!');
    }

    function toggleLike(postId, likeButton) {
        console.log("toggle like post", postId);
        const isLiked = $(likeButton).hasClass('btn-primary');
        const method = isLiked ? "DELETE" : "POST";
        $.ajax({
            url: "http://localhost:8888/api/likes/post/" + postId,
            method: method,
            success: function() {
                $(likeButton).toggleClass('btn-primary btn-outline-primary');
                let countLikeElement = $('#countLike-' + postId);
                let countLike = parseInt(countLikeElement.text().trim());
                countLike = isLiked ? countLike - 1 : countLike + 1;
                countLikeElement.text(countLike);
            },
            error: function() {
                alert("Có lỗi xảy ra khi thích bài viết!");
            }
        });
    }


    // Hàm kiểm tra khi người dùng cuộn đến cuối trang
    function checkScrollPosition() {
        //console.log(window.innerHeight);
        const scrollHeight = $(document).height();
        //console.log('scrollHeight', scrollHeight);
        const scrollPosition = $(window).scrollTop() + window.innerHeight;
        //console.log('scrollPosition', scrollPosition);

        const threshold = scrollHeight * 0.05;
        // Nếu người dùng cuộn đến cuối trang
        if (scrollHeight - scrollPosition <= threshold) {
            console.log("fetch data");
            fetchPosts();
        }
    }

     async function fetchLikes(postId) {
        console.log("fetch users like post", postId);
         $('#usersLikeModal').data('postId', postId);

        try {
            const response = await $.ajax({
                url: "http://localhost:8888/api/likes/post/" + postId + "?page=" + likePage,
                method: "GET",
                dataType: "json"
            });
            console.log(response.data);
            if (likePage >= response.data.totalPages) {
                $('#loadMoreLikesBtn').hide();
            } else {
                likePage++;
                $('#loadMoreLikesBtn').show();
            }
            await renderLikes(response.data.content);
        } catch (error) {
            alert("Có lỗi xảy ra khi tải bài viết!");
        }
    }

    async function renderLikes(likes){
        const modalBody = document.querySelector("#usersLikeModal .modal-body .list-users-like");

        if (likes.length === 0 && modalBody.childElementCount === 0) {
            modalBody.innerHTML = "<p class='text-center text-muted'>Không có lượt like nào.</p>";
            return;
        }

        for (let like of likes) {
            const likeElement = document.createElement('div');
            likeElement.classList.add('d-flex', 'justify-content-between', 'align-items-center', 'my-2');
            likeElement.innerHTML = `
                <div class="d-flex align-items-center">
                    <img src="` + like.user.avatar + `" alt="User Avatar" class="rounded-circle me-3" style="width: 35px; height: 35px; object-fit: cover"/>
                    <a href="" class="text-decoration-none text-dark">` + like.user.username + `</a>
                </div>
                <button class="btn btn-outline-primary btn-sm">
                    <i class="bi bi-person-plus"></i> Thêm bạn bè
                </button>
            `;
            modalBody.append(likeElement);
        }
    }

    async function renderComments(comments) {
        const modalBody = document.querySelector("#usersCommentModal .modal-body #commentList");
        const modalFooter = document.querySelector("#usersCommentModal .modal-footer");
        modalFooter.innerHTML = "";
        for (let comment of comments) {
            const commentElement = document.createElement('div');
            commentElement.innerHTML =
                `
                <div class="d-flex mb-2">
                <img
                  src="` + comment.user.avatar + `"
                  alt="User Avatar"
                  class="rounded-circle me-3"
                  style="width: 45px; height: 45px; object-fit: cover"
                />
                <div>
                  <div class="bg-light rounded-4 px-2 py-1">
                    <a href="" class="text-decoration-none text-dark fw-bold"
                      >` + comment.user.username + `</a
                    >
                    <p class="mb-1">` + comment.text + `</p>
                  </div>
                  <div class="d-flex align-items-center text-muted">
                    <small class="mx-2">` + comment.created + `</small>
                    <button
                      class="btn btn-link text-decoration-none text-muted"
                      onclick="fetchReplies(` + comment.id + `)"
                    >
                      Phản hồi
                    </button>
                  </div>
                </div>
                 <div class="dropdown ms-1">
                  <button
                    class="btn btn-light p-0 border-0"
                    type="button"
                    id="dropdownMenuButton"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i class="bi bi-three-dots-vertical"></i>
                    <!-- Icon 3 chấm -->
                  </button>
                  <ul
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="dropdownMenuButton"
                  >
                    <li><a class="dropdown-item" href="#">Báo cáo</a></li>
                    <li>
                      <a class="dropdown-item text-danger" onclick="deleteComment(` + comment.id + `)">Xóa</a>
                    </li>
                  </ul>
                </div>
              </div>
              <div class="ms-5" id="repliesBox-` + comment.id + `"></div>
            `;
            modalBody.append(commentElement);
        }

        const postId = $('#usersCommentModal').data('postId');

        const commentFooter = document.createElement('div');
        commentFooter.classList.add('d-flex', 'w-100', 'align-items-center');
        commentFooter.innerHTML =
            `
              <input
                name="commentTextModal"
                type="text"
                class="form-control me-2"
                placeholder="Viết bình luận công khai... "
              />
              <button class="btn btn-primary rounded-circle" id="sendCommentModal-` + postId + `">
                <i class="bi bi-send"></i>
              </button>
            `;

        modalFooter.append(commentFooter);
    }

    async function fetchReplies(commentId) {
        console.log("fetch replies for comment", commentId);
        console.log("post reply:" + $('#usersCommentModal').data('postId'));

        try {
            const response = await $.ajax({
                url: "http://localhost:8888/api/comments/" + commentId + "/replies",
                method: "GET",
                dataType: "json"
            });
            console.log(response.data);
            await renderReplies(commentId, response.data.content);

        } catch (error) {
            alert("Có lỗi xảy ra khi tải bài viết!");
        }
    }

    async function renderReplies(commentId, replies) {
        const repliesBox = document.querySelector("#repliesBox-" + commentId);
        repliesBox.innerHTML = "";
        for (let reply of replies) {
            const replyElement = document.createElement('div');
            replyElement.classList.add('ms-5');
            replyElement.id = 'Reply-' + commentId;
            replyElement.innerHTML =
                `
                <div class="d-flex mb-2">
                    <img
                      src="` + reply.user.avatar + `"
                      alt="User Avatar"
                      class="rounded-circle me-3"
                      style="width: 45px; height: 45px; object-fit: cover"
                    />
                    <div>
                      <div class="bg-light rounded-4 px-2 py-1">
                        <a
                          href=""
                          class="text-decoration-none text-dark fw-bold"
                          >` + reply.user.username + `</a
                        >
                        <p class="mb-1">` + reply.text + `</p>
                      </div>
                      <div class="d-flex align-items-center text-muted">
                        <small class="mx-2">` + reply.created + `</small>
                        <a class="text-decoration-none text-muted"
                          >Phản hồi</a
                        >
                      </div>
                    </div>
<div class="dropdown ms-1">
                  <button
                    class="btn btn-light p-0 border-0"
                    type="button"
                    id="dropdownMenuButton"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    <i class="bi bi-three-dots-vertical"></i>
                    <!-- Icon 3 chấm -->
                  </button>
                  <ul
                    class="dropdown-menu dropdown-menu-end"
                    aria-labelledby="dropdownMenuButton"
                  >
                    <li><a class="dropdown-item" href="#">Báo cáo</a></li>
                    <li>
                      <a class="dropdown-item text-danger" onclick="deleteComment(` + reply.id + `)">Xóa</a>
                    </li>
                  </ul>
                </div>
                  </div>
                       `;
            repliesBox.append(replyElement);
        }
        let toolCommentElement = document.createElement('div');
        toolCommentElement.classList.add('ms-5');
        toolCommentElement.innerHTML = `
                <div class="d-flex w-100 align-items-center mb-2">
                        <img
                          src="https://cdn2.fptshop.com.vn/small/avatar_trang_1_cd729c335b.jpg"
                          alt="User Avatar"
                          class="rounded-circle me-3"
                          style="width: 45px; height: 45px; object-fit: cover"
                        />
                        <input
                          name="replyText"
                          type="text"
                          class="form-control me-2"
                          placeholder="Viết bình luận công khai... "
                        />
                        <button class="btn btn-primary rounded-circle" id="sendReply-` + commentId + `">
                          <i class="bi bi-send"></i>
                        </button>
                      </div>
                        `;
        repliesBox.append(toolCommentElement);
    }

    async function fetchComments(postId) {
        console.log("fetch comments for post", postId);
        $('#usersCommentModal').data('postId', postId);
        try {
            const response = await $.ajax({
                url: "http://localhost:8888/api/comments/post/" + postId + "?page=" + commentPage,
                method: "GET",
                dataType: "json"
            });
            console.log(response.data);
            if (commentPage >= response.data.totalPages) {
                $('#loadMoreCommentsBtn').hide();
            } else {
                commentPage++;
                $('#loadMoreCommentsBtn').show();
            }
            console.log(response.data);
            await renderComments(response.data.content);
        } catch (error) {
            alert("Có lỗi xảy ra khi tải comment!");
        }
    }

    async function sendComment(postId, commentText, buttonType) {
        if (commentText.trim() === '') {
            alert('Vui lòng nhập bình luận!');
            return;
        }
        try {
            $.ajax({
                url: "http://localhost:8888/api/comments",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify({ text: commentText,
                    postId: postId
                }),
                success: function(response) {
                    if (buttonType === 'sendCommentModal') {
                        $('#usersCommentModal').modal('hide');
                    }
                    else {
                        $('#commentBox-' + postId).find('input[name="commentText"]').val('');
                    }
                    let countCommentElement = $('#countComment-' + postId);
                    let countComment = parseInt(countCommentElement.text().trim());
                    countComment++;
                    countCommentElement.text(countComment);
                    alert('Bình luận thành công !' + response.data.id);
                },
                error: function(xhr) {
                    alert('Có lỗi xảy ra khi bình luận: ' + xhr.responseText);
                }
            });
        } catch (error) {
            alert('Có lỗi xảy ra khi bình luận: ' + error);
        }
    }

    function sendReply(commentId, replyText) {
        if (replyText.trim() === '') {
            alert('Vui lòng nhập phản hồi!');
            return;
        }
        try {
            $.ajax({
                url: "http://localhost:8888/api/comments/reply",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify({ text: replyText,
                    commentId: commentId
                }),
                success: function(response) {
                    $('#repliesBox-' + commentId).find('input[name="replyText"]').val('');
                    let countCommentElement = $('#countComment-' + response.data.parent.id);
                    let countComment = parseInt(countCommentElement.text().trim());
                    countComment++;
                    countCommentElement.text(countComment);
                    fetchReplies(commentId);
                },
                error: function(xhr) {
                    alert('Có lỗi xảy ra khi phản hồi: ' + xhr.responseText);
                }
            });
        } catch (error) {
            alert('Có lỗi xảy ra khi phản hồi: ' + error);
        }
    }

        $(document).on('click', 'button', function() {
            const buttonId = $(this).attr('id');
            if (!buttonId) return;
            let buttonType = buttonId.split('-')[0];
            const id = buttonId.split('-')[1];
            if(buttonType === 'likeBtn') {
                toggleLike(id, this);
            }
            else if(buttonType === 'sendComment') {
                console.log('Send comment for post', id);
                const commentText = $(this).siblings('input[name="commentText"]').val();
                sendComment(id, commentText, buttonType);
            }
            else if(buttonType === 'sendCommentModal') {
                console.log('Send comment for post', id);
                const commentText = $(this).siblings('input[name="commentTextModal"]').val();
                sendComment(id, commentText, buttonType);
            }
            else if(buttonType === 'sendReply') {
                console.log('Send reply for comment', id);
                const replyText = $(this).siblings('input[name="replyText"]').val();
                sendReply(id, replyText);
            }
            console.log('You clicked on button with ID:', buttonId);
        });

    function deleteComment(commentId){
        console.log("delete comment", commentId);
        const postId = $('#usersCommentModal').data('postId');
        $.ajax({
            url: "http://localhost:8888/api/comments/" + commentId,
            method: "DELETE",
            success: async function (response) {
                alert(response.data);
                let countCommentElement = $('#countComment-' + postId);
                const countComment = (await fetchCommentsAndLikes(postId)).countComment;
                countCommentElement.text(countComment);
                $('#usersCommentModal').modal('hide');
            },
            error: function() {
                alert("Có lỗi xảy ra khi xóa bình luận!");
            }
        });
    }

    $('#loadMoreCommentsBtn').on('click', function() {
        console.log('Load more comments');
        const postId = $('#usersCommentModal').data('postId');
        fetchComments(postId);
    });

    $('#usersCommentModal').on('hidden.bs.modal', function () {
        const modalBody = document.querySelector("#usersCommentModal .modal-body #commentList");
        modalBody.innerHTML = "";
        commentPage = 1;
    });


    $('#loadMoreLikesBtn').on('click', function() {
        const postId = $('#usersLikeModal').data('postId');
        fetchLikes(postId);
    });

    $('#usersLikeModal').on('hidden.bs.modal', function () {
        const modalBody = document.querySelector("#usersLikeModal .modal-body .list-users-like");
        modalBody.innerHTML = "";
        likePage = 1;
    });

    $(window).on('scroll', checkScrollPosition);

    $(document).ready(function() {
        fetchPosts();
    });
</script>


