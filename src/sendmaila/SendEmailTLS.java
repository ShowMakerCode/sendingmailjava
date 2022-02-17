/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendmaila;

/**
 *
 * @author 98tae
 */
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendEmailTLS {

    public static int i;

    public static void main(String[] args) {
        sending();

    }

    public static void sending() {
        int codeSMS = (int) (Math.random() * (999999 - 000000 + 1) + 000000); // random
        i = codeSMS;
        try {
            Infile(String.valueOf(i));
        } catch (IOException ex) {
           ex.printStackTrace();
        }
        final String username = "sendmail.fpttext@gmail.com"; // tài khoản mật khẩu của gmail gửi
        final String password = "sendmail.fpttext10";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendmail.fpttext@gmail.com"));// tiêu đề tài khoản 
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("98taeyang@gmail.com") // gmail nhận thư
            );
            message.setSubject("Orange Teams");
            message.setText("Chao " + "Dong \n"
                    + "That is your code : " + codeSMS);

            Transport.send(message);

            System.out.println("Thành Công ");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void Infile(String txtcode) throws IOException { // lưu mã code vào file
        FileWriter bis = new FileWriter(new File("outtxt.txt"));
        bis.write(txtcode);
        bis.close();

    }

}
