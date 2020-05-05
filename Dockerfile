# Docker inheritance
FROM repo.backbase.com/backbase-docker-releases/contact-manager:DBS-2.18.0

ARG JAR_FILE
COPY target/${JAR_FILE} /app/WEB-INF/lib/