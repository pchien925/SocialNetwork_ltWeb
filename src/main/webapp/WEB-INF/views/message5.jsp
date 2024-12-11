<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>



<div class="message-container">
	<h2>Messages</h2>

	<!-- Display messages -->
	<div id="message-list">
		<!-- Messages will be loaded here via AJAX -->
	</div>

	<!-- Load more button -->
	<button id="load-more-btn" class="load-more-button">Load More</button>

	<!-- Send new message -->
	<div class="form-container">
    <form id="message-form"
        action="${pageContext.request.contextPath}/api/messages/post/48"
        method="POST">
        <input type="text" name="message" id="message-input"
            placeholder="Type your message..." required>
        <button type="submit">
            <i class="fas fa-paper-plane"></i> Send
        </button>
    </form>
</div>

</div>

<script>
var messageForm = document.querySelector('#message-form');

var stompClient = null;
var userId = null;
var userTransmitter=3;
var userReceiver=48;
var socket = new SockJS('/ws');
stompClient = Stomp.over(socket);
stompClient.connect({}, onConnected, onError);
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
                        "id": 3,
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
                        "id": 48,
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


function sendMessage(event) {
	var messageInput = document.querySelector('#message-input');
    var messageContent = messageInput.value.trim();
    if(messageContent && stompClient) {
        var chatMessage = {
                "id": 25,
                "createdAt": "2024-12-11T05:56:47.222874",
                "updatedAt": "2024-12-11T05:56:47.222874",
                "text": messageContent,
                "userTransmitter": {
                    "id": 3,
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
                    "id": 48,
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
            };
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));
    }
    event.preventDefault();
}




	var friendId = 48;
	var currentPage = 1; // Tracks the current page of messages
	// Function to load messages from the server
	function loadMessages(friendId, currentPage) {

		$
				.ajax({
					url : '/api/messages/get/list/' + friendId + '&'
							+ currentPage,
					method : 'GET',
					dataType : 'json',
					success : function(response) {
						if (response.messages && response.messages.length > 0) {
							var messagesHtml = '';
							response.messages
									.forEach(function(message) {
										messagesHtml += '<div class="message-box">';
										messagesHtml += '<div class="message-text">'
												+ message.text + '</div>';
										if (message.photo) {
											messagesHtml += '<img src="' + message.photo + '" alt="Photo" class="message-photo">';
										}
										messagesHtml += '<div class="message-time"><small>'
												+ message.createdAt
												+ '</small></div>';
										messagesHtml += '</div>';
									});
							$('#message-list').empty();
							$('#message-list').append(messagesHtml);

							// If there are no more messages to load, hide the "Load More" button
							if (!response.isLastPage) {
								$('#load-more-btn').show();
							} else {
								$('#load-more-btn').hide();
							}
						} else {
							$('#load-more-btn').hide(); // Hide button if no messages
						}
					},
					error : function() {
						alert("Error loading messages");
					}
				});
	}
	 
	
	

	// Load more messages when the "Load More" button is clicked
	$('#load-more-btn').on('click', function() {
		currentPage++;
		loadMessages(friendId, currentPage);
	});
	
	$('#message-form').on('submit', function(event) {
	    event.preventDefault(); // Ngăn form gửi yêu cầu thông thường
	    sendMessage;
	    const message = $('#message-input').val(); // Lấy nội dung tin nhắn

	    $.ajax({
	        url: $(this).attr('action'),
	        method: "POST",
	        contentType: "application/json",
	        data: JSON.stringify({ message: message }), // Gửi tin nhắn dưới dạng JSON
	        success: function(response) {

	            // Xóa nội dung nhập*/
	            $('#message-input').val(''); 

	            $('#message-list').empty();
	            loadMessages(friendId, currentPage);
	        },
	        error: function(xhr) {
	            alert('Error sending message: ' + xhr.responseText);
	        }
	    });
	});
	messageForm.addEventListener('submit', sendMessage, true)
	function onMessageReceived(payload) {
	    var message = JSON.parse(payload.body);
	 // Load the first set of messages
		loadMessages(friendId, currentPage);
	}
</script>