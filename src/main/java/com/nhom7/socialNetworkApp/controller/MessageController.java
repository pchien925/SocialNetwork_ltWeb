package com.nhom7.socialNetworkApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhom7.socialNetworkApp.dto.request.MessageRequest;
import com.nhom7.socialNetworkApp.entity.Message;
import com.nhom7.socialNetworkApp.entity.User;
import com.nhom7.socialNetworkApp.services.IMessageService;
import com.nhom7.socialNetworkApp.services.IUserService;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;

@Controller
@RequestMapping("api/messages/")
public class MessageController {
	private final static String FINAL_DIRECTORY = "/messages";
	@Autowired
    private IMessageService messageService;
	@Autowired
	IUserService userService;
    @Autowired
    MessageSource messageSource;
    @GetMapping("/")
	public String Register(ModelMap model)
	{
    	String username=SecurityContextHolder.getContext().getAuthentication().getName();
    	User user=userService.findByUsername(username).get();
    	model.addAttribute("user",user);
		return "/message";
	}
    @GetMapping("/get/list/{idFriend}&{page}")
    public ResponseEntity<?> getMessagesFromUsers(@PathVariable Long idFriend,
                                                  @PathVariable Integer page,
                                                  Locale locale) {
    	String username=SecurityContextHolder.getContext().getAuthentication().getName();
    	User user=userService.findByUsername(username).get();
    	Long iduser=user.getId();
        Map<String, Object> response = new HashMap<>();
        try {
            List<Message> messages = messageService.findByUsers(iduser, idFriend, page);
            System.out.println(messages.size());
            Long lastIdMessage = messageService.findLastIdMessageFromUsers(iduser, idFriend);
            boolean isLastPage = messageService.getIsLastPage(lastIdMessage, messages);
            response.put("messages", messages);
            response.put("isLastPage", isLastPage);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataAccessException e) {
            response.put("message", messageSource.getMessage("error.database", null, locale));
            response.put("error", e.getMessage() + ": " + e.getMostSpecificCause().getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/post/{receiver_id}")
    public ResponseEntity<?> createMessage(
    		@RequestBody MessageRequest request,
    		@PathVariable("receiver_id") Long receiverId,
          Locale locale) {
        Map<String, Object> response = new HashMap<>();
        System.out.println("send mess");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String username=SecurityContextHolder.getContext().getAuthentication().getName();
           // Message messageConverted = objectMapper.readValue(message, Message.class);
            Message messageConverted= Message.builder()
            		.userReceiver(userService.findById(receiverId).get())
            		.userTransmitter(userService.findByUsername(username).get())
            		.text(request.getMessage())
            		.build();
            System.out.println(request.getMessage());
            messageConverted.setPhoto("testUrl");
//            if (file != null && !file.isEmpty()) {
//                String fileName = uploadFileService.save(file, FINAL_DIRECTORY);
//                messageConverted.setPhoto(fileName);
//            }
            messageConverted = messageService.save(messageConverted);
            response.put("messageId", messageConverted.getId());
            response.put("messageText", messageConverted.getText());
            response.put("messagePhoto", messageConverted.getPhoto());
            response.put("messageDate", messageConverted.getCreatedAt());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
//        } catch (FileNameTooLongException e) {
//            response.put("message", messageSource.getMessage("error.nameTooLong", null, locale));
//            response.put("error", e.getMessage());
//            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            response.put("error", messageSource.getMessage("error.databaseOrFile", null, locale));
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long idMessage, Locale locale) {
        Map<String, Object> response = new HashMap<>();
        try {
            Message message = messageService.findById(idMessage).get();
            //uploadFileService.delete(message.getPhoto(), FINAL_DIRECTORY);
            messageService.deleteById(idMessage);
            response.put("message", messageSource.getMessage("messageController.deleteMessage", null, locale));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", response.put("message", messageSource.getMessage("error.databaseOrFile", null, locale)));
            response.put("error", e.getMessage() + ": " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/img/{fileName:.+}")
//    public ResponseEntity<Resource> viewPhoto(@PathVariable String fileName) {
//        Resource resource = null;
//        HttpHeaders header = null;
//        try {
//            resource = uploadFileService.charge(fileName, FINAL_DIRECTORY);
//            header = new HttpHeaders();
//            header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>(resource, header, HttpStatus.OK);
//    }

}
