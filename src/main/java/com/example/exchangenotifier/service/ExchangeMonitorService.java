package com.example.exchangenotifier.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ExchangeMonitorService {

    private final ExchangeService exchangeService;
    private double lastRate = 0.0;

    public ExchangeMonitorService(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @Scheduled(fixedRate = 300000) // Cada 5 minutos
    public void checkExchangeRate() {
        try {
            double currentRate = Double.parseDouble(exchangeService.getEurToArsRate());

            if (currentRate != lastRate) {
                System.out.println("🚨 Cotización actualizada: " + currentRate + " (anterior: " + lastRate + ")");
                // Acá en el futuro llamaremos a WhatsApp
                lastRate = currentRate;
            } else {
                System.out.println("✅ Sin cambios. Valor actual: " + currentRate);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener cotización: " + e.getMessage());
        }
    }
}
