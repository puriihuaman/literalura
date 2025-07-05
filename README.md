# Api Rest Literalura

Este sistema expone una API RESTful que permite gestionar y consultar información relacionada con libros, autores,
traductores, entre otros recursos.

![Documentación de Swagger](/src/main/resources/static/doc-swagger.png)

## Características del sistema

Los clientes pueden:

- Obtener el detalle de un libro específico mediante su identificador.
- Acceder a listados de libros con soporte para paginación y ordenamiento.
- Realizar búsquedas filtradas por múltiples parámetros, facilitando una experiencia flexible y eficiente de consulta
  de datos.

## Instalación y configuración

1. **Clonar el repositorio:**

```
git clone https://github.com/puriihuaman/api-electro.git
cd api-electro
```

2. **Configurar la base de datos:**
    - Crear una base de datos en MySQL.
        - Indicar el nombre de la base de datos.
3. **Configurar las variables de entorno:**
    - Crear un fichero (`.env`) igual al fichero `.env.example` con los mismos valores.
        ```
        JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/db_name?serverTimezone=UTC
        JDBC_DATABASE_NAME=db_name
        JDBC_DATABASE_USERNAME=your_username
        JDBC_DATABASE_PASSWORD=your_password
        ```
4. **Configurar las propiedades:**
    - En el fichero `application.properties` actualizar las propiedades con los valores de las variables de entorno.
        ```
        spring.datasource.url=${JDBC_DATABASE_URL}
        spring.datasource.username=${JDBC_DATABASE_USERNAME}
        spring.datasource.password=${JDBC_DATABASE_PASSWORD}
        ```

5. **Construir el proyecto:**
   ```
   mvn clean install
   ```
6. **Ejecutar la aplicación:**
   ```
   mvn spring-boot:run
   ```
7. **Realizar peticiones**
    - Con aplicación en ejecución puede realizar las peticiones a los siguientes `endpoints` indicado en el 
      siguiente paso ().
    - _Recomendación_: Primero hacer las peticiones `POST`.

## Endpoints principales

| Método | Ruta                                        | Descripción                                                                         |
|--------|---------------------------------------------|-------------------------------------------------------------------------------------|
| POST   | `/api/books/save/book`                      | Guarda la primera página.                                                           |
| POST   | `api/books/save/book?page=7`                | Guarda los libros de la página 7 (la página puede variar).                          |
| GET    | `api/books`                                 | Retorna un listado de libros.                                                       |
| GET    | `api/books/{id}`                            | Retorna un libro.                                                                   |
| GET    | `api/books/title/{title}`                   | Retorna un listado de libros que coincida con el título.                            |
| GET    | `api/books?title={book-title}`              | Filtra los libros que coincida con el título.                                       |
| GET    | `api/authors`                               | Retorna un listado de autores.                                                      |
| GET    | `api/authors/{id}`                          | Retorna un libro.                                                                   |
| GET    | `api/authors/name/{name}`                   | Retorna un listado de autores que coincida con el nombre.                           |
| GET    | `api/authors?birthYear={1835}&page={2}`     | Filtra los autores que coincida con el año del cumpleaños y la página indicada.     |
| GET    | `api/translator`                            | Retorna un listado de traductores.                                                  |
| GET    | `api/translators/{id}`                      | Retorna un traductor.                                                               |
| GET    | `api/translators/name/{name}`               | Retorna un listado de traductores que coincida con el nombre.                       |
| GET    | `api/translators?birthYear={1835}&page={2}` | Filtra los traductores que coincida con el año del cumpleaños y la página indicada. |
| GET    | `api/languages`                             | Retorna un listado de lenguajes.                                                    |
| GET    | `api/languages/{code}`                      | Retorna un lenguaje que coincida con el código.                                     |

### Languages

- Consultar languages: `GET /api/languages`
- Consultar traductor: `GET /api/languages/{code}`

## Contribuciones

Si deseas contribuir al proyecto, por favor sigue estos pasos:

Haz un fork del repositorio.
Crea una rama nueva para tu funcionalidad o mejora.
Envía un pull request con una descripción detallada de los cambios.