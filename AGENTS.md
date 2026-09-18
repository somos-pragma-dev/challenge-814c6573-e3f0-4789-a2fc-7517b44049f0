# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de API REST con persistencia en H2 y documentación Swagger**.

| | |
|---|---|
| Tema | Java Spring Boot |
| Nivel | junior-l2 |
| Chapter | Generico |
| Especialidad | Inferido del contexto |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | capas estándar (Controller-Service-Repository) |
| Tiempo estimado | 8 horas |

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `el comando de build o arranque canonico del stack elegido` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `el comando de build o arranque canonico del stack elegido` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de la estructura de la API**: Especificación de las rutas y métodos HTTP para las operaciones CRUD de préstamos.
- **Fase 2 — Implementación de la persistencia en H2**: Implementación de la persistencia de los préstamos en H2 y métodos de servicio para las operaciones CRUD.
- **Fase 3 — Documentación con Swagger**: Documentación completa de la API utilizando Swagger.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Boilerplate del stack (1)

Sin esto el proyecto no compila ni arranca. **Es tu trabajo crearlo**, y no toca nada de lo pedagogico: es andamiaje del stack.

- [ ] **Punto de entrada del stack elegido** — Sin un punto de entrada reconocible, el runtime no tiene por donde arrancar la aplicacion.

### 2. Referencias colgando (26)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/loansapi/repository/LoanRepository.java` — `LoanStatus`
      LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java` — `LoanStatus`
      LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java` — `LoanStatus`
      LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanStatus`
      LoanStatus se usa en el cuerpo del archivo pero no esta importado. El proyecto lo declara en com.fintech.loansapi.model.entity.LoanStatus (hay mas de un tipo con ese nombre en el proyecto).
- [ ] `src/main/java/com/fintech/loansapi/service/LoanService.java` — `Loan`
      El import com.fintech.loansapi.model.entity.Loan no se usa en ningun lado del cuerpo del archivo. Se puede eliminar.
- [ ] `src/test/java/com/fintech/loansapi/controller/LoanControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findAll`
      Se invoca `findAll` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findById`
      Se invoca `findById` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.create`
      Se invoca `create` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.update`
      Se invoca `update` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.delete`
      Se invoca `delete` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByStatus`
      Se invoca `findByStatus` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByAmountBetween`
      Se invoca `findByAmountBetween` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/controller/LoanController.java` — `LoanService.findByClientId`
      Se invoca `findByClientId` sobre `LoanService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loansapi/service/LoanServiceImpl.java` — `LoanRequest.clientId`
      Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.size`
      Se invoca `size` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.get`
      Se invoca `get` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.isPresent`
      Se invoca `isPresent` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.amount`
      Se invoca `amount` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.interestRate`
      Se invoca `interestRate` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.durationMonths`
      Se invoca `durationMonths` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.clientId`
      Se invoca `clientId` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanRequest.description`
      Se invoca `description` sobre `LoanRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.id`
      Se invoca `id` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.amount`
      Se invoca `amount` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java` — `LoanResponse.status`
      Se invoca `status` sobre `LoanResponse`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (16)

- `pom.xml`
- `src/main/java/com/fintech/loansapi/LoansApiApplication.java`
- `src/main/resources/application.properties`
- `src/main/java/com/fintech/loansapi/repository/LoanRepository.java`
- `src/main/java/com/fintech/loansapi/model/entity/Loan.java`
- `src/main/java/com/fintech/loansapi/model/dto/LoanRequest.java`
- `src/main/java/com/fintech/loansapi/model/dto/LoanResponse.java`
- `src/main/java/com/fintech/loansapi/controller/LoanController.java`
- `src/main/java/com/fintech/loansapi/service/LoanService.java`
- `src/main/java/com/fintech/loansapi/exception/LoanNotFoundException.java`
- `src/main/java/com/fintech/loansapi/exception/GlobalExceptionHandler.java`
- `src/main/java/com/fintech/loansapi/service/LoanServiceImpl.java`
- `src/main/java/com/fintech/loansapi/config/OpenApiConfig.java`
- `src/test/java/com/fintech/loansapi/controller/LoanControllerTest.java`
- `src/test/java/com/fintech/loansapi/service/LoanServiceTest.java`
- `src/main/java/com/fintech/loansapi/model/entity/LoanStatus.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/loansapi`
- `src/main/java/com/fintech/loansapi/controller`
- `src/main/java/com/fintech/loansapi/service`
- `src/main/java/com/fintech/loansapi/repository`
- `src/main/java/com/fintech/loansapi/model/dto`
- `src/main/java/com/fintech/loansapi/model/entity`
- `src/main/java/com/fintech/loansapi/exception`
- `src/main/java/com/fintech/loansapi/config`
- `src/main/resources`
- `src/test/java/com/fintech/loansapi`

## Verificacion

```bash
el comando de build o arranque canonico del stack elegido
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar (Controller-Service-Repository)**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Brecha que el reto ataca: Crear una API REST con persistencia en H2 y documentación con Swagger

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
