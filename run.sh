#!/bin/bash
if [ ! -f target/pedija-1.0-SNAPSHOT.jar ]; then
    echo "Compilando o projeto pela primeira vez..."
    mvn clean package -q
fi
java -jar target/pedija-1.0-SNAPSHOT.jar
