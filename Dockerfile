ARG GITHUB_ACTOR
ARG GITHUB_TOKEN

FROM eclipse-temurin:21-jdk AS builder

WORKDIR /build

COPY . .

RUN mkdir -p /root/.m2 && \
    echo '<settings>' > /root/.m2/settings.xml && \
    echo '  <servers>' >> /root/.m2/settings.xml && \
    echo '    <server>' >> /root/.m2/settings.xml && \
    echo '      <id>github</id>' >> /root/.m2/settings.xml && \
    echo "      <username>${GITHUB_ACTOR}</username>" >> /root/.m2/settings.xml && \
    echo "      <password>${GITHUB_TOKEN}</password>" >> /root/.m2/settings.xml && \
    echo '    </server>' >> /root/.m2/settings.xml && \
    echo '  </servers>' >> /root/.m2/settings.xml && \
    echo '</settings>' >> /root/.m2/settings.xml

RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
