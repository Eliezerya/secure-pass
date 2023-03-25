FROM amazoncorretto:17
EXPOSE 8081
ADD target/secure-pass.jar secure-pass.jar
ENTRYPOINT ["java", "-jar", "/secure-pass.jar"]