# Proyecto prueba de notas y estudiantes

Este es un proyecto desarrollado bajo el framework de spring usando api's rest para exponer el listado y guardado de notas con respecto a un estudiante en específico. Se han añadido ciertas pruebas unitarias que se hicieron mediante TDD. Como base de datos se esta usando H2 por temas de comodidad en el desarrollo.

Para obtener un JWT y poder acceder a los recursos del api se tiene que hacer una petición GET al siguiente endpoint

```
http://localhost:8080/api/obtenerToken
```
se le tiene que enviar en el cuerpo de la petición un objeto de tipo usuario que solo tiene dos valores como se muestra a continuación:

```
{
    "usuario": "italo",
    "clave": "12345"
}
```
Para esto mediante el uso de Spring Security se ha definido como filtro el uso de un token de acceso, los usuarios han sido definidos en la implementación de la interfaz UserDetailsService y estan almacenados en la base de datos embebida H2 en la cual tambien se estan guardando los cursos, más detalle se ve en el archivo import.sql:

```
"usuario": "italo","clave": "12345"
"usuario": "luna","clave": "12345"
"usuario": "admin","clave": "12345"
```

Se tiene un endpoint para el listado de notas de los estudiantes al cual se le tiene q pasar en el header el token generado en base al estudiante del cual se quieren ver las notas, de lo contrario va a botar un 403 con un mensaje, y como pathvariable el id del usuario de la bd, hay validaciones con respecto a si no existe el estudiante o notas del estudiante.

```
localhost:8080/api/obtenerNotas/{idUsuario}
```

Se tiene un endpoint para la creacion de notas de un estudiante en particular, con sus respectivas validaciones, por el cuerpo de la peticion se le tiene que mandar el listado de notas y un pathvariable indicando el estudiante, a esta api solo pueden acceder los estudiantes con rol admin

```
localhost:8080/api/guardarNota/{idUsuario}
```

