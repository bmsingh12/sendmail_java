package com.nobilis.gsuite.controller;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailTest {
    private static String HOST = "smtp.gmail.com";

  private static String USER = "011bmsingh@gmail.com";
//    private static String USER = "nobilis.ai@nobilisai.com";
    private static String FROM = "011bmsingh@gmail.com";
//    private static String FROM = "nobilis.ai@nobilisai.com";
    private static String PASS = "testpassword";
//    private static String PASS = "qwwzxvwfqudikpdr";

    private static String PORT = "587";
    private static String AUTH = "true";

    public static void main(String[] args) {
        String[] to = {"lockedoutofheaven123@gmail.com"};
        MailTest.sendMessage(to, "Check #", "Email Check!");

    }

    private static void sendMessage(String[] to, String subject, String msg) {

        Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true"); // added this line
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.user", USER);
        props.put("mail.smtp.password", PASS);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        Session session = Session.getDefaultInstance(props, null);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(FROM, "no-reply@nobilisai.com"));

            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for (InternetAddress toAddres : toAddress) {
                message.addRecipient(Message.RecipientType.TO, toAddres);
            }

            message.setSubject(subject);
            message.setText(msg);

            Transport transport = session.getTransport("smtps");
            transport.connect(HOST, USER, PASS);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
