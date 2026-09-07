FROM maven:3.9.9-openjdk-25

WORKDIR /spring
COPY . .
RUN mvn clean install

CMD "mvn", "spring-boot:run"