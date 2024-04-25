FROM openjdk:17-jdk
WORKDIR /home
COPY build/libs/*.jar /home/app.jar
ENTRYPOINT ["java","-jar","/home/app.jar"]