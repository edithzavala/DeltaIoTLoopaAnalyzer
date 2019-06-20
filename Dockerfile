FROM java:8
VOLUME /tmp
EXPOSE 8092
ADD /build/libs/DeltaIoTLoopaAnalyzer.jar DeltaIoTLoopaAnalyzer.jar
ENTRYPOINT ["java","-jar","DeltaIoTLoopaAnalyzer.jar"]
