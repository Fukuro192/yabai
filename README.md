# yabai


Yet Another Blog Application Implementation, or: "YABAI!!!! A BLOG APPLICATION IMPLEMENTATION????!!!!"

It's a simple Blog API to learn how Spring Boot REST-API works.

## before you run

make sure you modified `src/main/resources/application.properties` for your username and password:

```
spring.datasource.username=dev_user
spring.datasource.password=UrP@55w0rd!!!
```

and write your secret secret string for the JSON Web Token generator on the same file:

```
app.jwtSecret=Ur53cr37
```

## run

you need to compile the maven project into a `jar`-file:

```bash
mvn clean install
```

then run:

```bash
java -jar target/risha-0.0.1-SNAPSHOT.jar
```

## example

when you register as a new user:

```
POST :8080/auth/register username=fukuro192 email=fukuro192@example.com password=1234
```

you get this result with http 200 status:

```json
{
    "email": "fukuro192@example.com",
    "username": "fukuro192",
    "uuid": "404fa45d-43bc-4c9a-877c-cb8141c60745"
}
```

then you login:

```
POST :8080/auth/login username=fukuro192 password=1234
```

the result would be a Jwt(Json Web Token):

```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmdWt1cm8xOTIiLCJpYXQiOjE2NjMyNDIzNTAsImV4cCI6MTY2Mzg0NzE1MH0.BlBd5_Heh9bdx5wY92O7txebp-kcC6vscZ88HnArIZ4PpGWL4dNXaT9TehpSuzkUpgFFvgQWUsT2mrOUDYsnnw
```

finally, to send any command, you add the Jwt as a Bearer like this:


```
POST :8080/post/add Authorization:Bearer:<your_token> text=<your_text>
```

the result

```json
{
    "creationDate": "2022-09-15T11:50:07.109+00:00",
    "text": "<your_text>",
    "uuid": "2f0f3e39-121c-432c-8583-907529097979"
}
```