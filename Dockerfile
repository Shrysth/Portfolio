FROM openjdk
WORKDIR /app
COPY target/Shrysth-0.0.1-SNAPSHOT.jar /app/Shrysth-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT [ "java","-jar","Shrysth-0.0.1-SNAPSHOT.jar" ]