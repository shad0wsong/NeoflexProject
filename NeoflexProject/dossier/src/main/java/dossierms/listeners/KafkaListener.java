package dossierms.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dossierms.businesslogic.EmailSenderService;
import dossierms.dto.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;


import java.util.logging.Logger;

@Component
public class KafkaListener {

    static Logger log = Logger.getLogger(KafkaListener.class.getName());

    @Autowired
    EmailSenderService emailSenderService;

    @Autowired
    ObjectMapper mapper;

    @org.springframework.kafka.annotation.KafkaListener(topics = "finish-registration", groupId = "finish-reg")
    void Listener(String data) throws JsonProcessingException {
        log.info("Got email message dto");
        EmailMessage emailMessage = mapper.readValue(data, EmailMessage.class);
        emailSenderService.sendEmail(emailMessage.getAddress(), emailMessage.getTheme().toString(),
                "Hello, this is a request to sign docs EmailMessage: " + emailMessage);
        log.info("Sended email message ");
        ;
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "send-ses", groupId = "send-ses")
    void signListener(String data) throws JsonProcessingException {
        log.info("Got email message dto");
        EmailMessage emailMessage = mapper.readValue(data, EmailMessage.class);
        emailSenderService.sendEmail(emailMessage.getAddress(), emailMessage.getTheme().toString(),
                "Hello, this documents are needed to be signed. EmailMessage: " + emailMessage);
        log.info("Sended email message ");

    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "send-documents", groupId = "send-documents")
    void sendListener(String data) throws JsonProcessingException {
        log.info("Got email message dto");
        EmailMessage emailMessage = mapper.readValue(data, EmailMessage.class);
        emailSenderService.sendEmail(emailMessage.getAddress(), emailMessage.getTheme().toString(),
                "Hello, here are the documents: ...... EmailMessage: " + emailMessage);
        log.info("Sended email message ");
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = "create-documents", groupId = "create-documents")
    void createListener(String data) throws JsonProcessingException {
        log.info("Got email message dto");
        EmailMessage emailMessage = mapper.readValue(data, EmailMessage.class);
        emailSenderService.sendEmail(emailMessage.getAddress(), emailMessage.getTheme().toString(),
                "Hello, here are the documents to be created: ...... EmailMessage: " + emailMessage);
        log.info("Sended email message ");
    }
}
