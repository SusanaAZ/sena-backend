package com.sena.sena_backend.service;

import com.sena.sena_backend.dto.ContactoRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ContactoService {

    private final JavaMailSender mailSender;

    @Value("${sena.contacto.destino}")
    private String correoDestino;

    @Value("${spring.mail.username}")
    private String correoRemitente;

    public ContactoService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarMensaje(ContactoRequest request) {
        if (!StringUtils.hasText(request.nombre())
                || !StringUtils.hasText(request.correo())
                || !StringUtils.hasText(request.mensaje())) {
            throw new RuntimeException("Completa todos los campos");
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(correoRemitente);
        mail.setTo(correoDestino);
        mail.setReplyTo(request.correo());
        mail.setSubject("Nuevo mensaje de soporte SENA");
        mail.setText(
                "Nombre: " + request.nombre() +
                        "\nCorreo: " + request.correo() +
                        "\n\nMensaje:\n" + request.mensaje()
        );

        mailSender.send(mail);
    }
}