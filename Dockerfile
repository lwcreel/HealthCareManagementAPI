FROM ibm-semeru-runtimes:11
RUN mkdir /opt/app
COPY japp.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/japp.jar"]