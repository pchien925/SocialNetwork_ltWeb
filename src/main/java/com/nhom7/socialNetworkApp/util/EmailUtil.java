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
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Verify Your Account</title>
            </head>
            <body>
                <div style="font-family: Arial, sans-serif; text-align: center; padding: 20px; background-color: #f9f9f9; border: 1px solid #ddd; border-radius: 10px; width: 400px; margin: 0 auto;">
                    <h2 style="color: #333;">Verify Your Account</h2>
                    <p style="font-size: 16px; color: #666;">
                        Welcome to ALOUTE! Please click the button below to complete your account verification:
                    </p>
                    <a href="http://localhost:8888/user/verify-account?email=%s&otp=%s" target="_blank"\s
                        style="display: inline-block; text-decoration: none; padding: 10px 20px; font-size: 16px; color: #fff; background-color: #007bff; border-radius: 5px; margin-top: 10px; transition: background-color 0.3s;">
                        Verify Account
                    </a>
                    <p style="font-size: 14px; color: #999; margin-top: 20px;">
                        If you did not request this, please ignore this email.
                    </p>
                </div>
            </body>
            </html>
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