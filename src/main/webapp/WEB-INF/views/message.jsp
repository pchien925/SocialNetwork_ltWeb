<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <div class="container-fluid newsfeed d-flex" id="wrapper">
        <div class="row newsfeed-size">
            <div class="col-md-12 message-right-side">

                <div class="row message-right-side-content">
                    <div class="col-md-12">
                        <div id="message-frame">
                            <div class="message-sidepanel">
                                <div class="message-profile">
                                    <div class="wrap">
                                        <img src="<c:choose>
                <c:when test="${not empty user.avatar}">${user.avatar}</c:when>
                <c:otherwise>/assets/images/users/user-11.png</c:otherwise>
            </c:choose>" class="online conv-img" alt="Conversation user" />
            <p>${user.getFullName()}</p>
                                        <p hidden="false">${user.id}</p>
                                        <i class='bx bx-chevron-down expand-button'></i>
                                        <div id="status-options">
                                            <ul id="set-online-status">
                                                <li id="status-online" class="messenger-user-active"><span class="status-circle"></span>
                                                    <p>Online</p>
                                                </li>
                                                <li id="status-away"><span class="status-circle"></span>
                                                    <p>Away</p>
                                                </li>
                                                <li id="status-busy"><span class="status-circle"></span>
                                                    <p>Busy</p>
                                                </li>
                                                <li id="status-offline"><span class="status-circle"></span>
                                                    <p>Offline</p>
                                                </li>
                                            </ul>
                                        </div>
                                        <div id="expanded">
                                            <ul class="list-group list-group-flush">
                                                <li class="list-group-item">
                                                    <a href="javascript:void(0)" class="text-dark fs-9"><i class='bx bx-search text-primary mr-3'></i> Search in Conversation</a>
                                                </li>
                                                <li class="list-group-item">
                                                    <a href="javascript:void(0)" class="text-dark fs-9"><i class='bx bx-pencil text-primary mr-3'></i> Edit Nicknames</a>
                                                </li>
                                                <li class="list-group-item">
                                                    <a href="javascript:void(0)" class="text-dark fs-9"><i class='bx bxs-color-fill text-primary mr-3'></i> Change Color</a>
                                                </li>
                                                <li class="list-group-item">
                                                    <a href="javascript:void(0)" class="text-dark fs-9"><i class='bx bx-bell text-primary mr-3'></i> Notifications</a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <div class="message-search position-relative d-flex">
                                    <label for=""><i class='bx bx-search'></i></label>
                                    <input type="text" class="form-control search-input" placeholder="Search for conversations..." />
                                    <button type="button" class="btn btn-create-conversation" data-toggle="modal" data-target="#newConversation"><i class='bx bx-pencil'></i></button>
                                </div>
                                <div class="message-contacts">
	                                    <ul class="conversations">
	                                        <li class="contact">
	                                            <div class="wrap">
	                                                <span class="contact-status online"></span>
	                                                <img src="/assets/images/users/user-5.png" alt="Conversation user" />
	                                                <span class="unread-messages">3</span>
	                                                <div class="meta">
	                                                    <p class="name">Ruth D. Greene</p>
	                                                </div>
	                                            </div>
	                                        </li>
	                                    </ul>
                                </div>
                            </div>
                            <div class="content">
                                <div class="row">
                                    <div class="col-md-12 messenger-top-section">
                                        <div class="contact-profile d-flex align-items-center justify-content-between">
                                            <div class="messenger-top-luser df-aic">
                                                <img hidden=false src="/assets/images/users/user-6.png" class="messenger-user" alt="Convarsation user image" />
                                                <a href="#" class="message-profile-name">Select your friend to start a conversation.</a>
                                            </div>
                                            <div class="social-media messenger-top-ricon df-aic">
                                                <img src="/assets/images/icons/messenger/phone.png" data-toggle="modal" data-target="#callModal" class="msg-top-more-info" alt="Messenger icons">
                                                <img src="/assets/images/icons/messenger/videocam.png" class="msg-top-more-info" alt="Messenger icons">
                                                <img src="/assets/images/icons/messenger/info.png" class="msg-top-more-info" alt="Messenger icons">
                                                <span class="dropdown">
                                                    <a href="#" role="button" id="message-options" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                        <i class='bx bx-dots-vertical-rounded'></i>
                                                    </a>
                                                    <div class="dropdown-menu dropdown-menu-right fs-9" aria-labelledby="message-options">
                                                        <a href="#" class="dropdown-item">Mute</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a href="#" class="dropdown-item">Delete</a>
                                                        <div class="dropdown-divider"></div>
                                                        <a href="#" class="dropdown-item">Mark as Unread</a>
                                                        <a href="#" class="dropdown-item">Something's wrong</a>
                                                        <a href="#" class="dropdown-item">Ignore Messages</a>
                                                        <a href="#" class="dropdown-item">Block Messages</a>
                                                    </div>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-12" style="max-height: 532px;">
                                        <div class="messages">
                                            <ul hidden=false class="messages-content" id="message-list">


                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-md-12">
                                        <div class="message-input">
                                            <div class="wrap">
                                                 <form class="d-inline form-inline" id="message-form"
       										 action="${pageContext.request.contextPath}/api/messages/post/"
       											 method="POST">
                                                    <div class="d-flex align-items-center justify-content-between messenger-icons">
                                                        <button type="button" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-camera.png" alt="Messenger icons">
                                                        </button>
                                                        <button type="button" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-photo.png" alt="Messenger icons">
                                                        </button>
                                                        <button type="button" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-gamepad.png" alt="Messenger icons">
                                                        </button>
                                                        <button type="button" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-microphone.png" alt="Messenger icons">
                                                        </button>
                                                        <div class="input-group messenger-input">
                                                            <input id="message-input" type="text" class="form-control search-input" placeholder="Type a message..." aria-label="Type a message..." aria-describedby="button-addon2">
                                                            <div class="input-group-append">
                                                                <button class="btn search-button" type="button" id="button-addon2 send-message">
                                                                    <img src="/assets/images/icons/messenger/m-smile.png" alt="Messenger icons">
                                                                </button>
                                                            </div>
                                                        </div>
                                                        <button type="submit" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-send.png" alt="Messenger icons">
                                                        </button>

                                                    </div>
                                                </form>
                                                <%-- <form id="message-form"
        											action="${pageContext.request.contextPath}/api/messages/post/48"
       												 method="POST">
												        <input type="text" name="message" id="message-input"
												            placeholder="Type your message..." required>
												        <button type="submit" class="btn search-button">
                                                            <img src="/assets/images/icons/messenger/m-send.png" alt="Messenger icons">
                                                        </button>
    											</form> --%>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- New Conversation Modal -->
    <div class="modal fade" id="newConversation" tabindex="-1" role="dialog" aria-labelledby="newConversationLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header new-msg-header">
                    <h5 class="modal-title" id="newConversationLabel">Start new conversation</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body new-msg-body">
                    <form action="" method="" class="new-msg-form">
                        <div class="form-group">
                            <label for="recipient-name" class="col-form-label">Recipient:</label>
                            <input type="text" class="form-control search-input" id="recipient-name" placeholder="Add recipient...">
                        </div>
                        <div class="form-group">
                            <label for="message-text" class="col-form-label">Message:</label>
                            <textarea class="form-control search-input" rows="5" id="message-text" placeholder="Type a message..."></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer new-msg-footer">
                    <button type="button" class="btn btn-primary btn-sm">Send message</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Call modal -->
    <div id="callModal" class="modal fade call-modal" tabindex="-1" role="dialog" aria-labelledby="callModalLabel" aria-hidden="true">
        <div class="modal-dialog call-modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header align-items-center">
                    <div class="call-status">
                        <h1 id="callModalLabel" class="modal-title mr-3">Connected</h1>
                        <span class="online-status bg-success"></span>
                    </div>
                    <div class="modal-options d-flex align-items-center">
                        <button type="button" class="btn btn-quick-link" id="minimize-call-window">
                            <i class='bx bx-minus'></i>
                        </button>
                    </div>
                </div>
                <div class="modal-body">
                    <div class="row h-100">
                        <div class="col-md-12 d-flex align-items-center justify-content-center">
                            <div class="call-user text-center">
                                <div class="call-user-img-anim">
                                    <img src="/assets/images/users/user-1.jpg" class="call-user-img" alt="Call user image">
                                </div>
                                <p class="call-user-name">Name Surename</p>
                                <p class="text-muted call-time">05:28</p>
                            </div>
                        </div>
                        <div class="col-md-4 offset-md-4 d-flex align-items-center justify-content-between call-btn-list">
                            <a href="#" class="btn call-btn" data-toggle="tooltip" data-placement="top" data-title="Disable microphone"><i class='bx bxs-microphone'></i></a>
                            <a href="#" class="btn call-btn" data-toggle="tooltip" data-placement="top" data-title="Enable camera"><i class='bx bxs-video-off'></i></a>
                            <a href="#" class="btn call-btn drop-call" data-toggle="tooltip" data-placement="top" data-title="End call"><i class='bx bxs-phone'></i></a>
                            <a href="#" class="btn call-btn" data-toggle="tooltip" data-placement="top" data-title="Share Screen"><i class='bx bx-laptop'></i></a>
                            <a href="#" class="btn call-btn" data-toggle="tooltip" data-placement="top" data-title="Dark mode"><i class='bx bx-moon'></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END call modal -->

    <!-- Core -->
    <script>

