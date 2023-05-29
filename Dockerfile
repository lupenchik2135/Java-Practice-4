FROM postgres:15.2-alpine

ENV POSTGRES_USER user
ENV POSTGRES_PASSWORD bebebe
ENV POSTGRES_DB spring_tickets_postgres

EXPOSE 5432