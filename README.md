# Sistema de Gestión de Envíos - LogiExpress (Quiz 4)

## Tema 1: Justificación de la Arquitectura Desacoplada
El sistema **LogiExpress** ha sido diseñado bajo un modelo de **arquitectura limpia desacoplada cliente-servidor** orientada a servicios RESTful. 
Este desacoplamiento separa por completo la capa de persistencia y lógica de negocio (Back-End) de los clientes de consumo (Front-End web SPA o aplicaciones móviles). La API expone endpoints seguros bajo contratos claros (DTOs y respuestas estandarizadas RFC 7807), permitiendo que cualquier cliente futuro consuma los servicios de forma independiente, garantizando escalabilidad, mantenibilidad y robustez en la infraestructura de la empresa.

## 🛠️ Tecnologías Utilizadas
* **Java 21**[cite: 1]
* **Spring Boot 3.2.x** (Web, Data JPA, Security, Validation)[cite: 1]
* **H2 Database** (Persistencia relacional en memoria)[cite: 1]
* **JUnit 5 & Mockito** (Pruebas unitarias automatizadas)[cite: 1]
* **OpenAPI 3.0 / Swagger UI** (Documentación de API)[cite: 1]

## Instrucciones de Ejecución
1. Clona el repositorio y asegúrate de estar posicionado en la rama principal `main`[cite: 1].
2. Compila y ejecuta el proyecto utilizando Maven desde la terminal:
   ```bash
   mvn clean spring-boot:run