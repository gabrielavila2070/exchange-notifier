package com.example.exchangenotifier.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ExchangeService {

    @Autowired
    private WhatsAppNotifierService notifier;

    private String ultimaCotizacion = "";

    public String getEurToArsRate() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maximo\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--no-sandbox");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://www.westernunion.com/fr/fr/web/send-money/start?ReceiveCountry=AR&ISOCurrency=ARS&SendAmount=150&FundsOut=AG&FundsIn=CreditCard");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement montantEnvoyeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("txtSendAmount")));
            montantEnvoyeInput.clear();
            montantEnvoyeInput.sendKeys("150");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("smoExchangeRate")));
            String cotizacionTexto = driver.findElement(By.id("smoExchangeRate")).getText();

            System.out.println("Texto de cotización obtenido: " + cotizacionTexto);

            Pattern pattern = Pattern.compile("= ([0-9,]+(?:\\.[0-9]+)?)\\s+Argentine");
            Matcher matcher = pattern.matcher(cotizacionTexto);

            if (matcher.find()) {
                String cotizacion = matcher.group(1);
                cotizacion = cotizacion.replace(",", ".");
                cotizacion = cotizacion.replaceAll("\\.(?=.*\\.)", ""); // Eliminar puntos adicionales

                System.out.println("Cotización extraída: " + cotizacion);

                // Si es diferente a la última, enviamos alerta
                if (!cotizacion.equals(ultimaCotizacion)) {
                    ultimaCotizacion = cotizacion;
                    notifier.sendAlert(cotizacion); // ✅ Notificación por WhatsApp
                }

                return cotizacion;
            } else {
                return "No se pudo extraer la cotización";
            }

        } catch (Exception e) {
            System.err.println("Error al obtener cotización: " + e.getMessage());
            return "Error al obtener cotización";
        } finally {
            driver.quit();
        }
    }
}
