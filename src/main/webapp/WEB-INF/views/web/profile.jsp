<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="container mt-5">
    <div class="row">
        <!-- Sidebar -->
        <div class="col-md-3">
            <div class="card">
                <div class="card-header bg-primary text-white text-center">
                    <h5>Profile Details</h5>
                </div>
                <div class="card-body text-center">
                    <img src=${profile.avatar} class="img-fluid rounded-circle" alt="Profile Image">

                    <h6 class="mt-3">${profile.firstName} ${profile.lastName}</h6>

                    <hr>
                    <ul class="list-group">
                        <li class="list-group-item"><strong>Email:</strong> ${profile.email}</li>
                        <li class="list-group-item"><strong>Phone:</strong> ${profile.phone}</li>
                        <li class="list-group-item"><strong>Gender:</strong> ${profile.gender}</li>
                        <li class="list-group-item"><strong>DOB:</strong> ${profile.dateOfBirth}</li>
                        <div class="d-flex justify-content-between mt-3">
                                                        <button class="btn btn-outline-primary btn-sm" onclick="addFriend(${userId})">
                                                            <i class="bi bi-person-plus"></i> Kết bạn
                                                        </button>
                                                        <button class="btn btn-outline-info btn-sm" onclick="addFollow(${userId})">
                                                            <i class="bi bi-person-check"></i> Theo dõi
                                                        </button>
                                                    </div>
                    </ul>
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
        <!-- Main Content -->
        <div class="col-md-9">
            <div class="card">
                <div class="card-header bg-info text-white">
                    <strong>${profile.firstName}'s Wall</strong>
                </div>
                <div class="card-body">

                    <hr>
                    <!-- Recent Posts -->
                    <div id="list-post">
                                <!-- post -->
                            </div>
                </div>
            </div>
        </div>
    </div>
</div>




