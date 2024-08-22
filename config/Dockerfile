
# Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
# Click nbfs://nbhost/SystemFileSystem/Templates/Other/Dockerfile to edit this template

FROM alpine/java:21.0.3-jre
RUN apk update && apk add --no-cache curl
WORKDIR /app
COPY target/config-0.0.1-SNAPSHOT.jar /app/app.jar
RUN mkdir config-repo
COPY src/main/resources/config-repo /app/config-repo
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["/bin/sh"]
