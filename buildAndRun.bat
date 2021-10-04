@echo off
call mvn clean package
call docker build -t com.mycompany/Practica2 .
call docker rm -f Practica2
call docker run -d -p 9080:9080 -p 9443:9443 --name Practica2 com.mycompany/Practica2