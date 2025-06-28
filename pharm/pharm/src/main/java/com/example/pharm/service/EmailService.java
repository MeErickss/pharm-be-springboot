package com.example.pharm.service;

import com.example.pharm.model.Usuario;
import com.example.pharm.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final UsuarioRepository usuarioRepository;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public EmailService(JavaMailSender mailSender, UsuarioRepository usuarioRepository) {
        this.mailSender = mailSender;
        this.usuarioRepository = usuarioRepository;
    }

    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAddress);
        msg.setTo("erickalair18@gmail.com");
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
    }
}
