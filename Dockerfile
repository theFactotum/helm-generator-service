FROM adoptopenjdk/openjdk11:latest

RUN mkdir /opt/app

COPY template/deployments.yml /opt/app/deployments.yml
COPY target/hem-generator-service-0.0.1-SNAPSHOT.jar /opt/app/helm-generator-service-0.0.1.jar

CMD ["java", "-jar", "/opt/app/helm-generator-service-0.0.1.jar"]



ENTRYPOINT ["java","-jar","/opt/app/helm-generator-service-0.0.1.jar"]