<!-- Add jQuery and Ajax Script -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function() {
        // Extract userId from URL
        var path = window.location.pathname;
        var userId = path.split('/').pop(); // Get the last part of the URL after the last slash




       $.ajax({
           url: '/api/posts/user/' + userId,  // Use the extracted userId in the URL
           method: 'GET',
           success: async function(posts) {
               const postContainer = $('#list-post');

               // Loop through the posts to handle each post
               for (let post of posts) {
                   const postId = post.id;  // Get the postId from the current post

                   // Fetch comments and likes count for the post
                   const { countComment, countLike } = await fetchCommentsAndLikes(postId);

                   // Check if the post is liked
                   const isLiked = await checkLiked(postId);
                   const buttonClass = isLiked ? 'btn-primary' : 'btn-outline-primary';

                   // Create the post element dynamically
                   const postElement = $('<div>', {
                       class: 'card shadow-sm rounded-3 my-4',
                       id: `post-` + postId,  // Use postId as part of the id
                       html: `
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
                                       Báo cáo
                                   </button>
                                   <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                       <li><a class="dropdown-item" href="#" onclick="openReportModal('1', 'Nội dung bịa đặt, sai sự thật', ` + postId + `)">Nội dung bịa đặt, sai sự thật</a></li>
                                       <li><a class="dropdown-item" href="#" onclick="openReportModal('2', 'Giả mạo người khác', ` + postId + `)">Giả mạo người khác</a></li>
                                       <li><a class="dropdown-item" href="#" onclick="openReportModal('3', 'Nội dung chứa hình ảnh nhạy cảm', ` + postId + `)">Nội dung chứa hình ảnh nhạy cảm</a></li>
                                       <li><a class="dropdown-item" href="#" onclick="openReportModal('4', 'Nội dung xúc phạm người khác', ` + postId + `)">Nội dung xúc phạm người khác</a></li>
                                   </ul>
                               </div>
                           </div>

                           <!-- Nội dung bài đăng -->
                           <div class="card-body">
                               <p class="text-muted mb-3">` + post.text + `</p>
                               <div class="d-flex justify-content-center" style="height: 500px; " bis_skin_checked="1">
                                   <img src="` + post.photo + `" alt="Post Image" class="  rounded-3" style=" width: 100%;object-fit: contain"/>
                               </div>
                           </div>

                           <!-- Interaction buttons -->
                           <div class="d-flex justify-content-between align-items-center py-2 px-3">
                               <div>
                                   <a data-bs-toggle="modal" data-bs-target="#usersLikeModal" class="text-decoration-none" onclick="fetchLikes(` + postId + `)">
                                       <span id="countLike-` + postId + `">` + countLike + `</span> <i class="bi bi-hand-thumbs-up-fill"></i>
                                   </a>
                               </div>
                               <div>
                                   <a data-bs-toggle="modal" data-bs-target="#usersCommentModal" class="text-decoration-none" onclick="fetchComments(` + postId + `)">
                                      <span id="countComment-` + postId + `">` + countComment + `</span>  <i class="bi bi-chat-fill"></i>
                                   </a>
                               </div>
                           </div>

                           <!-- Footer -->
                           <div class="card-footer d-flex justify-content-between align-items-center bg-white border-top border-bottom" style="border: none">
                               <button id="likeBtn-` + postId + `" class="btn ` + buttonClass + ` flex-fill me-2 rounded-pill" >
                                   <i class="bi bi-hand-thumbs-up-fill"></i> Thích
                               </button>
                               <button class="btn btn-outline-primary flex-fill ms-2 rounded-pill" data-bs-toggle="collapse" data-bs-target="#commentBox-` + postId + `">
                                   <i class="bi bi-chat-fill"></i> Bình luận
                               </button>
                           </div>
                           <div class="collapse" id="commentBox-` + postId + `">
                               <div class="d-flex p-3" style="border-top: none">
                                   <input type="text" class="form-control me-2" name="commentText" placeholder="Viết bình luận công khai... "/>
                                   <button id="sendComment-` + postId + `" class="btn btn-primary rounded-circle">
                                       <i class="bi bi-send"></i>
                                   </button>
                               </div>
                           </div>
                       `});

                   // Append the post to the container
                   postContainer.append(postElement);
               }
               console.log('Tất cả bài viết đã được tải và hiển thị!');
           }
       });
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
    function openReportModal(reasonId, reason, postId) {
        document.getElementById('postReportId').value = postId;
        document.getElementById('reportReasonId').value = reasonId;
        document.getElementById('reportReason').value = reason;
        var reportModal = new bootstrap.Modal(document.getElementById('reportModal'));
        reportModal.show();
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

    document.getElementById('submitReport').addEventListener('click', function () {
        const postReportId = document.getElementById('postReportId').value;
        const reportReasonId = document.getElementById('reportReasonId').value;
        const reportReason = document.getElementById('reportReason').value;
        const extraInformation = document.getElementById('extraInformation').value;

        // Send the data to the server (use fetch or AJAX)
        console.log({postReportId, reportReasonId, reportReason, extraInformation }); // Replace with actual server call

        try {
            $.ajax({
                url: "http://localhost:8888/api/mod/reports/create",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify({
                    postId : postReportId,
                    reportReasonId: reportReasonId,
                    userId: 3,
                    extraInformation: extraInformation,
                }),
                success: function(response) {
                    console.log(response.data);
                    alert('Báo cáo thành công!');
                },
                error: function(xhr) {
                    alert('Có lỗi xảy ra khi thêm: ' + xhr.responseText);
                }
            });
        } catch (error) {
            alert('Có lỗi xảy ra khi bình luận: ' + error);
        }

        // Close the modal after submission
        var reportModal = bootstrap.Modal.getInstance(document.getElementById('reportModal'));
        reportModal.hide();

        // Optionally reset the form
        document.getElementById('reportForm').reset();
    });

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

    })
    });

// Function to extract the userId from the current URL
function getUserIdFromURL() {
    const urlParts = window.location.pathname.split('/');
    return urlParts[urlParts.length - 1]; // The userId is the last part of the path
}

function addFriend() {
    const userId = getUserIdFromURL();
    fetch('/user/api/sendFriendRequest', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ userId: userId })
    })
    .then(response => response.json())
    .then(data => {
        alert(data.status === 'success' ? data.message : 'Lỗi: ' + data.message);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Có lỗi xảy ra khi thêm bạn.');
    });
}

function addFollow() {
    const userId = getUserIdFromURL();
    fetch('/user/api/followerships/follow', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ userId: userId })
    })
    .then(response => response.json())
    .then(data => {
        alert(data.status === 'success' ? data.message : 'Lỗi: ' + data.message);
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Có lỗi xảy ra khi theo dõi.');
    });
}


</script>





<!-- Add custom CSS -->
<style>
    .card {
        border-radius: 10px;
    }
    .card-header {
        font-size: 18px;
        font-weight: bold;
    }
    .img-fluid {
        max-width: 150px;
    }
    .media-body p {
        font-size: 14px;
        color: #555;
    }
</style>
