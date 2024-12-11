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
	 
	// Load the first set of messages
	loadMessages(friendId, currentPage);
	

	// Load more messages when the "Load More" button is clicked
	$('#load-more-btn').on('click', function() {
		currentPage++;
		loadMessages(friendId, currentPage);
	});
	
	$('#message-form').on('submit', function(event) {
	    event.preventDefault(); // Ngăn form gửi yêu cầu thông thường

	    const message = $('#message-input').val(); // Lấy nội dung tin nhắn

	    $.ajax({
	        url: $(this).attr('action'),
	        method: "POST",
	        contentType: "application/json",
	        data: JSON.stringify({ message: message }), // Gửi tin nhắn dưới dạng JSON
	        success: function(response) {
	            // Hiển thị tin nhắn mới ngay sau khi gửi
	            /* var messageHtml = '';
	            messageHtml += '<div class="message-box">';
	            messageHtml += '<div class="message-text">' + message + '</div>';
	            messageHtml += '<div class="message-time"><small>Just now</small></div>'; // Thời gian tin nhắn
	            messageHtml += '</div>';


	            // Xóa nội dung nhập*/
	            $('#message-input').val(''); 

	            $('#message-list').empty(); // Thêm tin nhắn vào danh sách tin nhắn
	            // Tải lại các tin nhắn để chắc chắn chúng đã được lưu vào hệ thống
	            loadMessages(friendId, currentPage);
	        },
	        error: function(xhr) {
	            alert('Error sending message: ' + xhr.responseText);
	        }
	    });
	});


</script>