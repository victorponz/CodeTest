FROM openjdk:17-alpine3.14

WORKDIR /usr/bin
COPY lib/junit.jar ./

WORKDIR /application/entrada
COPY lib ./
ENTRYPOINT ["java", "-jar", "Executor.jar"]
CMD ["aqui-va-el-script", "aqui-va-el-directorio-para-resultados"]
