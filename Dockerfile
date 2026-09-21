FROM amazoncorretto:25-jdk
RUN yum -y install findutils
WORKDIR /app
COPY . .
EXPOSE 8080
CMD ["./gradlew", "lib:app:bootRun", "--configuration-cache"]
