package com.felipysantsss.telegram_amara_bot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
public class MercadoPagoWebhookController {

    @PostMapping("/mercadopago")
    @ResponseStatus(HttpStatus.OK)
    public void receiveNotification(@RequestBody String body){
        System.out.println(body);
    }
}
