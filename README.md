# ⚙️ Api-Taller-Bicicletas
Sistema de gestión para taller de bicicletas desarrollado en Java con Spring Boot, bajo una arquitectura monolítica. La aplicación permite registrar bicicletas, generar órdenes de trabajo, asignar servicios, gestionar estados de reparación y controlar la capacidad operativa del taller. Implementa una arquitectura en capas (Controller, Service y Repository) y utiliza MySQL como sistema de gestión de base de datos.
Este proyecto busca la organizacion del taller de una tienda de bicicletas para un mejor servicio.

## 🛠 Tecnologías

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman


## Modelo Entidad Relacion

<img width="1125" height="783" alt="image" src="https://github.com/user-attachments/assets/fef01903-05b1-4828-a0e9-f6ec42ed4f38" />

## 🏗 Arquitectura

El proyecto sigue una arquitectura monolítica en capas:

- Controller → Maneja las peticiones HTTP
- Service → Contiene la lógica de negocio
- Repository → Acceso a datos (JPA)
- Entity → Modelos de la base de datos

## Funcionalidades

- Registro de bicicletas
- Gestión de usuarios
- Creación de órdenes de trabajo
- Asignación de servicios
- Control de estado de reparaciones
- Validación de capacidad del taller

# Documentación de endpoints

## Base
- Base URL: `http://<host>:<port>/api`
- Todas las peticiones y respuestas usan JSON.

## Usuarios
| Campo | Tipo | Notas |
| --- | --- | --- |
| `id` | `Long` | Generado por el servidor. |
| `nombre` | `String` | |
| `email` | `String` | Único y obligatorio; valida formato estándar. |
| `telefono` | `String` | |
| `password` | `String` | Se persiste sin encriptar. |
| `rol` | `Enum` (`MECANICO`, `CLIENTE`) | Determina si el usuario puede ser mecanico. |
| `bicicletas` | `List<Bicicleta>` | Bicicletas asociadas; se llena cuando se consulta. |

#### `GET /api/usuario`
- Descripción: lista todos los usuarios registrados.
- Parámetros: ninguno.
- Respuesta: `200 OK` con array de usuarios completos.

#### `GET /api/usuario/{id}`
- Descripción: retorna el usuario indicado.
- Parámetros: `id` en la ruta.
- Respuesta: `200 OK` con el objeto; `404 Not Found` si no existe.

#### `POST /api/usuario`
- Descripción: crea un usuario nuevo.
- Cuerpo: JSON con `nombre`, `email`, `telefono`, `password`, `rol`.
- Respuesta: `201 Created` con el usuario persistido; `400 Bad Request` implícito si falta `email` válido o ya existe.

#### `DELETE /api/usuario/{id}`
- Descripción: elimina un usuario por id.
- Parámetros: `id` en la ruta.
- Respuesta: `204 No Content` al eliminar; `404 Not Found` si no existe.

## Bicicletas
| Campo | Tipo | Notas |
| --- | --- | --- |
| `id` | `Long` | Generado por el servidor. |
| `marca` | `String` | |
| `modelo` | `String` | |
| `tipo` | `String` | |
| `numeroSerie` | `Long` | Único. |
| `usuario` | `Usuario` | Referencia opcional al dueño. |

#### `GET /api/bicicleta`
- Descripción: lista todas las bicicletas con el dueño incluido cuando existe.
- Respuesta: `200 OK` con array de bicicletas.

#### `GET /api/bicicleta/{id}`
- Descripción: recupera una bicicleta específica.
- Respuesta: `200 OK` con bicicleta o `404 Not Found`.

#### `POST /api/bicicleta`
- Descripción: crea una bicicleta; opcionalmente incluye el campo `usuario` con `id`.
- Cuerpo: `marca`, `modelo`, `tipo`, `numeroSerie`, `usuario` (opcional).
- Respuesta: `201 Created` con la bicicleta creada.

#### `DELETE /api/bicicleta/{id}`
- Descripción: elimina la bicicleta indicada.
- Respuesta: `204 No Content`; `404 Not Found` si no existe.

#### `PUT /api/bicicleta/{idBicicleta}/usuario/{idUsuario}`
- Descripción: asigna un usuario existente a una bicicleta.
- Parámetros: `idBicicleta`, `idUsuario` en la ruta.
- Respuesta: `200 OK` con la bicicleta actualizada; `404 Not Found` si una entidad no existe.

#### `POST /api/bicicleta/desvincular-usuario`
- Descripción: remueve la relación entre bicicleta y usuario.
- Parámetros: `idBicicleta`, `idUsuario` como query params.
- Respuesta: `200 OK` con bicicleta desvinculada; `404 Not Found` si no se encuentra la relación.

## Servicios
| Campo | Tipo | Notas |
| --- | --- | --- |
| `id` | `Long` | Generado por el servidor. |
| `nombre` | `String` | |
| `descripcion` | `String` | |
| `precio` | `Long` | Valores enteros. |
| `ordenServicios` | `Set<OrdenServicio>` | Relación inversa. |

