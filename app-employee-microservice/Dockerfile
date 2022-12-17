FROM openjdk:11
ARG JAR_FILE
ENV JAVA_OPTS=""
ADD target/app-employee-microservice-1.0.jar /home/app.jar
WORKDIR /home/
CMD java -jar $JAVA_OPTS app.jar