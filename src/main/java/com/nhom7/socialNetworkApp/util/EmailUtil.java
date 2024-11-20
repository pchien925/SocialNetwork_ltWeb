package com.nhom7.socialNetworkApp.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

  @Autowired
  private JavaMailSender javaMailSender;

  public void sendVerifyOtpEmail(String email, String otp) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Verify OTP");
    mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:8888/verify-account?email=%s&otp=%s" target="_blank">click link to verify</a>
        </div>
        """.formatted(email, otp), true);

    javaMailSender.send(mimeMessage);
  }
  public void sendOtpEmail(String email, String otp) throws MessagingException {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
	    mimeMessageHelper.setTo(email);
	    mimeMessageHelper.setSubject("Verify OTP");
	    String message="Your OTP is:"+otp;
	    mimeMessageHelper.setText(message.formatted(email, otp), true);

	    javaMailSender.send(mimeMessage);
	  }
}