#### `GET /api/servicio`
- Descripción: listado completo de servicios.
- Respuesta: `200 OK` con array de servicios.

#### `GET /api/servicio/{id}`
- Descripción: recupera un servicio por id.
- Respuesta: `200 OK` con servicio o `404 Not Found`.

#### `POST /api/servicio`
- Descripción: crea un servicio nuevo.
- Cuerpo: `nombre`, `descripcion`, `precio`; no se acepta un `id` que ya exista.
- Respuesta: `201 Created` con el servicio persistido.

#### `PUT /api/servicio`
- Descripción: actualiza nombre, descripción y precio.
- Cuerpo: JSON con `id` y los campos a reemplazar.
- Respuesta: `200 OK` con el servicio modificado; `404 Not Found` si no existe.

#### `DELETE /api/servicio/{id}`
- Descripción: elimina un servicio existente.
- Respuesta: `204 No Content`; `404 Not Found` si no existe.

## Órdenes de trabajo
| Campo | Tipo | Notas |
| --- | --- | --- |
| `id` | `Long` | Generado por el servidor. |
| `fechaIngreso` | `Date` | Fecha en formato ISO. |
| `fechaEstimada` | `Date` | Fecha tentativa de entrega. |
| `estado` | `Enum` (`EN_PROCESO`, `ENTREGADO`) | Por defecto `null` hasta que se setea. |
| `observaciones` | `String` | Comentarios libres. |
| `bicicleta` | `Bicicleta` | Debe existir y tener un cliente. |
| `cliente` | `Usuario` | Derivado del dueño de la bicicleta. |
| `mecanico` | `Usuario` | ID pasado como query param; debe tener rol `MECANICO`. |

#### `GET /api/orden-trabajo`
- Descripción: lista las órdenes de trabajo existentes con sus relaciones.
- Respuesta: `200 OK`.

#### `GET /api/orden-trabajo/{id}`
- Descripción: obtiene una orden específica.
- Respuesta: `200 OK` o `404 Not Found`.

#### `POST /api/orden-trabajo`
- Descripción: crea una orden asociada a una bicicleta y un mecánico.
- Parámetros: `idMecanico`, `idBicicleta` en la query.
- Cuerpo: `fechaIngreso`, `fechaEstimada`, `estado` (opcional), `observaciones`.
- Comportamiento: el `cliente` se infiere del `usuario` dueño de la bicicleta.
- Respuesta: `201 Created` con la orden; `404 Not Found` si el mecánico/bicicleta no existen o el mecánico no tiene rol `MECANICO`.

#### `DELETE /api/orden-trabajo/{id}`
- Descripción: elimina una orden validando su existencia.
- Respuesta: `204 No Content` o `404 Not Found`.

## Órdenes de servicio
| Campo | Tipo | Notas |
| --- | --- | --- |
| `id` | `Long` | Generado. |
| `estado` | `Enum` (`APROBADO`, `EN_ESPERA`) | Se actualiza con `/modificar`. |
| `ordenTrabajo` | `OrdenTrabajo` | Debe referenciar una orden existente. |
| `servicios` | `Set<Servicio>` | Servicios asociados. Se añaden mediante los endpoints dedicados. |

#### `GET /api/orden-servicio`
- Descripción: lista todas las órdenes de servicio con su estado y relaciones.
- Respuesta: `200 OK`.

#### `POST /api/orden-servicio`
- Descripción: crea una orden de servicio, vincula el servicio inicial y la orden de trabajo.
- Parámetros: `idOrdenTrabajo`, `idServicio` como query params.
- Cuerpo: JSON con `estado` y opcionalmente `ordenTrabajo` (se valida con `idOrdenTrabajo`).
- Respuesta: `201 Created`; lanza errores si no se encuentra el servicio u orden de trabajo referenciados.

#### `POST /api/orden-servicio/asignar-servicio`
- Descripción: agrega un servicio adicional a una orden existente.
- Parámetros: `idOrdenServicio`, `idServicio`.
- Respuesta: `201 Created` con la orden actualizada; `404 Not Found` si alguno no existe.

#### `POST /api/orden-servicio/modificar`
- Descripción: permite actualizar el `estado` de una orden de servicio.
- Cuerpo: JSON con `id` y `estado`.
- Respuesta: `200 OK` con la orden actualizada o `404 Not Found`.

#### `DELETE /api/orden-servicio/{id}`
- Descripción: elimina la orden de servicio indicada.
- Respuesta: `204 No Content` o `404 Not Found`.

## Notas adicionales
- No hay versiones ni autenticación definidas en el controlador actual.
- Las relaciones entre entidades se manejan mediante IDs en parámetros y bodies; asegúrate de enviar objetos completos o identificadores válidos antes de crear recursos dependientes.








