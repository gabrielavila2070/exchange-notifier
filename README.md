# 💱 Exchange Notifier - EUR a ARS (Western Union)

Este proyecto es una app personal desarrollada con **Java + Spring Boot** que utiliza **Selenium** para consultar la cotización del Euro (EUR) a Peso Argentino (ARS) desde **Western Union** y, en caso de detectar cambios, envía una alerta por **WhatsApp** utilizando la API de **Twilio**.

---

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.4
- Selenium WebDriver
- Twilio API (WhatsApp)
- WebDriverManager
- Maven

---

## ⚙️ ¿Cómo funciona?

1. La app se ejecuta en segundo plano cada 5 minutos.
2. Extrae el valor actual de cotización de EUR → ARS desde el sitio de Western Union.
3. Si la cotización cambia respecto al último valor conocido, **envía un mensaje por WhatsApp** al número configurado.
4. Se mantiene ejecutándose constantemente (recomendado usar con `cron` o `Docker`).

---

## 🔐 Variables sensibles

Las credenciales de Twilio están en `application.properties`, pero **no están incluidas en este repositorio** (por seguridad).  
Tenés que agregarlas manualmente:

```properties
twilio.account.sid=ACxxxxxxxxxxxxxxxxxxxxxxxxxxxx
twilio.auth.token=your_auth_token
twilio.from=whatsapp:+14155238886
twilio.to=whatsapp:+549XXXXXXXXXX

```
---
## 🛠 Cómo compilar el .jar

Desde la raíz del proyecto:

mvn clean package

El archivo .jar generado estará en la carpeta target/.
## 🧠 Uso sugerido en una PC 24/7

Cloná este repo.

Configurá tus credenciales.

Corré el .jar con:

```
java -jar target/exchangenotifier-0.0.1-SNAPSHOT.jar
```
Usá un cronjob o supervisor para mantener la app siempre viva y/o actualizar el repo automáticamente si cambia.

## 📦 Autor

Diego Avila 
Programador Java Full Stack
Este proyecto fue hecho para uso personal y aprendizaje 💡
