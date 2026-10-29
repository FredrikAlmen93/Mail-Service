# Mail-Service

The mail service is part of the webshop backend responsible for sending email notifications. It consumes messages from RabbitMQ and sends emails through an SMTP server, allowing other microservices to trigger email notifications without handling email delivery themselves.

---

## Contents

- [Responsibilities](#responsibilities)
- [System overview](#system-overview)
- [Technologies](#technologies)
- [Environment variables](#environment-variables)
- [Running the application locally](#running-the-application-locally)
- [Dependency](#dependency)
- [Documentation](#documentation)
- [Docker and CI](#docker-and-ci)
- [Related repositories](#related-repositories)

---

## Responsibilities

The mail service is responsible for:
1. **Receiving messages.** Consuming email messages from a RabbitMQ queue.
2. **Sending emails.** Using Spring Boot Mail and SMTP to deliver email notifications.
3. **Asynchronous communication.** Allowing other microservices to publish messages without sending emails directly.
4. **Message conversion.** Using Jackson and the shared Messenger library to work with messages exchanged through RabbitMQ.
5. **External configuration.** Reading RabbitMQ and SMTP connection settings from environment variables.

The mail service does not handle orders or products itself. Those responsibilities belong to the corresponding microservices.

---

## System overview

```text
+--------------------+
|    Order service   |
+---------+----------+
          |
          | Publishes email message
          v
+--------------------+
|      RabbitMQ      |
|       Queue        |
+---------+----------+
          |
          | Consumes message
          v
+--------------------+
|    Mail service    |
|      :8085         |
+---------+----------+
          |
          | SMTP
          v
+--------------------+
|   Email recipient  |
+--------------------+
```
The Order-Service publishes messages to RabbitMQ when an email notification needs to be sent.
Mail-Service consumes those messages and handles email delivery through the configured SMTP server.

This separates email delivery from order processing and reduces dependencies between the services.

---

## Technologies
- **Language:** Java 17
- **Framework:** Spring Boot 4.0.6
- **Build tool:** Maven (Maven wrapper:mvnw/mvnw.cmd)
- **Messaging:** RabbitMQ, Spring AMQP
- **Email Delivery:** Spring Boot Mail, SMTP
- **Message conversion:** Jackson
- **Shared library:** Messenger
- **API documentation:** Springdoc OpenAPI
- **Boilerplate reduction:** Lombok
- **Container:** Docker
- **CI:** Github Actions

---

## Environment variables

The application uses environment variables to configure RabbitMQ and SMTP connections.

| Variable | Default | Required | Description >
|---|---|---|---|
|`RABBITMQ_HOST`| none | **Yes** | RabbitMQ server hostname |
|`RABBITMQ_PORT`| none | **Yes** | RabbitMQ connection port |
|`RABBITMQ_USERNAME`| none | **Yes** | RabbitMQ username |
|`RABBITMQ_PASSWORD`| none | **Yes** | RabbitMQ password |
|`RABBITMQ_VHOST`| none | **Yes** | RabbitMQ virtual host |
|`QUEUE_NAME`| none | **Yes** | Name of the RabbitMQ queue consumed by the service |
|`MAIL_HOST`| none | **Yes** | SMTP server hostname |
|`MAIL_PORT`| none | **Yes** | SMTP server port |
|`MAIL_USERNAME`| none | **Yes** | SMTP account username |
|`MAIL_PASSWORD`| none | **Yes** | SMTP account password |
|`PORT`| 8085 | **No** | Port the application listens on |

---

## Running the application locally

Ensure you have Java 17 (or later) installed.
### Start application

Clone the repository:
```
git clone https://github.com/FredrikAlmen93/Mail-Service.git
cd Mail-Service
```

Set the required environment variables, then run the application from the project root.

Windows (PowerShell):

```powershell
$env:RABBITMQ_HOST="your_rabbitmq_host"
$env:RABBITMQ_PORT="your_rabbitmq_port"
$env:RABBITMQ_USERNAME="your_rabbitmq_username"
$env:RABBITMQ_PASSWORD="your_rabbitmq_password"
$env:RABBITMQ_VHOST="your_rabbitmq_vhost"
$env:QUEUE_NAME="confirmation-queue"

$env:MAIL_HOST="your_smtp_host"
$env:MAIL_PORT="your_smtp_port"
$env:MAIL_USERNAME="your_email_username"
$env:MAIL_PASSWORD_"your_email_password"

mvnw.cmd spring-boot:run
```
macOS and Linux:

```bash
export RABBITMQ_HOST="your_rabbitmq_host"
export RABBITMQ_PORT="your_rabbitmq_port"
export RABBITMQ_USERNAME="your_rabbitmq_username"
export RABBITMQ_PASSWORD="your_rabbitmq_password"
export RABBITMQ_VHOST="your_rabbitmq_vhost"
export QUEUE_NAME="confirmation-queue"

export MAIL_HOST="your_smtp_host"
export MAIL_PORT="your_smtp_port"
export MAIL_USERNAME="your_email-username"
export MAIL_PASSWORD="your_email_password"

./mvnw spring-boot:run
```

---

## Dependency

The project uses the shared **messenger** library, which is hosted on Github Packages.

Make sure you have the required Maven repository credentials and configuration before building the project.
Do not commit access tokens to the repository.

---

## Documentation

Mail-Service is primarily a message-driven microservice. Its main responsibility is consuming
RabbitMQ messages and sending emails rather than exposing product or order management endpoint.

The project includes Springdoc OpenAPI for API documentation. If the application exposes its OpenAPI UI, it can be accessed at:

http://localhost:8085/swagger-ui.html

---

## Docker and CI

Build the application:

 ```bash
 .\msnw.cmd clean package
```

Build the Docker image:

```bash
docker build -t mail-service .
```

---

## Related repositories

- [Auth Service](https://github.com/OP-Devloop/Micro-Project-Auth-Server)
- [Product Service](https://github.com/L2025S/micro-project-product-service)
- [Order Service](https://github.com/Yunus-CanYucel/Micro-ProjectOrder-Service)
- [Messenger](https://github.com/FredrikAlmen93/messenger)
- [Frontend](https://github.com/L2025S/webshop-frontend)
- [Gateway](https://github.com/L2025S/webshop-gateway)
