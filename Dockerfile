FROM adoptopenjdk/openjdk11
COPY target/challange-0.0.1-SNAPSHOT.jar ./challange-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/challange-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080