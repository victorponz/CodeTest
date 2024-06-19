FROM openjdk:17-alpine3.14

WORKDIR /usr/bin
COPY lib/junit.jar ./

WORKDIR /
COPY lib ./
COPY CodeTest.jar ./
# COPY *.java ./
ENTRYPOINT ["java", "-jar", "CodeTest.jar"]
CMD ["aqui-va-el-script", "aqui-va-el-directorio-para-resultados"]
