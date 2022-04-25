#FROM openjdk:12-alpine
#
#ARG PROFILE
#ARG ADDITIONAL_OPTS
#
#ENV PROFILE=${PROFILE}
#ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}
#
#WORKDIR /opt/ItineraryDimed
#
#COPY /target/ItineraryDimed*.jar ItineraryDimed-0.0.1-SNAPSHOT.jar
#
#
#
#EXPOSE 8081
#
#CMD java ${ADDITIONAL_OPTS} -jar ItineraryDimed-0.0.1-SNAPSHOT.jar --spring.profiles.active=${PROFILE}


FROM openjdk:12-alpine
MAINTAINER angelicaweiler
COPY /target/Itinerary-0.0.1-SNAPSHOT*.jar /app/Itinerary-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "Itinerary-0.0.1-SNAPSHOT.jar"]

EXPOSE 8081
