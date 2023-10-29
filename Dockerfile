FROM openjdk:17

COPY ./target/ProgressSoftTask-1.0-SNAPSHOT.jar ProgressSoftTask-1.0-SNAPSHOT.jar


CMD ["sh", "-c", "java -jar ProgressSoftTask-1.0-SNAPSHOT.jar"]


