package com.example.exchangenotifier.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WhatsAppNotifierService {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.from}")
    private String FROM;

    @Value("${twilio.to}")
    private String TO;

    public void sendAlert(String rate) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + TO),
                new com.twilio.type.PhoneNumber("whatsapp:" + FROM),
                "💱 Cotización actual EUR -> ARS: " + rate
        ).create();

        System.out.println("Mensaje enviado por WhatsApp: " + message.getSid());
    }
}
