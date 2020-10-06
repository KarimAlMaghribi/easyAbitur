# we'll be using the minimal openjdk 9 image as a basis
FROM openjdk:8-alpine

# copy the pre-generated jar files
WORKDIR /app
COPY target/loga3-joboffer-service-1.0.0-SNAPSHOT.jar /app/loga3-joboffer-service-1.0.0-SNAPSHOT.jar

EXPOSE 8252

# and finally, set the service to be started
CMD ["java","-jar","/app/loga3-joboffer-service-1.0.0-SNAPSHOT.jar"]
