FROM openjdk:17-alpine3.14
# Queremos que el usuario sea igual que el del anfitrión para que los
# archivos creados sean de dicho usuario y no de root
#####################################################
# docker build --build-arg HOST_USER=$(whoami) --build-arg HOST_UID=$(id -u) --build-arg HOST_GID=$(id -g) -t codetest .
######################################################
# Define build arguments for the host user, user ID, and group ID
ARG HOST_USER
ARG HOST_UID
ARG HOST_GID

# Install necessary packages to create a new user and group
# docker build --build-arg HOST_USER=$(whoami) --build-arg HOST_UID=$(id -u) --build-arg HOST_GID=$(id -g) -t codetest .
RUN apk update && apk add --no-cache sudo

# Create a new group with the same GID as the host user
RUN addgroup -g ${HOST_GID} ${HOST_USER} || addgroup -S -g ${HOST_GID} ${HOST_USER}

# Create a new user with the same username and user ID as the host user
RUN adduser -D -u ${HOST_UID} -G ${HOST_USER} -s /bin/sh ${HOST_USER}

WORKDIR /usr/bin
COPY lib/junit.jar ./

WORKDIR /
COPY lib ./
COPY CodeTest.jar ./

# Switch to the new user
USER ${HOST_USER}

ENTRYPOINT ["java", "-jar", "CodeTest.jar"]
CMD ["aqui-va-el-script", "aqui-va-el-directorio-para-resultados"]
