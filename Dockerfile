FROM eclipse-temurin:17.0.2_8-jre-alpine
COPY ./target/url_shortener.jar /url_shortener.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "url_shortener.jar"]
