# Desarrollo de API REST con persistencia en H2 y documentación Swagger

El equipo de desarrollo de una fintech necesita implementar una API REST para gestionar operaciones de préstamo. La API debe permitir la creación, lectura, actualización y eliminación de préstamos. Los préstamos se almacenarán en una base de datos H2 y se documentarán utilizando Swagger para facilitar la comprensión y uso por parte de los desarrolladores externos. Los préstamos tienen los siguientes atributos: ID (único), monto, tasa de interés, duración (en meses) y estado (pendiente, aprobado, rechazado). La API debe manejar correctamente los errores de validación y proporcionar una respuesta adecuada en caso de fallos.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Java Spring Boot |
| **Nivel** | junior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de la estructura de la API

**Objetivo:** Definir la estructura básica de la API REST y establecer las rutas necesarias para las operaciones CRUD de préstamos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las rutas y métodos HTTP necesarios para las operaciones CRUD de préstamos.
- Establecer los criterios de aceptación para cada ruta, incluyendo los códigos de estado HTTP apropiados y los mensajes de respuesta esperados.

**Entregable:** Especificación de las rutas y métodos HTTP para las operaciones CRUD de préstamos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera los diferentes estados de un préstamo y cómo deben ser manejados por la API.
- Piensa en los posibles errores de validación y cómo deben ser comunicados al cliente.

</details>

### Fase 2: Implementación de la persistencia en H2

**Objetivo:** Implementar la persistencia de los préstamos en una base de datos H2 y asegurar que la API pueda crear, leer, actualizar y eliminar préstamos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar la conexión a la base de datos H2.
- Definir el modelo de datos para los préstamos y establecer las relaciones necesarias.
- Implementar los métodos de servicio para las operaciones CRUD de préstamos.

**Entregable:** Implementación de la persistencia de los préstamos en H2 y métodos de servicio para las operaciones CRUD.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la estructura de la base de datos y cómo se relaciona con el modelo de datos de los préstamos.
- Piensa en cómo manejar los errores de persistencia y cómo comunicarlos al cliente.

</details>

### Fase 3: Documentación con Swagger

**Objetivo:** Documentar la API utilizando Swagger para facilitar la comprensión y uso por parte de los desarrolladores externos.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar Swagger en el proyecto.
- Definir las anotaciones necesarias para documentar las rutas y métodos de la API.
- Probar la documentación generada por Swagger y asegurar que sea clara y completa.

**Entregable:** Documentación completa de la API utilizando Swagger.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la claridad y completitud de la documentación generada por Swagger.
- Piensa en cómo mejorar la experiencia del usuario al utilizar la documentación.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es una API REST y cómo se utiliza en el contexto de la gestión de préstamos?
- **paraQueSirve**: ¿Para qué sirve la documentación Swagger en el contexto de esta API?
- **comoSeUsa**: ¿Cómo se utilizan las bases de datos H2 para la persistencia de datos en esta API?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir al implementar una API REST con persistencia en H2 y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la implementación de esta API REST con persistencia en H2 y documentación Swagger?

## Criterios de Evaluacion

- Definición correcta de las rutas y métodos HTTP para las operaciones CRUD de préstamos.
- Implementación correcta de la persistencia de los préstamos en H2.
- Documentación completa y clara de la API utilizando Swagger.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
el comando de build o arranque canonico del stack elegido
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