var messageForm = document.querySelector('#message-form');

var stompClient = null;
var userIdElement = document.querySelector('.message-profile p:nth-of-type(2)');

//Lấy giá trị của user.id
var userId = userIdElement.textContent;
loadFriends(userId);
var userReceiver=48;
var currentPage = 1; // Tracks the current page of messages
var socket = new SockJS('/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, onConnected, onError);
function loadFriends(idUser) {
    $.ajax({
        url: '/user/get/friends/' + idUser,
        method: 'GET',
        dataType: 'json',
        success: function (friends) {
            console.log(friends); // Kiểm tra dữ liệu trả về từ API
            if (friends && friends.length > 0) {
                let friendsHtml = '';
                friends.forEach(function (friend) {
                    console.log(friend.fullName);
                    let avatar = friend.avatar || '/assets/images/users/user-11.png'; // Ảnh mặc định nếu không có avatar
                    friendsHtml += '<li class="contact">';
                    	friendsHtml += '<div class="wrap">';
                    		friendsHtml += '<span class="contact-status online"></span>';
                    		friendsHtml += '<img src="'+avatar+'" alt="Conversation user" />';
                    		friendsHtml += '<div class="meta">';
                    		friendsHtml += '<p class="name">'+friend.fullName+'</p>'
                    		+'<p hidden="false" class="name">'+friend.id+'</p>'
                    		+'</div>'
                    		+'</div>'
                    		+'</li>';

                });

                // Chèn HTML vào danh sách bạn bè
                $('.conversations').empty().append(friendsHtml);
            }
            else {
                // Hiển thị thông báo nếu không có bạn bè
                $('.conversations').html('<p>No friends found.</p>');
            }
        },
        error: function (xhr) {
            console.error('Error loading friends:', xhr.responseText); // Log lỗi chi tiết
            alert('Error loading friends');
        }
    });
}
document.addEventListener('click', function(event) {
    // Kiểm tra xem phần tử được click có class là "contact"
    if (event.target.closest('.contact')) {
        var contactElement = event.target.closest('.contact');

        // Lấy giá trị của thẻ <p hidden="false" class="name">
        var id = contactElement.querySelector('p[hidden="false"].name')?.textContent || '';

        // Lấy giá trị của thẻ <p class="name"> đầu tiên (tên người dùng)
        var name = contactElement.querySelector('p.name:not([hidden])')?.textContent || '';

        // Lấy giá trị src của thẻ <img>
        var imgSrc = contactElement.querySelector('img')?.getAttribute('src') || '';

        console.log('ID:', id);
        console.log('Name:', name);
        console.log('Image Source:', imgSrc);
        userReceiver=id;
        const userImage = document.querySelector('.messenger-top-luser .messenger-user');
        userImage.setAttribute('src', imgSrc);

        // Hiển thị thẻ <img> bằng cách bỏ thuộc tính hidden
        userImage.removeAttribute('hidden');
        document.querySelector('.messenger-top-luser .message-profile-name').textContent = name;   // Thay đổi tên
        const messengerTopUser = $('.messenger-top-luser');
        messengerTopUser.find('img.messenger-user').attr('true', true);
        var messageList = document.getElementById('message-list');
        if (messageList.hasAttribute('hidden')) {
            messageList.removeAttribute('hidden');
        }
        loadMessages(userReceiver, currentPage);
    }
});


function onConnected() {
    // Subscribe to the Public Topic
    stompClient.subscribe('/topic/public', onMessageReceived);
    // Tell your userId to the server
    stompClient.send("/app/chat.addUser",
        {},
        JSON.stringify(
        		{
                    "id": 25,
                    "createdAt": "2024-12-11T05:56:47.222874",
                    "updatedAt": "2024-12-11T05:56:47.222874",
                    "text": "thienly13",
                    "userTransmitter": {
                        "id": userId,
                        "createdAt": "2024-11-28T00:00:00",
                        "updatedAt": "2024-11-28T00:00:00",
                        "firstName": "Nguyễn",
                        "lastName": "Hòa",
                        "dateOfBirth": "2024-11-07",
                        "gender": "Male",
                        "phone": "0966736338",
                        "email": "hoa856856@gmail.com",
                        "password": "$2a$10$iPpaUoSjhORHPI/keBhD6.UagGMTxHhLeVSTa3/hviaxSsDarP4iu",
                        "otp": "066228",
                        "otpGeneratedTime": "2024-11-28T10:19:45.423975",
                        "isActive": true,
                        "roles": [
                            {
                                "id": 1,
                                "createdAt": null,
                                "updatedAt": null,
                                "name": "USER"
                            }
                        ],
                        "fullName": "Nguyễn Hòa"
                    },
                    "userReceiver": {
                        "id": userReceiver,
                        "createdAt": "2024-11-26T00:00:00",
                        "updatedAt": "2024-11-26T00:00:00",
                        "firstName": "Mai",
                        "lastName": "Hoa",
                        "dateOfBirth": "1989-05-17",
                        "gender": "Female",
                        "phone": "0961234567",
                        "email": "maihoa@gmail.com",
                        "password": "$2a$10$iPpaUoSjhORHPI/keBhD6.UagGMTxHhLeVSTa3/hviaxSsDarP4iu",
                        "otp": "123654",
                        "otpGeneratedTime": "2024-11-26T21:05:01",
                        "isActive": true,
                        "roles": [],
                        "fullName": "Mai Hoa"
                    },
                    "photo": "testUrl"
                })
    )
}
function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
    alert("loi ket noi");
}


	// Function to load messages from the server
	function loadMessages(friendId, currentPage) {
	    $.ajax({
	        url: '/api/messages/get/list/' + friendId + '&' + currentPage,
	        method: 'GET',
	        dataType: 'json',
	        success: function(response) {
	            if (response.messages && response.messages.length > 0) {
	                let messagesHtml = '';
	                response.messages.reverse().forEach(function(message) {
	                    if (message.userTransmitter.id !=userId) {
	                        // Message received
	                        messagesHtml += '<li class="message-receive">';
	                        messagesHtml += '<img src="' + (message.userTransmitter.userId || '/assets/images/users/user-11.png') + '" alt="Conversation user image" />';
	                    } else {
	                        // Message sent
	                        messagesHtml += '<li class="message-reply">';
	                    }
	                    messagesHtml += '<p>' + message.text + '</p>';
	                    messagesHtml += '</li>';
	                });

	                // Clear the current message list and append new messages
	                $('#message-list').empty();
	                $('#message-list').append(messagesHtml);

	                // Show or hide the "Load More" button based on response
	                if (!response.isLastPage) {
	                    $('#load-more-btn').show();
	                } else {
	                    $('#load-more-btn').hide();
	                }
	            } else {
	                // No messages to load
	                $('#load-more-btn').hide();
	            }

	        },
	        error: function() {
	            alert("Error loading messages");
	        }
	    });
	}

	// Load more messages when the "Load More" button is clicked
	$('#load-more-btn').on('click', function() {
		currentPage++;
		loadMessages(userReceiver, currentPage);
	});

	$('#message-form').on('submit', function(event) {
	    event.preventDefault(); // Ngăn form gửi yêu cầu thông thường
	    sendMessage(event); // Gọi hàm sendMessage
	    const message = $('#message-input').val(); // Lấy nội dung tin nhắn
	    $.ajax({
	        url: $(this).attr('action')+userReceiver,
	        method: "POST",
	        contentType: "application/json",
	        data: JSON.stringify({ message: message }), // Gửi tin nhắn dưới dạng JSON
	        success: function(response) {
	            // Xóa nội dung nhập
	            $('#message-input').val('');
	            $('#message-list').empty();
	            loadMessages(userReceiver, currentPage);
	        },
	        error: function(xhr) {
	            alert('Error sending message: ' + xhr.responseText);
	        }
	    });
	});

	function sendMessage(event) {
	    var messageInput = document.querySelector('#message-input');
	    var messageContent = messageInput.value.trim();
	    if (messageContent && stompClient) {
	        var chatMessage = {
	            "id": 25,
	            "createdAt": "2024-12-11T05:56:47.222874",
	            "updatedAt": "2024-12-11T05:56:47.222874",
	            "text": messageContent,
	            "userTransmitter": {
	                "id": userId,
	            },
	            "userReceiver": {
	                "id": userReceiver,

	            },
	            "photo": "testUrl"
	        };
	        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
	    }
	    event.preventDefault();
	}
	function onMessageReceived(payload) {
	    var message = JSON.parse(payload.body);
	 // Load the first set of messages
		loadMessages(userReceiver, currentPage);
	}
	messageForm.addEventListener('submit', sendMessage, true)

</script>
    <script src="/assets/js/jquery/jquery-3.3.1.min.js"></script>
    <script src="/assets/js/popper/popper.min.js"></script>
    <script src="/assets/js/bootstrap/bootstrap.min.js"></script>
    <script src="/assets/js/jquery/jquery-ui.js"></script>
    <!-- Optional -->
    <script src="/assets/js/components/components.js"></script>
    <script src="/assets/js/app.js"></script>
