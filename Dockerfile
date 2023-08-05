FROM azul/zulu-openjdk-alpine:11.0.20

WORKDIR /app

COPY ./build/libs/products-ms-0.0.1-SNAPSHOT.jar .

EXPOSE 9002

ADD ./build/libs/products-ms-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
