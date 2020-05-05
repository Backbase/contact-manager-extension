# Docker inheritance
FROM repo.backbase.com/backbase-docker-releases/contact-manager:DBS-2.18.0

ARG JAR_FILE
COPY target/${JAR_FILE} /app/WEB-INF/lib/

# docker run -e SIG_SECRET_KEY=JWTSecretKeyDontUseInProduction! -e EUREKA_CLIENT_ENABLED=false \
#   --name=extensiontest \
#   -v extension-vol:/target/contact-presentation-service-extension-1.0.0-SNAPSHOT.jar:ro \
#   harbor.backbase.eu/staging/contact-presentation-service:2.18.0--latest