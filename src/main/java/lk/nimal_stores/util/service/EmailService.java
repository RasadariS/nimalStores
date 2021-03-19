package lk.nimal_stores.util.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;
    // to access application properties entered details
    private final Environment environment;

    @Autowired
    public EmailService(JavaMailSender javaMailSender, Environment environment) {
        this.javaMailSender = javaMailSender;
        this.environment = environment;
    }


    public void sendEmail(String receiverEmail, String subject, String message) throws
            MailException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();


        try {
            mailMessage.setTo(receiverEmail);
            mailMessage.setFrom("-(Samarasinghe Super - Kadawatha - (not reply))");
            mailMessage.setSubject(subject);
            mailMessage.setText(message);

            javaMailSender.send(mailMessage);
        } catch ( Exception e ) {
            System.out.println("Email Exception " + e.toString());
        }
    }

}
