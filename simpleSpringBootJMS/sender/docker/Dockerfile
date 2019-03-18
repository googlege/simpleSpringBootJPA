FROM openjdk:11.0-jdk-slim
MAINTAINER Vermop

ENV WORKING_DIR /home/vermop

WORKDIR ${WORKING_DIR}

#Harden the container with unsetting the SUID flag
RUN for i in `find / -perm +600 -type f`; do chmod a-s $i; done

RUN addgroup --gid 900 vermop
RUN adduser --system --uid 900 --gid 900 --shell /bin/sh --no-create-home --disabled-password vermop
RUN mkdir -p ${WORKING_DIR}

VOLUME ["${WORKING_DIR}/activemq-data", "${WORKING_DIR}/filedata"]

EXPOSE 8080
ENTRYPOINT ["entrypoint.sh"]

COPY docker/entrypoint.sh /usr/local/bin
RUN chmod 500 /usr/local/bin/entrypoint.sh && chown vermop:vermop /usr/local/bin/entrypoint.sh && ln -s /usr/local/bin/entrypoint.sh


