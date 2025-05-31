FROM eclipse-temurin:17-jdk-jammy

# Install useful DNS + network tools for debugging
RUN apt-get update && \
    apt-get install -y dnsutils iputils-ping curl && \
    rm -rf /var/lib/apt/lists/*

# Ensure proper DNS behavior inside JVM
RUN echo "hosts: files dns" > /etc/nsswitch.conf

WORKDIR /service-a

COPY target/bitcoin-tracker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 80

ENTRYPOINT ["java", "-Dsun.net.spi.nameservice.provider.1=dns,sun", "-jar", "app.jar"]