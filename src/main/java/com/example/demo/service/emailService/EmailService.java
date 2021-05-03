package com.example.demo.service.emailService;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EmailService implements EmailServiceInterface{
    @Autowired
    public JavaMailSender javaMailSender;



    @Async
    public void sendMessageAboutManufacture(Manufacture manufacture) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("nikolaevdanil2000@gmail.com");
        simpleMailMessage.setSubject("Added manufacture");
        simpleMailMessage.setText("Added manufacture: name - " +
                manufacture.getName() + " address - " +
                manufacture.getAddress());
        javaMailSender.send(simpleMailMessage);

    }

    @Async
    public void sendMessageAboutWorker(Worker worker) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("nikolaevdanil2000@gmail.com");
        simpleMailMessage.setSubject("Added worker");
        simpleMailMessage.setText("Added worker: firstName - " +
                worker.getFirstName() + " lastName - " +
                worker.getLastName()+" middleName - " +
                worker.getMiddleName());
        javaMailSender.send(simpleMailMessage);

    }
}
