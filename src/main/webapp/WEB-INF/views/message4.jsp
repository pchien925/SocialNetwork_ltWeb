<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div id="userId-page">
    <div class="userId-page-container">
        <h1 class="title">Type your userId to enter the Chatroom</h1>
        <form id="userIdForm" name="userIdForm">
            <div class="form-group">
                <input type="text" id="name" placeholder="userId" autocomplete="off" class="form-control" value="48"/>
            </div>
            <div class="form-group">
                <button type="submit" class="accent userId-submit">Start Chatting</button>
            </div>
        </form>
    </div>
</div>

<div id="chat-page" class="hidden">
	<div class="chat-container">
		<div class="chat-header">
			<h2>Spring WebSocket Chat Demo - By Alibou</h2>
		</div>
		<div class="connecting">Connecting...</div>
		<ul id="messageArea">

		</ul>
		<form id="messageForm" name="messageForm">
			<div class="form-group">
				<div class="input-group clearfix">
					<input type="text" id="message" placeholder="Type a message..."
						autocomplete="off" class="form-control" />
					<button type="submit" class="primary">Send</button>
				</div>
			</div>
		</form>
	</div>
</div>


<script>
var userIdPage = document.querySelector('#userId-page');
var chatPage = document.querySelector('#chat-page');
var userIdForm = document.querySelector('#userIdForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var userId = null;

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];
var userTransmitter=3;
var userReceiver=48;
userId = document.querySelector('#name').value.trim();
userIdPage.classList.add('hidden');
chatPage.classList.remove('hidden');

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
    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
    alert("loi ket noi");
}


function sendMessage(event) {
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
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);
    var messageElement = document.createElement('li');

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.text);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

messageForm.addEventListener('submit', sendMessage, true)

</script>