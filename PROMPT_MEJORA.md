# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Boilerplate del stack que falta

Sin esto no compila ni arranca. Es andamiaje, no toca nada de lo pedagogico:

- **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/loansapi/repository/LoanRepository.java` — `LoanStatus`: LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- `src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java` — `LoanStatus`: LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- `src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java` — `LoanStatus`: LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanStatus`: LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- `src/main/java/com/fintech/loansapi/service/LoanService.java` — `Loan`: El import com.fintech.loansapi.model.entity.Loan no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- `src/test/java/com/fintech/loansapi/controller/LoanControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findAll`: Se invoca `findAll` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findById`: Se invoca `findById` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.create`: Se invoca `create` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.update`: Se invoca `update` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.delete`: Se invoca `delete` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByStatus`: Se invoca `findByStatus` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByAmountBetween`: Se invoca `findByAmountBetween` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByClientId`: Se invoca `findByClientId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loansapi/service/LoanServiceImpl.java` — `LoanRequest.clientId`: Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.size`: Se invoca `size` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.get`: Se invoca `get` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.isPresent`: Se invoca `isPresent` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.amount`: Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.interestRate`: Se invoca `interestRate` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.durationMonths`: Se invoca `durationMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.clientId`: Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.description`: Se invoca `description` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.id`: Se invoca `id` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.amount`: Se invoca `amount` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.status`: Se invoca `status` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Contexto técnico original
Crear una API REST con persistencia en H2 y documentación con Swagger

### Reto
- Tema: Java Spring Boot
- Seniority: junior-l2
- Tipo: practical
- Título: Desarrollo de API REST con persistencia en H2 y documentación Swagger
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de la estructura de la API — objetivo: Definir la estructura básica de la API REST y establecer las rutas necesarias para las operaciones CRUD de préstamos. — entregable (NO resolver): Especificación de las rutas y métodos HTTP para las operaciones CRUD de préstamos.
- Fase 2: Implementación de la persistencia en H2 — objetivo: Implementar la persistencia de los préstamos en una base de datos H2 y asegurar que la API pueda crear, leer, actualizar y eliminar préstamos. — entregable (NO resolver): Implementación de la persistencia de los préstamos en H2 y métodos de servicio para las operaciones CRUD.
- Fase 3: Documentación con Swagger — objetivo: Documentar la API utilizando Swagger para facilitar la comprensión y uso por parte de los desarrolladores externos. — entregable (NO resolver): Documentación completa de la API utilizando Swagger.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.fintech</groupId>
    <artifactId>loans-api</artifactId>
    <version>1.0.0</version>
    <name>loans-api</name>
    <description>API REST para gestión de préstamos - Fintech</description>
    
    <properties>
        <java.version>21</java.version>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <springdoc.version>2.5.0</springdoc.version>
        <lombok.version>1.18.32</lombok.version>
        <mockito.version>5.12.0</mockito.version>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Web -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <!-- Spring Boot Data JPA -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        
        <!-- Base de datos H2 en memoria -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Documentación OpenAPI/Swagger -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc.version}</version>
        </dependency>
        
        <!-- Lombok para reducción de boilerplate -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- Validación de datos con Jakarta -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Dependencias de testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>${mockito.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.13.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <compilerArgs>
                        <arg>--enable-preview</arg>
                    </compilerArgs>
                </configuration>
            </plugin>
        </plugins>
    </build>
    
</project>

// === ARCHIVO: src/main/java/com/fintech/loansapi/LoansApiApplication.java ===
package com.fintech.loansapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Punto de entrada principal de la aplicación Loans API.
 * 
 * Esta clase inicia el contexto de Spring Boot y configura todos los
 * componentes necesarios para el funcionamiento de la API REST de préstamos.
 * La anotación @SpringBootApplication combina tres anotaciones principales:
 * - @Configuration: marca la clase como fuente de definiciones de beans
 * - @EnableAutoConfiguration: habilita la configuración automática de Spring
 * - @ComponentScan: permite el escaneo de componentes en el paquete actual
 * 
 * El proyecto sigue una arquitectura de capas estándar:
 * - Controller: capa de presentación que maneja las peticiones HTTP
 * - Service: capa de lógica de negocio que contiene las reglas del dominio
 * - Repository: capa de acceso a datos que interactúa con la base de datos
 * 
 * La base de datos H2 se configura en modo desarrollo para facilitar
 * las pruebas y el desarrollo sin necesidad de una base de datos externa.
 */
@SpringBootApplication
public class LoansApiApplication {
    
    private static final Logger logger = LoggerFactory.getLogger(LoansApiApplication.class);
    
    public static void main(String[] args) {
        logger.info("Iniciando la aplicación de Gestión de Préstamos...");
        SpringApplication.run(LoansApiApplication.class, args);
        logger.info("Aplicación iniciada correctamente. Swagger disponible en /swagger-ui.html");
    }
    
    /**
     * Bean de inicialización que se ejecuta al arranque de la aplicación.
     * Se utiliza para mostrar información de configuración y realizar
     * verificaciones iniciales del sistema.
     */
    @Bean
    public CommandLineRunner init() {
        return args -> {
            logger.info("=== Sistema de Gestión de Préstamos ===");
            logger.info("Versión de Java: {}", System.getProperty("java.version"));
            logger.info("Perfil activo: {}", System.getProperty("spring.profiles.active", "default"));
            logger.info("Puerto del servidor: {}", System.getProperty("server.port", "8080"));
            logger.info("Base de datos H2 inicializada en memoria");
            logger.info("Documentación Swagger disponible en: /swagger-ui.html");
            logger.info("Documentación OpenAPI JSON disponible en: /v3/api-docs");
        };
    }
}

// === ARCHIVO: src/main/resources/application.properties ===
# Configuración del servidor embebido
server.port=8080
server.servlet.context-path=/api

# Configuración de la fuente de datos H2
spring.datasource.url=jdbc:h2:mem:loansdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración del pool de conexiones
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.idle-timeout=600000
spring.datasource.hikari.max-lifetime=1800000

# Configuración de JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.use_sql_comments=false
spring.jpa.properties.hibernate.jdbc.batch_size=20
spring.jpa.properties.hibernate.order_inserts=true
spring.jpa.properties.hibernate.order_updates=true

# Configuración de la consola H2 (solo para desarrollo)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.web-allow-others=true

# Configuración de OpenAPI/Swagger
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true
springdoc.api-docs.enabled=true
springdoc.swagger-ui.operationsSorter=method
springdoc.swagger-ui.tagsSorter=alpha
springdoc.swagger-ui.tryItOutEnabled=true

# Configuración de validaciones
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false

# Configuración de logging
logging.level.root=INFO
logging.level.com.fintech.loansapi=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.springframework.security=DEBUG
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
logging.level.org.hibernate.stat=DEBUG

# Configuración de internacionalización
spring.messages.basename=messages
spring.messages.encoding=UTF-8

# Configuración de compresión de respuestas
server.compression.enabled=true
server.compression.mime-types=text/html,text/xml,text/plain,text/css,text/javascript,application/javascript,application/json
server.compression.min-response-size=1024

# Configuración de timeouts
server.servlet.session.timeout=30m
server.servlet.session.cookie.http-only=true
server.servlet.session.cookie.secure=false


// === ARCHIVO: src/main/java/com/fintech/loansapi/repository/LoanRepository.java ===
package com.fintech.loansapi.repository;

import com.fintech.loansapi.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de persistencia de préstamos.
 * Extiende JpaRepository para heredar operaciones CRUD básicas.
 * Proporciona métodos de consulta personalizados para el dominio de préstamos.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    /**
     * Busca un préstamo por su identificador único.
     * @param id identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> findById(Long id);

    /**
     * Recupera todos los préstamos ordenados por fecha de creación descendente.
     * @return lista de préstamos
     */
    List<Loan> findAllByOrderByCreatedAtDesc();

    /**
     * Busca préstamos por estado.
     * @param estado estado del préstamo (PENDING, APPROVED, REJECTED)
     * @return lista de préstamos con el estado especificado
     */
    List<Loan> findByStatus(Loan.LoanStatus estado);

    /**
     * Busca préstamos por rango de monto.
     * @param montoMin monto mínimo
     * @param montoMax monto máximo
     * @return lista de préstamos dentro del rango
     */
    List<Loan> findByAmountBetween(BigDecimal montoMin, BigDecimal montoMax);

    /**
     * Busca préstamos por ID de cliente.
     * @param clientId identificador del cliente
     * @return lista de préstamos del cliente
     */
    List<Loan> findByClientId(Long clientId);

    /**
     * Consulta JPQL para obtener préstamos aprobados con monto mayor al especificado.
     * @param montoMin monto mínimo
     * @return lista de préstamos aprobados que superan el monto
     */
    @Query("SELECT l FROM Loan l WHERE l.status = 'APPROVED' AND l.amount >= :montoMin")
    List<Loan> findApprovedLoansWithMinAmount(@Param("montoMin") BigDecimal montoMin);

    /**
     * Consulta nativa para contar préstamos por estado.
     * @param status estado del préstamo
     * @return cantidad de préstamos en ese estado
     */
    @Query(value = "SELECT COUNT(*) FROM loans WHERE status = :status", nativeQuery = true)
    Long countByStatus(@Param("status") String status);

    /**
     * Busca préstamos creados después de una fecha específica.
     * @param fecha fecha límite
     * @return lista de préstamos posteriores a la fecha
     */
    List<Loan> findByCreatedAtAfter(LocalDate fecha);

    /**
     * Verifica si existe un préstamo con el ID proporcionado.
     * @param id identificador
     * @return true si existe
     */
    boolean existsById(Long id);

    /**
     * Elimina un préstamo por su ID.
     * @param id identificador del préstamo a eliminar
     */
    void deleteById(Long id);
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/entity/Loan.java ===
package com.fintech.loansapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un préstamo en el sistema.
 * Mapea la tabla "loans" de la base de datos H2.
 * Los atributos incluyen: monto, tasa de interés, duración, estado y cliente.
 */
@Entity
@Table(name = "loans")
public class Loan {

    /**
     * Enumeración de estados posibles de un préstamo.
     */
    public enum LoanStatus {
        PENDING("Pendiente"),
        APPROVED("Aprobado"),
        REJECTED("Rechazado"),
        ACTIVE("Activo"),
        PAID("Pagado"),
        DEFAULTED("Incumplido");

        private final String descripcion;

        LoanStatus(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
    @DecimalMax(value = "100.0", message = "La tasa de interés no puede exceder el 100%")
    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima es 1 mes")
    @Max(value = 360, message = "La duración máxima es 360 meses")
    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del préstamo es obligatorio")
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status = LoanStatus.PENDING;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Loan() {
    }

    /**
     * Constructor con parámetros para crear un préstamo.
     */
    public Loan(BigDecimal amount, BigDecimal interestRate, Integer durationMonths, Long clientId) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.clientId = clientId;
        this.status = LoanStatus.PENDING;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Calcula el monto total a pagar con intereses.
     * @return monto total con intereses
     */
    public BigDecimal calculateTotalAmount() {
        if (amount == null || interestRate == null) {
            return amount;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual aproximada.
     * @return cuota mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total == null || durationMonths == null || durationMonths == 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Aprueba el préstamo estableciendo la fecha de inicio.
     */
    public void approve() {
        this.status = LoanStatus.APPROVED;
        this.startDate = LocalDate.now();
        if (this.durationMonths != null) {
            this.endDate = this.startDate.plusMonths(this.durationMonths);
        }
    }

    /**
     * Rechaza el préstamo.
     */
    public void reject() {
        this.status = LoanStatus.REJECTED;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", durationMonths=" + durationMonths +
                ", status=" + status +
                ", clientId=" + clientId +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;
import jakarta.validation.constraints.*;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para la creación y actualización de préstamos.
 * Utiliza Jakarta Validation para validar los datos de entrada.
 * Se mapea entre la entidad Loan y las solicitudes HTTP.
 *
 * Este record proporciona inmutabilidad y reduce boilerplate en Java 21.
 */
public record LoanRequest(

        @NotNull(message = "El monto del préstamo es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        @DecimalMax(value = "10000000.0", message = "El monto máximo es 10,000,000")
        BigDecimal amount,

        @NotNull(message = "La tasa de interés es obligatoria")
        @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
        @DecimalMax(value = "100.0", message = "La tasa de interés no puede exceder el 100%")
        BigDecimal interestRate,

        @NotNull(message = "La duración es obligatoria")
        @Min(value = 1, message = "La duración mínima es 1 mes")
        @Max(value = 360, message = "La duración máxima es 360 meses")
        Integer durationMonths,

        @NotNull(message = "El ID del cliente es obligatorio")
        @Min(value = 1, message = "El ID del cliente debe ser válido")
        Long clientId,

        @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
        String description,

        @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Estado inválido")
        String status
) {

    /**
     * Convierte este DTO en una entidad Loan.
     * @return nueva instancia de Loan con los datos del DTO
     */
    public Loan toEntity() {
        Loan loan = new Loan(this.amount, this.interestRate, this.durationMonths, this.clientId);
        loan.setDescription(this.description);

        if (this.status != null) {
            loan.setStatus(Loan.LoanStatus.valueOf(this.status));
        }

        return loan;
    }

    /**
     * Actualiza una entidad Loan existente con los datos del DTO.
     * @param loan entidad a actualizar
     */
    public void updateEntity(Loan loan) {
        if (this.amount != null) {
            loan.setAmount(this.amount);
        }
        if (this.interestRate != null) {
            loan.setInterestRate(this.interestRate);
        }
        if (this.durationMonths != null) {
            loan.setDurationMonths(this.durationMonths);
        }
        if (this.clientId != null) {
            loan.setClientId(this.clientId);
        }
        if (this.description != null) {
            loan.setDescription(this.description);
        }
        if (this.status != null && !this.status.isEmpty()) {
            loan.setStatus(Loan.LoanStatus.valueOf(this.status));
        }
    }

    /**
     * Crea un LoanRequest desde una entidad Loan.
     * @param loan entidad origen
     * @return nuevo LoanRequest con los datos de la entidad
     */
    public static LoanRequest fromEntity(Loan loan) {
        return new LoanRequest(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getDurationMonths(),
                loan.getClientId(),
                loan.getDescription(),
                loan.getStatus() != null ? loan.getStatus().name() : null
        );
    }

    /**
     * Valida que los campos numéricos sean coherentes.
     * @return true si los datos son coherentes
     */
    public boolean isValid() {
        if (amount == null || interestRate == null || durationMonths == null || clientId == null) {
            return false;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (interestRate.compareTo(BigDecimal.ZERO) < 0 || interestRate.compareTo(BigDecimal.valueOf(100)) > 0) {
            return false;
        }

        if (durationMonths < 1 || durationMonths > 360) {
            return false;
        }

        return clientId > 0;
    }

    /**
     * Calcula el monto total estimado con intereses.
     * @return monto total
     */
    public BigDecimal calculateTotalAmount() {
        if (!isValid()) {
            return BigDecimal.ZERO;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual estimada.
     * @return cuota mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total.compareTo(BigDecimal.ZERO) == 0 || durationMonths == null) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta para operaciones de préstamos")
public record LoanResponse(
    @Schema(description = "ID único del préstamo", example = "1")
    Long id,
    
    @Schema(description = "Monto del préstamo", example = "10000.00")
    BigDecimal amount,
    
    @Schema(description = "Tasa de interés anual", example = "0.12")
    BigDecimal interestRate,
    
    @Schema(description = "Duración en meses", example = "12")
    Integer durationMonths,
    
    @Schema(description = "Estado del préstamo", example = "APPROVED")
    String status,
    
    @Schema(description = "ID del cliente", example = "100")
    Long clientId,
    
    @Schema(description = "Descripción del préstamo", example = "Préstamo personal para consolidación de deudas")
    String description,
    
    @Schema(description = "Fecha de inicio del préstamo")
    LocalDate startDate,
    
    @Schema(description = "Fecha de fin del préstamo")
    LocalDate endDate,
    
    @Schema(description = "Monto total a pagar incluyendo intereses")
    BigDecimal totalAmount,
    
    @Schema(description = "Pago mensual estimado")
    BigDecimal monthlyPayment,
    
    @Schema(description = "Fecha de creación del registro")
    LocalDateTime createdAt,
    
    @Schema(description = "Fecha de última actualización")
    LocalDateTime updatedAt
) {
    public static LoanResponse fromEntity(Loan loan) {
        if (loan == null) {
            return null;
        }
        return new LoanResponse(
            loan.getId(),
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getStatus() != null ? loan.getStatus().name() : null,
            loan.getClientId(),
            loan.getDescription(),
            loan.getStartDate(),
            loan.getEndDate(),
            loan.calculateTotalAmount(),
            loan.calculateMonthlyPayment(),
            loan.getCreatedAt(),
            loan.getUpdatedAt()
        );
    }

    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setId(this.id);
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        if (this.status != null) {
            loan.setStatus(Loan.LoanStatus.valueOf(this.status));
        }
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStartDate(this.startDate);
        loan.setEndDate(this.endDate);
        loan.setCreatedAt(this.createdAt);
        loan.setUpdatedAt(this.updatedAt);
        return loan;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/controller/LoanController.java ===
package com.fintech.loansapi.controller;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.service.LoanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Gestión de Préstamos", description = "API para operaciones CRUD de préstamos")
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(summary = "Obtener todos los préstamos", description = "Retorna una lista de todos los préstamos registrados en el sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<LoanResponse>> getAllLoans() {
        List<LoanResponse> loans = loanService.findAll();
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Obtener préstamo por ID", description = "Retorna un préstamo específico basado en su identificador único")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "ID de préstamo inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<LoanResponse> getLoanById(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id) {
        LoanResponse loan = loanService.findById(id);
        return ResponseEntity.ok(loan);
    }

    @Operation(summary = "Crear nuevo préstamo", description = "Registra un nuevo préstamo en el sistema con los datos proporcionados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de préstamo inválidos"),
        @ApiResponse(responseCode = "422", description = "Error de validación de datos")
    })
    @PostMapping
    public ResponseEntity<LoanResponse> createLoan(
            @Parameter(description = "Datos del préstamo a crear", required = true)
            @Valid @RequestBody LoanRequest request) {
        LoanResponse createdLoan = loanService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }

    @Operation(summary = "Actualizar préstamo existente", description = "Actualiza los datos de un préstamo existente basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamo actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "Datos de préstamo inválidos"),
        @ApiResponse(responseCode = "422", description = "Error de validación de datos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<LoanResponse> updateLoan(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Datos actualizados del préstamo", required = true)
            @Valid @RequestBody LoanRequest request) {
        LoanResponse updatedLoan = loanService.update(id, request);
        return ResponseEntity.ok(updatedLoan);
    }

    @Operation(summary = "Eliminar préstamo", description = "Elimina un préstamo del sistema basado en su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Préstamo eliminado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Préstamo no encontrado"),
        @ApiResponse(responseCode = "400", description = "ID de préstamo inválido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLoan(
            @Parameter(description = "ID único del préstamo", required = true, example = "1")
            @PathVariable Long id) {
        loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar préstamos por estado", description = "Retorna todos los préstamos que coinciden con el estado especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Estado de préstamo inválido")
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanResponse>> getLoansByStatus(
            @Parameter(description = "Estado del préstamo (PENDING, APPROVED, REJECTED)", required = true, example = "APPROVED")
            @PathVariable String status) {
        List<LoanResponse> loans = loanService.findByStatus(status);
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Buscar préstamos por rango de monto", description = "Retorna préstamos cuyo monto está dentro del rango especificado")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "Rango de monto inválido")
    })
    @GetMapping("/amount")
    public ResponseEntity<List<LoanResponse>> getLoansByAmountRange(
            @Parameter(description = "Monto mínimo", required = true, example = "1000.00")
            @RequestParam BigDecimal minAmount,
            @Parameter(description = "Monto máximo", required = true, example = "50000.00")
            @RequestParam BigDecimal maxAmount) {
        List<LoanResponse> loans = loanService.findByAmountBetween(minAmount, maxAmount);
        return ResponseEntity.ok(loans);
    }

    @Operation(summary = "Buscar préstamos por cliente", description = "Retorna todos los préstamos asociados a un cliente específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Préstamos encontrados exitosamente"),
        @ApiResponse(responseCode = "400", description = "ID de cliente inválido")
    })
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<LoanResponse>> getLoansByClient(
            @Parameter(description = "ID único del cliente", required = true, example = "100")
            @PathVariable Long clientId) {
        List<LoanResponse> loans = loanService.findByClientId(clientId);
        return ResponseEntity.ok(loans);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/service/LoanService.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;

import java.math.BigDecimal;
import java.util.List;

public interface LoanService {

    LoanResponse create(LoanRequest request);

    LoanResponse update(Long id, LoanRequest request);

    void delete(Long id);

    LoanResponse findById(Long id);

    List<LoanResponse> findAll();

    List<LoanResponse> findByStatus(String status);

    List<LoanResponse> findByAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);

    List<LoanResponse> findByClientId(Long clientId);

    List<LoanResponse> findApprovedLoansWithMinAmount(BigDecimal minAmount);

    Long countByStatus(String status);

    List<LoanResponse> findLoansCreatedAfter(java.time.LocalDate fecha);
}


// === ARCHIVO: src/main/java/com/fintech/loansapi/exception/LoanNotFoundException.java ===
package com.fintech.loansapi.exception;

import java.time.LocalDateTime;
import java.util.Map;

public class LoanNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final Long loanId;
    private final String requestedOperation;
    private final LocalDateTime timestamp;
    private final Map<String, Object> context;
    
    public LoanNotFoundException(Long loanId) {
        super(buildMessage(loanId, "consulta"));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        this.loanId = loanId;
        this.requestedOperation = "consulta";
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id"
        );
    }
    
    public LoanNotFoundException(Long loanId, String operation) {
        super(buildMessage(loanId, operation));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException("La operación no puede ser nula o vacía");
        }
        this.loanId = loanId;
        this.requestedOperation = operation;
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id",
            "operation", operation
        );
    }
    
    private static String buildMessage(Long loanId, String operation) {
        return String.format("Préstamo no encontrado con ID: %d durante operación de %s", loanId, operation);
    }
    
    public Long getLoanId() {
        return loanId;
    }
    
    public String getRequestedOperation() {
        return requestedOperation;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public Map<String, Object> getContext() {
        return context;
    }
    
    public String getDebugInfo() {
        return String.format("LoanNotFoundException{id=%d, operation='%s', timestamp=%s}", 
            loanId, requestedOperation, timestamp);
    }
    
    @Override
    public String toString() {
        return "LoanNotFoundException{" +
                "loanId=" + loanId +
                ", requestedOperation='" + requestedOperation + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/exception/GlobalExceptionHandler.java ===
package com.fintech.loansapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR_CODE_VALIDATION = "VALIDATION_ERROR";
    private static final String ERROR_CODE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    private static final String ERROR_CODE_INTERNAL = "INTERNAL_SERVER_ERROR";
    private static final String ERROR_CODE_TYPE_MISMATCH = "TYPE_MISMATCH";
    
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLoanNotFoundException(
            LoanNotFoundException ex, WebRequest request) {
        
        logger.warn("Préstamo no encontrado: {} - URI: {}", ex.getMessage(), request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Not Found")
                .message(ex.getMessage())
                .errorCode(ERROR_CODE_NOT_FOUND)
                .path(request.getDescription(false).replace("uri=", ""))
                .debugMessage(ex.getDebugInfo())
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        logger.warn("Error de validación en la solicitud: {}", request.getDescription(false));
        
        Map<String, String> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        error -> error.getDefaultMessage() != null ? 
                                error.getDefaultMessage() : "Valor inválido",
                        (existing, replacement) -> existing
                ));
        
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos proporcionados no son válidos")
                .errorCode(ERROR_CODE_VALIDATION)
                .path(request.getDescription(false).replace("uri=", ""))
                .fieldErrors(fieldErrors)
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        logger.warn("Tipo de argumento inválido: {} - URI: {}", ex.getMessage(), request.getDescription(false));
        
        String message = String.format("El parámetro '%s' tiene un valor '%s' que no es válido para el tipo '%s'",
                ex.getName(), ex.getValue(), ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconocido");
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(message)
                .errorCode(ERROR_CODE_TYPE_MISMATCH)
                .path(request.getDescription(false).replace("uri=", ""))
                .debugMessage(ex.getName())
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        
        logger.warn("Argumento ilegal: {} - URI: {}", ex.getMessage(), request.getDescription(false));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .errorCode(ERROR_CODE_VALIDATION)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {
        
        logger.error("Error interno del servidor: {} - URI: {}", ex.getMessage(), request.getDescription(false), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ha ocurrido un error inesperado. Por favor, contacte al administrador.")
                .errorCode(ERROR_CODE_INTERNAL)
                .path(request.getDescription(false).replace("uri=", ""))
                .debugMessage(ex.getClass().getSimpleName())
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    
    public static class ErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String errorCode;
        private String path;
        private String debugMessage;
        
        public static ErrorResponseBuilder builder() {
            return new ErrorResponseBuilder();
        }
        
        public static class ErrorResponseBuilder {
            private LocalDateTime timestamp;
            private int status;
            private String error;
            private String message;
            private String errorCode;
            private String path;
            private String debugMessage;
            
            public ErrorResponseBuilder timestamp(LocalDateTime timestamp) {
                this.timestamp = timestamp;
                return this;
            }
            
            public ErrorResponseBuilder status(int status) {
                this.status = status;
                return this;
            }
            
            public ErrorResponseBuilder error(String error) {
                this.error = error;
                return this;
            }
            
            public ErrorResponseBuilder message(String message) {
                this.message = message;
                return this;
            }
            
            public ErrorResponseBuilder errorCode(String errorCode) {
                this.errorCode = errorCode;
                return this;
            }
            
            public ErrorResponseBuilder path(String path) {
                this.path = path;
                return this;
            }
            
            public ErrorResponseBuilder debugMessage(String debugMessage) {
                this.debugMessage = debugMessage;
                return this;
            }
            
            public ErrorResponse build() {
                ErrorResponse response = new ErrorResponse();
                response.timestamp = this.timestamp;
                response.status = this.status;
                response.error = this.error;
                response.message = this.message;
                response.errorCode = this.errorCode;
                response.path = this.path;
                response.debugMessage = this.debugMessage;
                return response;
            }
        }
        
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
        public int getStatus() { return status; }
        public void setStatus(int status) { this.status = status; }
        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
        public String getMessage() { return message; }
        public void setMessage(String message) { this.message = message; }
        public String getErrorCode() { return errorCode; }
        public void setErrorCode(String errorCode) { this.errorCode = errorCode; }
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getDebugMessage() { return debugMessage; }
        public void setDebugMessage(String debugMessage) { this.debugMessage = debugMessage; }
    }
    
    public static class ValidationErrorResponse extends ErrorResponse {
        private Map<String, String> fieldErrors;
        
        public Map<String, String> getFieldErrors() { return fieldErrors; }
        public void setFieldErrors(Map<String, String> fieldErrors) { this.fieldErrors = fieldErrors; }
        
        public static ValidationErrorResponseBuilder builder() {
            return new ValidationErrorResponseBuilder();
        }
        
        public static class ValidationErrorResponseBuilder extends ErrorResponseBuilder {
            private Map<String, String> fieldErrors;
            
            public ValidationErrorResponseBuilder fieldErrors(Map<String, String> fieldErrors) {
                this.fieldErrors = fieldErrors;
                return this;
            }
            
            public ValidationErrorResponse build() {
                ValidationErrorResponse response = new ValidationErrorResponse();
                response.fieldErrors = this.fieldErrors;
                return response;
            }
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/service/LoanServiceImpl.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.exception.LoanNotFoundException;
import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    
    private final LoanRepository loanRepository;
    
    public LoanServiceImpl(LoanRepository loanRepository) {
        if (loanRepository == null) {
            throw new IllegalArgumentException("LoanRepository no puede ser nulo");
        }
        this.loanRepository = loanRepository;
    }
    
    @Override
    public LoanResponse createLoan(LoanRequest request) {
        logger.info("Creando nuevo préstamo para cliente: {}", request.clientId());
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        if (!request.isValid()) {
            throw new IllegalArgumentException("Los datos del préstamo no son válidos: " + 
                    "monto debe ser positivo, tasa de interés debe ser positiva, duración debe ser mayor a 0");
        }
        
        Loan loan = request.toEntity();
        loan.setStatus(Loan.LoanStatus.PENDING);
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan savedLoan = loanRepository.save(loan);
        logger.info("Préstamo creado exitosamente con ID: {}", savedLoan.getId());
        
        return LoanResponse.fromEntity(savedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long id) {
        logger.debug("Buscando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Optional<Loan> loanOptional = loanRepository.findById(id);
        
        if (loanOptional.isEmpty()) {
            logger.warn("Préstamo no encontrado con ID: {}", id);
            throw new LoanNotFoundException(id, "consulta");
        }
        
        Loan loan = loanOptional.get();
        logger.debug("Préstamo encontrado: {}", loan.getId());
        
        return LoanResponse.fromEntity(loan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getAllLoans() {
        logger.debug("Obteniendo todos los préstamos");
        
        List<Loan> loans = loanRepository.findAllByOrderByCreatedAtDesc();
        
        logger.info("Se encontraron {} préstamos", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse updateLoan(Long id, LoanRequest request) {
        logger.info("Actualizando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para actualización con ID: {}", id);
                    return new LoanNotFoundException(id, "actualización");
                });
        
        request.updateEntity(existingLoan);
        existingLoan.setUpdatedAt(LocalDateTime.now());
        
        Loan updatedLoan = loanRepository.save(existingLoan);
        logger.info("Préstamo actualizado exitosamente: {}", updatedLoan.getId());
        
        return LoanResponse.fromEntity(updatedLoan);
    }
    
    @Override
    public void deleteLoan(Long id) {
        logger.info("Eliminando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (!loanRepository.existsById(id)) {
            logger.warn("Préstamo no encontrado para eliminación con ID: {}", id);
            throw new LoanNotFoundException(id, "eliminación");
        }
        
        loanRepository.deleteById(id);
        logger.info("Préstamo eliminado exitosamente: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByStatus(Loan.LoanStatus status) {
        logger.debug("Buscando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        List<Loan> loans = loanRepository.findByStatus(status);
        
        logger.info("Se encontraron {} préstamos con estado {}", loans.size(), status);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        logger.debug("Buscando préstamos entre {} y {}", minAmount, maxAmount);
        
        if (minAmount == null || maxAmount == null) {
            throw new IllegalArgumentException("Los montos mínimo y máximo no pueden ser nulos");
        }
        
        if (minAmount.compareTo(BigDecimal.ZERO) <= 0 || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Los montos deben ser valores positivos");
        }
        
        if (minAmount.compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("El monto mínimo no puede ser mayor que el máximo");
        }
        
        List<Loan> loans = loanRepository.findByAmountBetween(minAmount, maxAmount);
        
        logger.info("Se encontraron {} préstamos en el rango de monto", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByClientId(Long clientId) {
        logger.debug("Buscando préstamos para cliente: {}", clientId);
        
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un valor positivo");
        }
        
        List<Loan> loans = loanRepository.findByClientId(clientId);
        
        logger.info("Se encontraron {} préstamos para el cliente {}", loans.size(), clientId);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse approveLoan(Long id) {
        logger.info("Aprobando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para aprobación con ID: {}", id);
                    return new LoanNotFoundException(id, "aprobación");
                });
        
        if (loan.getStatus() != Loan.LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.approve();
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(loan.getDurationMonths()));
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan approvedLoan = loanRepository.save(loan);
        logger.info("Préstamo aprobado exitosamente: {}", approvedLoan.getId());
        
        return LoanResponse.fromEntity(approvedLoan);
    }
    
    @Override
    public LoanResponse rejectLoan(Long id) {
        logger.info("Rechazando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para rechazo con ID: {}", id);
                    return new LoanNotFoundException(id, "rechazo");
                });
        
        if (loan.getStatus() != Loan.LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.reject();
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan rejectedLoan = loanRepository.save(loan);
        logger.info("Préstamo rechazado exitosamente: {}", rejectedLoan.getId());
        
        return LoanResponse.fromEntity(rejectedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countLoansByStatus(Loan.LoanStatus status) {
        logger.debug("Contando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        Long count = loanRepository.countByStatus(status.name());
        logger.info("Total de préstamos con estado {}: {}", status, count);
        
        return count;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/config/OpenApiConfig.java ===
package com.fintech.loansapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8080}")
    private String serverPort;

    @Bean
    public OpenAPI customOpenAPI() {
        Server server = new Server();
        server.setUrl("http://localhost:" + serverPort);
        server.setDescription("Servidor de desarrollo local");

        Contact contact = new Contact();
        contact.setName("Equipo de Desarrollo Fintech");
        contact.setEmail("dev@fintech.com");
        contact.setUrl("https://www.fintech.com");

        License license = new License();
        license.setName("Apache 2.0");
        license.setUrl("https://www.apache.org/licenses/LICENSE-2.0.html");

        Info info = new Info();
        info.setTitle("Loans API - Fintech");
        info.setVersion("1.0.0");
        info.setDescription("""
                API REST para la gestión de préstamos en la plataforma Fintech.
                
                Esta API permite realizar operaciones CRUD completas sobre préstamos,
                incluyendo creación, consulta, actualización y eliminación de registros.
                
                ## Autenticación
                Currently, this API uses basic authentication. Future versions will include
                OAuth 2.0 support for enhanced security.
                
                ## Rate Limiting
                Se aplica un límite de 1000 peticiones por hora por cada cliente.
                
                ## Códigos de Estado
                - **200 OK**: La operación fue exitosa
                - **201 Created**: El recurso fue creado correctamente
                - **400 Bad Request**: Datos de entrada inválidos
                - **404 Not Found**: El recurso no existe
                - **500 Internal Server Error**: Error interno del servidor
                """);
        info.setContact(contact);
        info.setLicense(license);

        Components components = new Components();

        return new OpenAPI()
                .info(info)
                .servers(List.of(server))
                .components(components);
    }

    @Bean
    public io.swagger.v3.oas.models.security.SecurityScheme securityScheme() {
        io.swagger.v3.oas.models.security.SecurityScheme securityScheme =
            new io.swagger.v3.oas.models.security.HttpSecurityScheme()
                .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("Authorization");
        return securityScheme;
    }
}

// === ARCHIVO: src/test/java/com/fintech/loansapi/controller/LoanControllerTest.java ===
package com.fintech.loansapi.controller;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanController")
class LoanControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private Loan loan;
    private LoanResponse loanResponse;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(Loan.LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo personal para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanResponse = LoanResponse.fromEntity(loan);
        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo personal para vehículo"
        );
    }

    @Test
    @DisplayName("GET /api/loans - Debe retornar todos los préstamos")
    void testGetAllLoans_ReturnsListOfLoans() throws Exception {
        List<LoanResponse> loans = Arrays.asList(loanResponse);
        when(loanService.getAllLoans()).thenReturn(loans);

        ResultActions result = mockMvc.perform(get("/api/loans"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].id").value(1))
              .andExpect(jsonPath("$[0].amount").value(10000.00))
              .andExpect(jsonPath("$[0].clientId").value(100));
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Debe retornar un préstamo por ID")
    void testGetLoanById_ReturnsLoan() throws Exception {
        when(loanService.getLoanById(1L)).thenReturn(Optional.of(loanResponse));

        ResultActions result = mockMvc.perform(get("/api/loans/1"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.amount").value(10000.00))
              .andExpect(jsonPath("$.interestRate").value(0.15));
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Debe retornar 404 cuando el préstamo no existe")
    void testGetLoanById_ReturnsNotFound() throws Exception {
        when(loanService.getLoanById(999L)).thenReturn(Optional.empty());

        ResultActions result = mockMvc.perform(get("/api/loans/999"));

        result.andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("POST /api/loans - Debe crear un nuevo préstamo")
    void testCreateLoan_ReturnsCreated() throws Exception {
        when(loanService.createLoan(any(LoanRequest.class))).thenReturn(loanResponse);

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isCreated())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(header().exists("Location"))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.clientId").value(100));
    }

    @Test
    @DisplayName("PUT /api/loans/{id} - Debe actualizar un préstamo existente")
    void testUpdateLoan_ReturnsOk() throws Exception {
        when(loanService.updateLoan(eq(1L), any(LoanRequest.class))).thenReturn(Optional.of(loanResponse));

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(put("/api/loans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.description").value("Préstamo personal para vehículo"));
    }

    @Test
    @DisplayName("DELETE /api/loans/{id} - Debe eliminar un préstamo")
    void testDeleteLoan_ReturnsNoContent() throws Exception {
        doNothing().when(loanService).deleteLoan(1L);

        ResultActions result = mockMvc.perform(delete("/api/loans/1"));

        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/loans/status/{status} - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_ReturnsFilteredList() throws Exception {
        List<LoanResponse> pendingLoans = Arrays.asList(loanResponse);
        when(loanService.getLoansByStatus(Loan.LoanStatus.PENDING)).thenReturn(pendingLoans);

        ResultActions result = mockMvc.perform(get("/api/loans/status/PENDING"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/approve - Debe aprobar un préstamo")
    void testApproveLoan_ReturnsOk() throws Exception {
        when(loanService.approveLoan(1L)).thenReturn(Optional.of(loanResponse));

        ResultActions result = mockMvc.perform(patch("/api/loans/1/approve"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/reject - Debe rechazar un préstamo")
    void testRejectLoan_ReturnsOk() throws Exception {
        when(loanService.rejectLoan(1L)).thenReturn(Optional.of(loanResponse));

        ResultActions result = mockMvc.perform(patch("/api/loans/1/reject"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }
}

// === ARCHIVO: src/test/java/com/fintech/loansapi/service/LoanServiceTest.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanService")
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan loan;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(Loan.LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo para vehículo"
        );
    }

    @Test
    @DisplayName("getAllLoans - Debe retornar todos los préstamos ordenados por fecha")
    void testGetAllLoans_ReturnsAllLoans() {
        List<Loan> loans = Arrays.asList(loan);
        when(loanRepository.findAllByOrderByCreatedAtDesc()).thenReturn(loans);

        List<LoanResponse> result = loanService.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        verify(loanRepository, times(1)).findAllByOrderByCreatedAtDesc();
    }

    @Test
    @DisplayName("getLoanById - Debe retornar préstamo cuando existe")
    void testGetLoanById_WhenExists_ReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Optional<LoanResponse> result = loanService.getLoanById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        assertEquals(new BigDecimal("10000.00"), result.get().amount());
        verify(loanRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getLoanById - Debe retornar vacío cuando no existe")
    void testGetLoanById_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.getLoanById(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("createLoan - Debe crear y retornar el préstamo")
    void testCreateLoan_CreatesAndReturnsLoan() {
        Loan savedLoan = new Loan();
        savedLoan.setId(1L);
        savedLoan.setAmount(loanRequest.amount());
        savedLoan.setInterestRate(loanRequest.interestRate());
        savedLoan.setDurationMonths(loanRequest.durationMonths());
        savedLoan.setClientId(loanRequest.clientId());
        savedLoan.setDescription(loanRequest.description());
        savedLoan.setStatus(Loan.LoanStatus.PENDING);
        savedLoan.setCreatedAt(LocalDateTime.now());
        savedLoan.setUpdatedAt(LocalDateTime.now());

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanResponse result = loanService.createLoan(loanRequest);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(new BigDecimal("10000.00"), result.amount());
        assertEquals(Loan.LoanStatus.PENDING, result.status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe actualizar y retornar el préstamo")
    void testUpdateLoan_UpdatesAndReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Optional<LoanResponse> result = loanService.updateLoan(1L, loanRequest);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        verify(loanRepository, times(1)).findById(1L);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe retornar vacío cuando el préstamo no existe")
    void testUpdateLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.updateLoan(999L, loanRequest);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("deleteLoan - Debe eliminar el préstamo sin retornar valor")
    void testDeleteLoan_DeletesLoan() {
        doNothing().when(loanRepository).deleteById(1L);

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("getLoansByStatus - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_FiltersByStatus() {
        List<Loan> pendingLoans = Arrays.asList(loan);
        when(loanRepository.findByStatus(Loan.LoanStatus.PENDING)).thenReturn(pendingLoans);

        List<LoanResponse> result = loanService.getLoansByStatus(Loan.LoanStatus.PENDING);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Loan.LoanStatus.PENDING, result.get(0).status());
        verify(loanRepository, times(1)).findByStatus(Loan.LoanStatus.PENDING);
    }

    @Test
    @DisplayName("approveLoan - Debe aprobar un préstamo pendiente")
    void testApproveLoan_ApprovesPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.APPROVED);
            return l;
        });

        Optional<LoanResponse> result = loanService.approveLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.APPROVED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("approveLoan - Debe retornar vacío cuando el préstamo no existe")
    void testApproveLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.approveLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe rechazar un préstamo pendiente")
    void testRejectLoan_RejectsPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.REJECTED);
            return l;
        });

        Optional<LoanResponse> result = loanService.rejectLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.REJECTED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe retornar vacío cuando el préstamo no existe")
    void testRejectLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.rejectLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("getLoansByAmountRange - Debe filtrar préstamos por rango de monto")
    void testGetLoansByAmountRange_FiltersByAmount() {
        List<Loan> loans = Arrays.asList(loan);
        BigDecimal minAmount = new BigDecimal("5000.00");
        BigDecimal maxAmount = new BigDecimal("15000.00");
        when(loanRepository.findByAmountBetween(minAmount, maxAmount)).thenReturn(loans);

        List<LoanResponse> result = loanService.getLoansByAmountRange(minAmount, maxAmount);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findByAmountBetween(minAmount, maxAmount);
    }

    @Test
    @DisplayName("getLoansByClientId - Debe filtrar préstamos por ID de cliente")
    void testGetLoansByClientId_FiltersByClient() {
        List<Loan> clientLoans = Arrays.asList(loan);
        when(loanRepository.findByClientId(100L)).thenReturn(clientLoans);

        List<LoanResponse> result = loanService.getLoansByClientId(100L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).clientId());
        verify(loanRepository, times(1)).findByClientId(100L);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/repository/LoanRepository.java ===
package com.fintech.loansapi.repository;

import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.Loan.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de persistencia de préstamos.
 * Extiende JpaRepository para heredar operaciones CRUD básicas.
 * Proporciona métodos de consulta personalizados para el dominio de préstamos.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    /**
     * Busca un préstamo por su identificador único.
     * @param id identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> findById(Long id);

    /**
     * Recupera todos los préstamos ordenados por fecha de creación descendente.
     * @return lista de préstamos
     */
    List<Loan> findAllByOrderByCreatedAtDesc();

    /**
     * Busca préstamos por estado.
     * @param estado estado del préstamo (PENDING, APPROVED, REJECTED)
     * @return lista de préstamos con el estado especificado
     */
    List<Loan> findByStatus(LoanStatus estado);

    /**
     * Busca préstamos por rango de monto.
     * @param montoMin monto mínimo
     * @param montoMax monto máximo
     * @return lista de préstamos dentro del rango
     */
    List<Loan> findByAmountBetween(BigDecimal montoMin, BigDecimal montoMax);

    /**
     * Busca préstamos por ID de cliente.
     * @param clientId identificador del cliente
     * @return lista de préstamos del cliente
     */
    List<Loan> findByClientId(Long clientId);

    /**
     * Consulta JPQL para obtener préstamos aprobados con monto mayor al especificado.
     * @param montoMin monto mínimo
     * @return lista de préstamos aprobados que superan el monto
     */
    @Query("SELECT l FROM Loan l WHERE l.status = 'APPROVED' AND l.amount >= :montoMin")
    List<Loan> findApprovedLoansWithMinAmount(@Param("montoMin") BigDecimal montoMin);

    /**
     * Consulta nativa para contar préstamos por estado.
     * @param status estado del préstamo
     * @return cantidad de préstamos en ese estado
     */
    @Query(value = "SELECT COUNT(*) FROM loans WHERE status = :status", nativeQuery = true)
    Long countByStatus(@Param("status") String status);

    /**
     * Busca préstamos creados después de una fecha específica.
     * @param fecha fecha límite
     * @return lista de préstamos posteriores a la fecha
     */
    List<Loan> findByCreatedAtAfter(LocalDate fecha);

    /**
     * Verifica si existe un préstamo con el ID proporcionado.
     * @param id identificador
     * @return true si existe
     */
    boolean existsById(Long id);

    /**
     * Elimina un préstamo por su ID.
     * @param id identificador del préstamo a eliminar
     */
    void deleteById(Long id);
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.Loan.LoanStatus;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

/**
 * DTO para la creación y actualización de préstamos.
 * Utiliza Jakarta Validation para validar los datos de entrada.
 * Se mapea entre la entidad Loan y las solicitudes HTTP.
 *
 * Este record proporciona inmutabilidad y reduce boilerplate en Java 21.
 */
public record LoanRequest(

        @NotNull(message = "El monto del préstamo es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        @DecimalMax(value = "10000000.0", message = "El monto máximo es 10,000,000")
        BigDecimal amount,

        @NotNull(message = "La tasa de interés es obligatoria")
        @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
        @DecimalMax(value = "100.0", message = "La tasa de interés no puede exceder el 100%")
        BigDecimal interestRate,

        @NotNull(message = "La duración es obligatoria")
        @Min(value = 1, message = "La duración mínima es 1 mes")
        @Max(value = 360, message = "La duración máxima es 360 meses")
        Integer durationMonths,

        @NotNull(message = "El ID del cliente es obligatorio")
        @Min(value = 1, message = "El ID del cliente debe ser válido")
        Long clientId,

        @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
        String description,

        @Pattern(regexp = "PENDING|APPROVED|REJECTED", message = "Estado inválido")
        String status
) {

    /**
     * Convierte este DTO en una entidad Loan.
     * @return nueva instancia de Loan con los datos del DTO
     */
    public Loan toEntity() {
        Loan loan = new Loan(this.amount, this.interestRate, this.durationMonths, this.clientId);
        loan.setDescription(this.description);

        if (this.status != null) {
            loan.setStatus(LoanStatus.valueOf(this.status));
        }

        return loan;
    }

    /**
     * Actualiza una entidad Loan existente con los datos del DTO.
     * @param loan entidad a actualizar
     */
    public void updateEntity(Loan loan) {
        if (this.amount != null) {
            loan.setAmount(this.amount);
        }
        if (this.interestRate != null) {
            loan.setInterestRate(this.interestRate);
        }
        if (this.durationMonths != null) {
            loan.setDurationMonths(this.durationMonths);
        }
        if (this.clientId != null) {
            loan.setClientId(this.clientId);
        }
        if (this.description != null) {
            loan.setDescription(this.description);
        }
        if (this.status != null && !this.status.isEmpty()) {
            loan.setStatus(LoanStatus.valueOf(this.status));
        }
    }

    /**
     * Crea un LoanRequest desde una entidad Loan.
     * @param loan entidad origen
     * @return nuevo LoanRequest con los datos de la entidad
     */
    public static LoanRequest fromEntity(Loan loan) {
        return new LoanRequest(
                loan.getAmount(),
                loan.getInterestRate(),
                loan.getDurationMonths(),
                loan.getClientId(),
                loan.getDescription(),
                loan.getStatus() != null ? loan.getStatus().name() : null
        );
    }

    /**
     * Valida que los campos numéricos sean coherentes.
     * @return true si los datos son coherentes
     */
    public boolean isValid() {
        if (amount == null || interestRate == null || durationMonths == null || clientId == null) {
            return false;
        }

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (interestRate.compareTo(BigDecimal.ZERO) < 0 || interestRate.compareTo(BigDecimal.valueOf(100)) > 0) {
            return false;
        }

        if (durationMonths < 1 || durationMonths > 360) {
            return false;
        }

        return clientId > 0;
    }

    /**
     * Calcula el monto total estimado con intereses.
     * @return monto total
     */
    public BigDecimal calculateTotalAmount() {
        if (!isValid()) {
            return BigDecimal.ZERO;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual estimada.
     * @return cuota mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total.compareTo(BigDecimal.ZERO) == 0 || durationMonths == null) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.Loan.LoanStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "DTO de respuesta para operaciones de préstamos")
public record LoanResponse(
    @Schema(description = "ID único del préstamo", example = "1")
    Long id,
    
    @Schema(description = "Monto del préstamo", example = "10000.00")
    BigDecimal amount,
    
    @Schema(description = "Tasa de interés anual", example = "0.12")
    BigDecimal interestRate,
    
    @Schema(description = "Duración en meses", example = "12")
    Integer durationMonths,
    
    @Schema(description = "Estado del préstamo", example = "APPROVED")
    String status,
    
    @Schema(description = "ID del cliente", example = "100")
    Long clientId,
    
    @Schema(description = "Descripción del préstamo", example = "Préstamo personal para consolidación de deudas")
    String description,
    
    @Schema(description = "Fecha de inicio del préstamo")
    LocalDate startDate,
    
    @Schema(description = "Fecha de fin del préstamo")
    LocalDate endDate,
    
    @Schema(description = "Monto total a pagar incluyendo intereses")
    BigDecimal totalAmount,
    
    @Schema(description = "Pago mensual estimado")
    BigDecimal monthlyPayment,
    
    @Schema(description = "Fecha de creación del registro")
    LocalDateTime createdAt,
    
    @Schema(description = "Fecha de última actualización")
    LocalDateTime updatedAt
) {
    public static LoanResponse fromEntity(Loan loan) {
        if (loan == null) {
            return null;
        }
        return new LoanResponse(
            loan.getId(),
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getStatus() != null ? loan.getStatus().name() : null,
            loan.getClientId(),
            loan.getDescription(),
            loan.getStartDate(),
            loan.getEndDate(),
            loan.calculateTotalAmount(),
            loan.calculateMonthlyPayment(),
            loan.getCreatedAt(),
            loan.getUpdatedAt()
        );
    }

    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setId(this.id);
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        if (this.status != null) {
            loan.setStatus(LoanStatus.valueOf(this.status));
        }
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStartDate(this.startDate);
        loan.setEndDate(this.endDate);
        loan.setCreatedAt(this.createdAt);
        loan.setUpdatedAt(this.updatedAt);
        return loan;
    }
}

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.fintech</groupId>
    <artifactId>loans-api</artifactId>
    <version>1.0.0</version>
    <name>loans-api</name>
    <description>Préstamos API</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.13</version>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/fintech/loansapi/exception/LoanNotFoundException.java ===
package com.fintech.loansapi.exception;

import com.fintech.loansapi.model.entity.Loan;
import java.time.LocalDateTime;
import java.util.Map;

public class LoanNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private final Long loanId;
    private final String requestedOperation;
    private final LocalDateTime timestamp;
    private final Map<String, Object> context;
    
    public LoanNotFoundException(Long loanId) {
        super(buildMessage(loanId, "consulta"));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        this.loanId = loanId;
        this.requestedOperation = "consulta";
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id"
        );
    }
    
    public LoanNotFoundException(Long loanId, String operation) {
        super(buildMessage(loanId, operation));
        if (loanId == null || loanId <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo mayor que cero");
        }
        if (operation == null || operation.isBlank()) {
            throw new IllegalArgumentException("La operación no puede ser nula o vacía");
        }
        this.loanId = loanId;
        this.requestedOperation = operation;
        this.timestamp = LocalDateTime.now();
        this.context = Map.of(
            "loanId", loanId,
            "entityType", "Loan",
            "searchField", "id",
            "operation", operation
        );
    }
    
    private static String buildMessage(Long loanId, String operation) {
        return String.format("Préstamo no encontrado con ID: %d durante operación de %s", loanId, operation);
    }
    
    public Long getLoanId() {
        return loanId;
    }
    
    public String getRequestedOperation() {
        return requestedOperation;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public Map<String, Object> getContext() {
        return context;
    }
    
    public String getDebugInfo() {
        return String.format("LoanNotFoundException{id=%d, operation='%s', timestamp=%s}", 
            loanId, requestedOperation, timestamp);
    }
    
    @Override
    public String toString() {
        return "LoanNotFoundException{" +
                "loanId=" + loanId +
                ", requestedOperation='" + requestedOperation + '\'' +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/entity/LoanStatus.java ===
package com.fintech.loansapi.model.entity;

public enum LoanStatus {
    PENDING,
    APPROVED,
    REJECTED,
    PAID;
    
    public String name() {
        return this.name();
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanRequest(
    BigDecimal amount,
    BigDecimal interestRate,
    Integer durationMonths,
    Long clientId,
    String description
) {
    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        return loan;
    }
    
    public void updateEntity(Loan loan) {
        if (this.amount != null) {
            loan.setAmount(this.amount);
        }
        if (this.interestRate != null) {
            loan.setInterestRate(this.interestRate);
        }
        if (this.durationMonths != null) {
            loan.setDurationMonths(this.durationMonths);
        }
        if (this.description != null) {
            loan.setDescription(this.description);
        }
    }
    
    public static LoanRequest fromEntity(Loan loan) {
        return new LoanRequest(
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getClientId(),
            loan.getDescription()
        );
    }
    
    public boolean isValid() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && interestRate != null && interestRate.compareTo(BigDecimal.ZERO) > 0
            && durationMonths != null && durationMonths > 0;
    }
    
    public BigDecimal calculateTotalAmount() {
        if (!isValid()) {
            return BigDecimal.ZERO;
        }
        BigDecimal interest = amount.multiply(interestRate);
        return amount.add(interest);
    }
    
    public BigDecimal calculateMonthlyPayment() {
        if (!isValid()) {
            return BigDecimal.ZERO;
        }
        BigDecimal total = calculateTotalAmount();
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/service/LoanService.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanService {
    LoanResponse createLoan(LoanRequest request);
    LoanResponse updateLoan(Long id, LoanRequest request);
    void deleteLoan(Long id);
    LoanResponse getLoanById(Long id);
    List<LoanResponse> getAllLoans();
    List<LoanResponse> getLoansByStatus(LoanStatus status);
    List<LoanResponse> getLoansByAmountRange(BigDecimal minAmount, BigDecimal maxAmount);
    List<LoanResponse> getLoansByClientId(Long clientId);
    List<LoanResponse> getApprovedLoansWithMinAmount(BigDecimal minAmount);
    Long countLoansByStatus(LoanStatus status);
    List<LoanResponse> getLoansCreatedAfter(LocalDate fecha);
    LoanResponse approveLoan(Long id);
    LoanResponse rejectLoan(Long id);
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/service/LoanServiceImpl.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.exception.LoanNotFoundException;
import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import com.fintech.loansapi.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {
    
    private static final Logger logger = LoggerFactory.getLogger(LoanServiceImpl.class);
    
    private final LoanRepository loanRepository;
    
    public LoanServiceImpl(LoanRepository loanRepository) {
        if (loanRepository == null) {
            throw new IllegalArgumentException("LoanRepository no puede ser nulo");
        }
        this.loanRepository = loanRepository;
    }
    
    @Override
    public LoanResponse createLoan(LoanRequest request) {
        logger.info("Creando nuevo préstamo para cliente: {}", request.clientId());
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        if (!request.isValid()) {
            throw new IllegalArgumentException("Los datos del préstamo no son válidos: " + 
                    "monto debe ser positivo, tasa de interés debe ser positiva, duración debe ser mayor a 0");
        }
        
        Loan loan = request.toEntity();
        loan.setStatus(LoanStatus.PENDING);
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan savedLoan = loanRepository.save(loan);
        logger.info("Préstamo creado exitosamente con ID: {}", savedLoan.getId());
        
        return LoanResponse.fromEntity(savedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public LoanResponse getLoanById(Long id) {
        logger.debug("Buscando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Optional<Loan> loanOptional = loanRepository.findById(id);
        
        if (loanOptional.isEmpty()) {
            logger.warn("Préstamo no encontrado con ID: {}", id);
            throw new LoanNotFoundException(id, "consulta");
        }
        
        Loan loan = loanOptional.get();
        logger.debug("Préstamo encontrado: {}", loan.getId());
        
        return LoanResponse.fromEntity(loan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getAllLoans() {
        logger.debug("Obteniendo todos los préstamos");
        
        List<Loan> loans = loanRepository.findAllByOrderByCreatedAtDesc();
        
        logger.info("Se encontraron {} préstamos", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse updateLoan(Long id, LoanRequest request) {
        logger.info("Actualizando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (request == null) {
            throw new IllegalArgumentException("La solicitud de préstamo no puede ser nula");
        }
        
        Loan existingLoan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para actualización con ID: {}", id);
                    return new LoanNotFoundException(id, "actualización");
                });
        
        request.updateEntity(existingLoan);
        existingLoan.setUpdatedAt(LocalDateTime.now());
        
        Loan updatedLoan = loanRepository.save(existingLoan);
        logger.info("Préstamo actualizado exitosamente: {}", updatedLoan.getId());
        
        return LoanResponse.fromEntity(updatedLoan);
    }
    
    @Override
    public void deleteLoan(Long id) {
        logger.info("Eliminando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        if (!loanRepository.existsById(id)) {
            logger.warn("Préstamo no encontrado para eliminación con ID: {}", id);
            throw new LoanNotFoundException(id, "eliminación");
        }
        
        loanRepository.deleteById(id);
        logger.info("Préstamo eliminado exitosamente: {}", id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByStatus(LoanStatus status) {
        logger.debug("Buscando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        List<Loan> loans = loanRepository.findByStatus(status);
        
        logger.info("Se encontraron {} préstamos con estado {}", loans.size(), status);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByAmountRange(BigDecimal minAmount, BigDecimal maxAmount) {
        logger.debug("Buscando préstamos entre {} y {}", minAmount, maxAmount);
        
        if (minAmount == null || maxAmount == null) {
            throw new IllegalArgumentException("Los montos mínimo y máximo no pueden ser nulos");
        }
        
        if (minAmount.compareTo(BigDecimal.ZERO) <= 0 || maxAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Los montos deben ser valores positivos");
        }
        
        if (minAmount.compareTo(maxAmount) > 0) {
            throw new IllegalArgumentException("El monto mínimo no puede ser mayor que el máximo");
        }
        
        List<Loan> loans = loanRepository.findByAmountBetween(minAmount, maxAmount);
        
        logger.info("Se encontraron {} préstamos en el rango de monto", loans.size());
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<LoanResponse> getLoansByClientId(Long clientId) {
        logger.debug("Buscando préstamos para cliente: {}", clientId);
        
        if (clientId == null || clientId <= 0) {
            throw new IllegalArgumentException("El ID del cliente debe ser un valor positivo");
        }
        
        List<Loan> loans = loanRepository.findByClientId(clientId);
        
        logger.info("Se encontraron {} préstamos para el cliente {}", loans.size(), clientId);
        
        return loans.stream()
                .map(LoanResponse::fromEntity)
                .collect(Collectors.toList());
    }
    
    @Override
    public LoanResponse approveLoan(Long id) {
        logger.info("Aprobando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para aprobación con ID: {}", id);
                    return new LoanNotFoundException(id, "aprobación");
                });
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden aprobar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.approve();
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(loan.getDurationMonths()));
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan approvedLoan = loanRepository.save(loan);
        logger.info("Préstamo aprobado exitosamente: {}", approvedLoan.getId());
        
        return LoanResponse.fromEntity(approvedLoan);
    }
    
    @Override
    public LoanResponse rejectLoan(Long id) {
        logger.info("Rechazando préstamo con ID: {}", id);
        
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID del préstamo debe ser un valor positivo");
        }
        
        Loan loan = loanRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Préstamo no encontrado para rechazo con ID: {}", id);
                    return new LoanNotFoundException(id, "rechazo");
                });
        
        if (loan.getStatus() != LoanStatus.PENDING) {
            throw new IllegalStateException("Solo se pueden rechazar préstamos en estado PENDING. Estado actual: " + loan.getStatus());
        }
        
        loan.reject();
        loan.setUpdatedAt(LocalDateTime.now());
        
        Loan rejectedLoan = loanRepository.save(loan);
        logger.info("Préstamo rechazado exitosamente: {}", rejectedLoan.getId());
        
        return LoanResponse.fromEntity(rejectedLoan);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Long countLoansByStatus(LoanStatus status) {
        logger.debug("Contando préstamos con estado: {}", status);
        
        if (status == null) {
            throw new IllegalArgumentException("El estado del préstamo no puede ser nulo");
        }
        
        Long count = loanRepository.countByStatus(status.name());
        logger.info("Total de préstamos con estado {}: {}", status, count);
        
        return count;
    }
}

// === ARCHIVO: src/test/java/com/fintech/loansapi/controller/LoanControllerTest.java ===
package com.fintech.loansapi.controller;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.model.entity.LoanStatus;
import com.fintech.loansapi.service.LoanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanController")
class LoanControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private LoanService loanService;

    @InjectMocks
    private LoanController loanController;

    private Loan loan;
    private LoanResponse loanResponse;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo personal para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanResponse = LoanResponse.fromEntity(loan);
        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo personal para vehículo"
        );
    }

    @Test
    @DisplayName("GET /api/loans - Debe retornar todos los préstamos")
    void testGetAllLoans_ReturnsListOfLoans() throws Exception {
        List<LoanResponse> loans = Arrays.asList(loanResponse);
        when(loanService.getAllLoans()).thenReturn(loans);

        ResultActions result = mockMvc.perform(get("/api/loans"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].id").value(1))
              .andExpect(jsonPath("$[0].amount").value(10000.00))
              .andExpect(jsonPath("$[0].clientId").value(100));
    }

    @Test
    @DisplayName("GET /api/loans/{id} - Debe retornar un préstamo por ID")
    void testGetLoanById_ReturnsLoan() throws Exception {
        when(loanService.getLoanById(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(get("/api/loans/1"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.amount").value(10000.00))
              .andExpect(jsonPath("$.interestRate").value(0.15));
    }

    @Test
    @DisplayName("POST /api/loans - Debe crear un nuevo préstamo")
    void testCreateLoan_ReturnsCreated() throws Exception {
        when(loanService.createLoan(any(LoanRequest.class))).thenReturn(loanResponse);

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isCreated())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(header().exists("Location"))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.clientId").value(100));
    }

    @Test
    @DisplayName("PUT /api/loans/{id} - Debe actualizar un préstamo existente")
    void testUpdateLoan_ReturnsOk() throws Exception {
        when(loanService.updateLoan(eq(1L), any(LoanRequest.class))).thenReturn(loanResponse);

        String jsonRequest = objectMapper.writeValueAsString(loanRequest);

        ResultActions result = mockMvc.perform(put("/api/loans/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1))
              .andExpect(jsonPath("$.description").value("Préstamo personal para vehículo"));
    }

    @Test
    @DisplayName("DELETE /api/loans/{id} - Debe eliminar un préstamo")
    void testDeleteLoan_ReturnsNoContent() throws Exception {
        doNothing().when(loanService).deleteLoan(1L);

        ResultActions result = mockMvc.perform(delete("/api/loans/1"));

        result.andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GET /api/loans/status/{status} - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_ReturnsFilteredList() throws Exception {
        List<LoanResponse> pendingLoans = Arrays.asList(loanResponse);
        when(loanService.getLoansByStatus(LoanStatus.PENDING)).thenReturn(pendingLoans);

        ResultActions result = mockMvc.perform(get("/api/loans/status/PENDING"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$[0].status").value("PENDING"));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/approve - Debe aprobar un préstamo")
    void testApproveLoan_ReturnsOk() throws Exception {
        when(loanService.approveLoan(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(patch("/api/loans/1/approve"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("PATCH /api/loans/{id}/reject - Debe rechazar un préstamo")
    void testRejectLoan_ReturnsOk() throws Exception {
        when(loanService.rejectLoan(1L)).thenReturn(loanResponse);

        ResultActions result = mockMvc.perform(patch("/api/loans/1/reject"));

        result.andExpect(status().isOk())
              .andExpect(content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.id").value(1));
    }
}

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" 
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.fintech</groupId>
    <artifactId>loans-api</artifactId>
    <version>1.0.0</version>
    <name>Loans API</name>
    <description>API REST para gestión de préstamos financieros</description>
    
    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <version>3.4.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <version>3.4.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>2.2.224</version>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.32</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <version>3.4.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <version>3.4.0</version>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
        
        <!-- Dependency for SLF4J (required for Logger in LoansApiApplication) -->
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.13</version>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/entity/Loan.java ===
package com.fintech.loansapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un préstamo en el sistema.
 * Mapea la tabla "loans" de la base de datos H2.
 * Los atributos incluyen: monto, tasa de interés, duración, estado y cliente.
 */
@Entity
@Table(name = "loans")
public class Loan {

    /**
     * Enumeración de estados posibles de un préstamo.
     */
    public enum LoanStatus {
        PENDING("Pendiente"),
        APPROVED("Aprobado"),
        REJECTED("Rechazado"),
        ACTIVE("Activo"),
        PAID("Pagado"),
        DEFAULTED("Incumplido");

        private final String descripcion;

        LoanStatus(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "El monto del préstamo es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @NotNull(message = "La tasa de interés es obligatoria")
    @DecimalMin(value = "0.0", message = "La tasa de interés no puede ser negativa")
    @DecimalMax(value = "100.0", message = "La tasa de interés no puede exceder el 100%")
    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración mínima es 1 mes")
    @Max(value = 360, message = "La duración máxima es 360 meses")
    @Column(name = "duration_months", nullable = false)
    private Integer durationMonths;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "El estado del préstamo es obligatorio")
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status = LoanStatus.PENDING;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    /**
     * Constructor por defecto requerido por JPA.
     */
    public Loan() {
    }

    /**
     * Constructor con parámetros para crear un préstamo.
     */
    public Loan(BigDecimal amount, BigDecimal interestRate, Integer durationMonths, Long clientId) {
        this.amount = amount;
        this.interestRate = interestRate;
        this.durationMonths = durationMonths;
        this.clientId = clientId;
        this.status = LoanStatus.PENDING;
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Integer getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(Integer durationMonths) {
        this.durationMonths = durationMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Calcula el monto total a pagar con intereses.
     * @return monto total con intereses
     */
    public BigDecimal calculateTotalAmount() {
        if (amount == null || interestRate == null) {
            return amount;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual aproximada.
     * @return cuota mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total == null || durationMonths == null || durationMonths == 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }

    /**
     * Aprueba el préstamo estableciendo la fecha de inicio.
     */
    public void approve() {
        this.status = LoanStatus.APPROVED;
        this.startDate = LocalDate.now();
        if (this.durationMonths != null) {
            this.endDate = this.startDate.plusMonths(this.durationMonths);
        }
    }

    /**
     * Rechaza el préstamo.
     */
    public void reject() {
        this.status = LoanStatus.REJECTED;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", durationMonths=" + durationMonths +
                ", status=" + status +
                ", clientId=" + clientId +
                '}';
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;

import java.math.BigDecimal;

/**
 * DTO de solicitud para crear o actualizar un préstamo.
 * Utiliza la sintaxis de record para inmutabilidad.
 */
public record LoanRequest(
    BigDecimal amount,
    BigDecimal interestRate,
    Integer durationMonths,
    Long clientId,
    String description
) {
    /**
     * Convierte este DTO en una entidad Loan.
     */
    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStatus(Loan.LoanStatus.PENDING);
        return loan;
    }

    /**
     * Actualiza una entidad Loan existente con los valores de este DTO.
     */
    public void updateEntity(Loan loan) {
        if (this.amount != null) {
            loan.setAmount(this.amount);
        }
        if (this.interestRate != null) {
            loan.setInterestRate(this.interestRate);
        }
        if (this.durationMonths != null) {
            loan.setDurationMonths(this.durationMonths);
        }
        if (this.clientId != null) {
            loan.setClientId(this.clientId);
        }
        if (this.description != null) {
            loan.setDescription(this.description);
        }
    }

    /**
     * Crea un LoanRequest a partir de una entidad Loan.
     */
    public static LoanRequest fromEntity(Loan loan) {
        return new LoanRequest(
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getClientId(),
            loan.getDescription()
        );
    }

    /**
     * Valida que los campos del request sean correctos.
     */
    public boolean isValid() {
        return amount != null && amount.compareTo(BigDecimal.ZERO) > 0
            && interestRate != null && interestRate.compareTo(BigDecimal.ZERO) >= 0
            && durationMonths != null && durationMonths > 0
            && clientId != null;
    }

    /**
     * Calcula el monto total del préstamo.
     */
    public BigDecimal calculateTotalAmount() {
        if (amount == null || interestRate == null) {
            return amount;
        }
        BigDecimal interes = amount.multiply(interestRate).divide(BigDecimal.valueOf(100));
        return amount.add(interes);
    }

    /**
     * Calcula la cuota mensual.
     */
    public BigDecimal calculateMonthlyPayment() {
        BigDecimal total = calculateTotalAmount();
        if (total == null || durationMonths == null || durationMonths == 0) {
            return BigDecimal.ZERO;
        }
        return total.divide(BigDecimal.valueOf(durationMonths), 2, java.math.RoundingMode.HALF_UP);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java ===
package com.fintech.loansapi.model.dto;

import com.fintech.loansapi.model.entity.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * DTO de respuesta para representar un préstamo.
 * Utiliza la sintaxis de record para inmutabilidad.
 */
public record LoanResponse(
    Long id,
    BigDecimal amount,
    BigDecimal interestRate,
    Integer durationMonths,
    Loan.LoanStatus status,
    Long clientId,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    /**
     * Crea un LoanResponse a partir de una entidad Loan.
     */
    public static LoanResponse fromEntity(Loan loan) {
        return new LoanResponse(
            loan.getId(),
            loan.getAmount(),
            loan.getInterestRate(),
            loan.getDurationMonths(),
            loan.getStatus(),
            loan.getClientId(),
            loan.getDescription(),
            loan.getStartDate(),
            loan.getEndDate(),
            loan.getCreatedAt(),
            loan.getUpdatedAt()
        );
    }

    /**
     * Convierte este DTO en una entidad Loan (sin ID para nuevos préstamos).
     */
    public Loan toEntity() {
        Loan loan = new Loan();
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setDurationMonths(this.durationMonths);
        loan.setStatus(this.status);
        loan.setClientId(this.clientId);
        loan.setDescription(this.description);
        loan.setStartDate(this.startDate);
        loan.setEndDate(this.endDate);
        return loan;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loansapi/repository/LoanRepository.java ===
package com.fintech.loansapi.repository;

import com.fintech.loansapi.model.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Loan.
 * Proporciona operaciones CRUD y consultas personalizadas.
 */
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    
    Optional<Loan> findById(Long id);
    
    List<Loan> findAllByOrderByCreatedAtDesc();
    
    List<Loan> findByStatus(Loan.LoanStatus estado);
    
    List<Loan> findByAmountBetween(BigDecimal montoMin, BigDecimal montoMax);
    
    List<Loan> findByClientId(Long clientId);
    
    @Query("SELECT l FROM Loan l WHERE l.status = 'APPROVED' AND l.amount >= :montoMin")
    List<Loan> findApprovedLoansWithMinAmount(@Param("montoMin") BigDecimal montoMin);
    
    @Query(value = "SELECT COUNT(*) FROM loans WHERE status = :status", nativeQuery = true)
    Long countByStatus(@Param("status") String status);
    
    List<Loan> findByCreatedAtAfter(LocalDate fecha);
    
    boolean existsById(Long id);
    
    void deleteById(Long id);
    
    /**
     * Guarda un préstamo en la base de datos.
     * @param loan el préstamo a guardar
     * @return el préstamo guardado con su ID generado
     */
    Loan save(Loan loan);
}

// === ARCHIVO: src/test/java/com/fintech/loansapi/service/LoanServiceTest.java ===
package com.fintech.loansapi.service;

import com.fintech.loansapi.model.dto.LoanRequest;
import com.fintech.loansapi.model.dto.LoanResponse;
import com.fintech.loansapi.model.entity.Loan;
import com.fintech.loansapi.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanService")
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanServiceImpl loanService;

    private Loan loan;
    private LoanRequest loanRequest;

    @BeforeEach
    void setUp() {
        loan = new Loan();
        loan.setId(1L);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.15"));
        loan.setDurationMonths(12);
        loan.setStatus(Loan.LoanStatus.PENDING);
        loan.setClientId(100L);
        loan.setDescription("Préstamo para vehículo");
        loan.setStartDate(LocalDate.now());
        loan.setEndDate(LocalDate.now().plusMonths(12));
        loan.setCreatedAt(LocalDateTime.now());
        loan.setUpdatedAt(LocalDateTime.now());

        loanRequest = new LoanRequest(
            new BigDecimal("10000.00"),
            new BigDecimal("0.15"),
            12,
            100L,
            "Préstamo para vehículo"
        );
    }

    @Test
    @DisplayName("getAllLoans - Debe retornar todos los préstamos ordenados por fecha")
    void testGetAllLoans_ReturnsAllLoans() {
        List<Loan> loans = Arrays.asList(loan);
        when(loanRepository.findAllByOrderByCreatedAtDesc()).thenReturn(loans);

        List<LoanResponse> result = loanService.getAllLoans();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).id());
        verify(loanRepository, times(1)).findAllByOrderByCreatedAtDesc();
    }

    @Test
    @DisplayName("getLoanById - Debe retornar préstamo cuando existe")
    void testGetLoanById_WhenExists_ReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));

        Optional<LoanResponse> result = loanService.getLoanById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        assertEquals(new BigDecimal("10000.00"), result.get().amount());
        verify(loanRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("getLoanById - Debe retornar vacío cuando no existe")
    void testGetLoanById_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.getLoanById(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("createLoan - Debe crear y retornar el préstamo")
    void testCreateLoan_CreatesAndReturnsLoan() {
        Loan savedLoan = new Loan();
        savedLoan.setId(1L);
        savedLoan.setAmount(loanRequest.amount());
        savedLoan.setInterestRate(loanRequest.interestRate());
        savedLoan.setDurationMonths(loanRequest.durationMonths());
        savedLoan.setClientId(loanRequest.clientId());
        savedLoan.setDescription(loanRequest.description());
        savedLoan.setStatus(Loan.LoanStatus.PENDING);
        savedLoan.setCreatedAt(LocalDateTime.now());
        savedLoan.setUpdatedAt(LocalDateTime.now());

        when(loanRepository.save(any(Loan.class))).thenReturn(savedLoan);

        LoanResponse result = loanService.createLoan(loanRequest);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(new BigDecimal("10000.00"), result.amount());
        assertEquals(Loan.LoanStatus.PENDING, result.status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe actualizar y retornar el préstamo")
    void testUpdateLoan_UpdatesAndReturnsLoan() {
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        Optional<LoanResponse> result = loanService.updateLoan(1L, loanRequest);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().id());
        verify(loanRepository, times(1)).findById(1L);
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("updateLoan - Debe retornar vacío cuando el préstamo no existe")
    void testUpdateLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.updateLoan(999L, loanRequest);

        assertFalse(result.isPresent());
        verify(loanRepository, times(1)).findById(999L);
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("deleteLoan - Debe eliminar el préstamo sin retornar valor")
    void testDeleteLoan_DeletesLoan() {
        doNothing().when(loanRepository).deleteById(1L);

        loanService.deleteLoan(1L);

        verify(loanRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("getLoansByStatus - Debe filtrar préstamos por estado")
    void testGetLoansByStatus_FiltersByStatus() {
        List<Loan> pendingLoans = Arrays.asList(loan);
        when(loanRepository.findByStatus(Loan.LoanStatus.PENDING)).thenReturn(pendingLoans);

        List<LoanResponse> result = loanService.getLoansByStatus(Loan.LoanStatus.PENDING);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(Loan.LoanStatus.PENDING, result.get(0).status());
        verify(loanRepository, times(1)).findByStatus(Loan.LoanStatus.PENDING);
    }

    @Test
    @DisplayName("approveLoan - Debe aprobar un préstamo pendiente")
    void testApproveLoan_ApprovesPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.APPROVED);
            return l;
        });

        Optional<LoanResponse> result = loanService.approveLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.APPROVED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("approveLoan - Debe retornar vacío cuando el préstamo no existe")
    void testApproveLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.approveLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe rechazar un préstamo pendiente")
    void testRejectLoan_RejectsPendingLoan() {
        loan.setStatus(Loan.LoanStatus.PENDING);
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> {
            Loan l = invocation.getArgument(0);
            l.setStatus(Loan.LoanStatus.REJECTED);
            return l;
        });

        Optional<LoanResponse> result = loanService.rejectLoan(1L);

        assertTrue(result.isPresent());
        assertEquals(Loan.LoanStatus.REJECTED, result.get().status());
        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("rejectLoan - Debe retornar vacío cuando el préstamo no existe")
    void testRejectLoan_WhenNotExists_ReturnsEmpty() {
        when(loanRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<LoanResponse> result = loanService.rejectLoan(999L);

        assertFalse(result.isPresent());
        verify(loanRepository, never()).save(any(Loan.class));
    }

    @Test
    @DisplayName("getLoansByAmountRange - Debe filtrar préstamos por rango de monto")
    void testGetLoansByAmountRange_FiltersByAmount() {
        List<Loan> loans = Arrays.asList(loan);
        BigDecimal minAmount = new BigDecimal("5000.00");
        BigDecimal maxAmount = new BigDecimal("15000.00");
        when(loanRepository.findByAmountBetween(minAmount, maxAmount)).thenReturn(loans);

        List<LoanResponse> result = loanService.getLoansByAmountRange(minAmount, maxAmount);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(loanRepository, times(1)).findByAmountBetween(minAmount, maxAmount);
    }

    @Test
    @DisplayName("getLoansByClientId - Debe filtrar préstamos por ID de cliente")
    void testGetLoansByClientId_FiltersByClient() {
        List<Loan> clientLoans = Arrays.asList(loan);
        when(loanRepository.findByClientId(100L)).thenReturn(clientLoans);

        List<LoanResponse> result = loanService.getLoansByClientId(100L);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(100L, result.get(0).clientId());
        verify(loanRepository, times(1)).findByClientId(100L);
    }
}

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>
    
    <groupId>com.fintech</groupId>
    <artifactId>loans-api</artifactId>
    <version>1.0.0</version>
    <name>loans-api</name>
    <description>API REST para gestión de préstamos</description>
    
    <properties>
        <java.version>21</java.version>
    </properties>
    
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-annotations</artifactId>
            <version>2.2.21</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>io.swagger</groupId>
            <artifactId>swagger-models</artifactId>
            <version>2.2.21</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.32</version>
            <scope>provided</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>2.0.13</version>
            <scope>compile</scope>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
