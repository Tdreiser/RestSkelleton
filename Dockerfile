FROM eclipse-temurin:11
ADD /target/RestSkelleton-1.0.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]