# Proyecto prueba de notas

Este es un proyecto desarrollado bajo el framework de spring usando api's rest para exponer el listado y guardado de notas con respecto a un estudiante en específico.

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
Para esto mediante el uso de Spring Security se ha definido como filtro el uso de un token de acceso, los usuarios definidos han sido declarados de manera estática en la implementación de la interfaz UserDetailsService, de esta manera se han definido dos usuarios con diferentes tipso de rol en esa clase:

```
"usuario": "italo","clave": "12345"
"usuario": "pedro","clave": "12345"
```

