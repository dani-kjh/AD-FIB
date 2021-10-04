#!/bin/sh
mvn clean package && docker build -t com.mycompany/Practica2 .
docker rm -f Practica2 || true && docker run -d -p 9080:9080 -p 9443:9443 --name Practica2 com.mycompany/Practica2