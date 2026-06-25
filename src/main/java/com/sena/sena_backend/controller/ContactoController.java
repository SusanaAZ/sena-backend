package com.sena.sena_backend.controller;

import com.sena.sena_backend.dto.ContactoRequest;
import com.sena.sena_backend.dto.MensajeResponse;
import com.sena.sena_backend.service.ContactoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
public class ContactoController {

    private final ContactoService contactoService;

    public ContactoController(ContactoService contactoService) {
        this.contactoService = contactoService;
    }

    @PostMapping
    public MensajeResponse enviarMensaje(@RequestBody ContactoRequest request) {
        contactoService.enviarMensaje(request);
        return new MensajeResponse("Mensaje enviado correctamente al equipo SENA");
    }
}