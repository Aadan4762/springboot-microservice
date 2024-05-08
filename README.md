# Keycloak + Api gateway + Springboot.
i implemented security on springboot microservice application using Keycloak. the request from the API gateway is dericted to Keycloak for authentication and authorisation,
when verified then the request is routed to specific service, where it's predicate has been used in the request sent from the frontend/client.
We also covered Distributed tracing using zipkin,Service Registry registration for registering service that is Running this is done by the help of Netflix Eureka.
config service is used to write all the configurations for other service and also most common used configurations in it, so that other services fetch it's configs from it.
