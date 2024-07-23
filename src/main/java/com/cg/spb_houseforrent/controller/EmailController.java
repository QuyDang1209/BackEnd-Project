package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.EmailDetails;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.time.temporal.ChronoUnit;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/send-email")
public class EmailController {
    @Autowired
    private JavaMailSender javaMailSender;
    @PostMapping("to-customer")
    public String sendEmailCustomer(@RequestBody EmailDetails emailDetails) {
        String htmlContent = "<h1>XÁC NHẬN ĐẶT THUÊ NHÀ %s</h1>\n" +
                "<p>Địa chỉ: %s </p> \n" +
                "<p>SĐT chủ nhà: %s</p> \n" +
                "<p>Tên khách hàng: %s</p> \n" +
                "<p>Check in: %s</p>\n" +
                "<p>Check out: %s</p>\n" +
                "<p>Giá thuê: %s</p>\n" +
                "<p>Khách đã cọc: %s</p>\n" +
                "<p>Số còn lại: %s</p>\n" +
                "<p>Giờ check in: 14h00</p>\n" +
                "<p>Giờ check out: 11h00</p>";
        String message = htmlContent.formatted(
                emailDetails.getForrent().getNamehouse(),
                emailDetails.getForrent().getAddress(),
                emailDetails.getUser().getPhone(),
                emailDetails.getUser().getName(),
                emailDetails.getBooking().getOrderday(),
                emailDetails.getBooking().getPayday(),
                ChronoUnit.DAYS.between(emailDetails.getBooking().getOrderday(),emailDetails.getBooking().getPayday()) * emailDetails.getForrent().getRentingprice(),
                emailDetails.getBooking().getDeposite(),
                ChronoUnit.DAYS.between(emailDetails.getBooking().getOrderday(),emailDetails.getBooking().getPayday()) * emailDetails.getForrent().getRentingprice() -emailDetails.getBooking().getDeposite()
        );
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
        try {
            messageHelper.setTo(emailDetails.getRecipient());
            messageHelper.setSubject(emailDetails.getSubject());
            messageHelper.setText(message, true); // true indicates HTML content
            javaMailSender.send(mimeMessage);
            return "Email sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email";
        }
    }
//    @PostMapping("to-user")
//    public String sendEmailUser(){
//        String htmlContent = "<h1>XÁC NHẬN ĐẶT THUÊ NHÀ %s</h1>\n" +
//                "<p>Địa chỉ: %s </p> \n" +
//                "<p>SĐT chủ nhà: %s</p> \n" +
//                "<p>Tên khách hàng: %s</p> \n" +
//                "<p>Check in: %s</p>\n" +
//                "<p>Check out: %s</p>\n" +
//                "<p>Giá thuê: %s</p>\n" +
//                "<p>Khách đã cọc: %s</p>\n" +
//                "<p>Số còn lại: %s</p>\n" +
//                "<p>Giờ check in: 14h00</p>\n" +
//                "<p>Giờ check out: 11h00</p>";
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
//        try {
//            messageHelper.setTo(emailDetails.getRecipient());
//            messageHelper.setSubject("");
//            messageHelper.setText(message, true); // true indicates HTML content
//            javaMailSender.send(mimeMessage);
//            return "Email sent successfully";
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Failed to send email";
//        }
//    }
}
