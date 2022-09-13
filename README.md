# yabai


Yet Another Blog Application Implementation, or: "YABAI!!!! A BLOG APPLICATION IMPLEMENTATION????!!!!"

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
