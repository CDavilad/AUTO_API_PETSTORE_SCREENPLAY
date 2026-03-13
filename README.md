# AUTO_API_PETSTORE_SCREENPLAY

Proyecto de automatización de pruebas para la API REST de [Swagger Petstore](https://petstore.swagger.io/v2) utilizando el **patrón Screenplay** con **Serenity BDD** y **Cucumber**.

---

## Stack Tecnológico

| Componente | Versión |
|---|---|
| Java | 17 |
| Serenity BDD | 4.2.1 |
| Serenity REST Assured | 4.2.1 |
| Cucumber | 7.20.1 |
| JUnit Platform | 1.10.2 |
| AssertJ | 3.27.7 |
| Gradle | Wrapper |

---

## Estructura del Proyecto

```
petstore/src/test/
├── java/com/screenplay/
│   ├── hooks/
│   │   └── SetupHook.java              # Hooks Before/After de cada escenario
│   ├── runners/
│   │   └── CucumberTestSuite.java      # Runner JUnit para Cucumber
│   ├── stepdefinitions/
│   │   └── PetCrudStepDefinitions.java  # Implementación de pasos Gherkin
│   ├── tasks/
│   │   ├── CreatePet.java              # POST /pet
│   │   ├── GetPet.java                 # GET /pet/{id}
│   │   ├── UpdatePet.java              # PUT /pet
│   │   └── DeletePet.java              # DELETE /pet/{id}
│   ├── questions/
│   │   ├── ResponseCode.java           # Extrae código HTTP de la respuesta
│   │   └── PetDetailsResponse.java     # Extrae campos JSON de la respuesta
│   └── models/
│       └── Pet.java                    # POJO con Category y Tag anidados
└── resources/
    ├── features/
    │   └── PetCrud.feature             # Escenario BDD en español
    ├── serenity.conf                   # Configuración de Serenity
    └── logback-test.xml                # Configuración de logging
```

---

## Patrón Screenplay

El proyecto implementa el patrón Screenplay, organizando la lógica en:

- **Tasks**: Acciones que el actor ejecuta (crear, consultar, actualizar, eliminar mascotas).
- **Questions**: Consultas sobre el estado del sistema (código HTTP, campos de la respuesta).
- **Models**: Objetos de datos que representan las entidades de la API.

### Tasks y el Principio de Responsabilidad Única (SRP)

Cada Task encapsula **una única operación** sobre la API, siguiendo el Principio de Responsabilidad Única (Single Responsibility Principle):

| Task | Responsabilidad única |
|---|---|
| `CreatePet` | Construir y enviar la petición POST para registrar una mascota |
| `GetPet` | Consultar la información de una mascota por su ID |
| `UpdatePet` | Enviar la petición PUT con los datos actualizados de una mascota |
| `DeletePet` | Eliminar una mascota por su ID |

Cada clase tiene **un solo motivo para cambiar**: si se modifica el endpoint, el body o los headers de una operación, solo se altera la Task correspondiente sin afectar a las demás. Esto garantiza:

- **Bajo acoplamiento**: las Tasks son independientes entre sí.
- **Alta cohesión**: toda la lógica de una operación HTTP vive en un solo lugar.
- **Facilidad de mantenimiento**: un cambio en la API solo impacta la Task asociada.
- **Reutilización**: cualquier escenario puede componer Tasks sin duplicar código.

---

## Escenario de Prueba

El feature `PetCrud.feature` ejecuta un ciclo CRUD completo:

1. **Crear** una mascota con nombre "Firulais" y estado "available" → valida HTTP 200.
2. **Consultar** la mascota registrada → valida nombre y estado.
3. **Actualizar** nombre a "Firulais Jr" y estado a "sold" → valida los nuevos valores.
4. **Eliminar** la mascota → valida HTTP 200.

---

## Ejecución

```bash
cd petstore

# Ejecutar todas las pruebas
./gradlew test

# Ejecutar con salida detallada
./gradlew test --info

# Ejecutar por tag
./gradlew test -Dcucumber.filter.tags="@tag"
```

---

## Reportes

Los reportes de Serenity se generan en:

```
petstore/target/site/serenity/
```

---

## Configuración

| Archivo | Descripción |
|---|---|
| `serenity.conf` | URL base (`https://petstore.swagger.io/v2`), encoding, screenshots |
| `logback-test.xml` | Nivel de log (INFO), appender de consola |
| `build.gradle` | Dependencias, plugins y configuración de compilación |