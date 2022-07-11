package dossierms.businesslogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String email, String theme, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("danneoflextest@gmail.com");
        message.setTo(email);
        message.setText(body);
        message.setSubject(theme);
        javaMailSender.send(message);

    }
}
