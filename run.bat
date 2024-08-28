@echo off
set MAVEN_OPTS=-Xmx1024m

call mvn clean package > build.log 2>&1
java -jar target\CropWateringList-1.0-SNAPSHOT-jar-with-dependencies.jar
pause
