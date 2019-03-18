#!/usr/bin/env sh

createDataDir () {
    mkdir -p $1

    chown -R vermop:vermop $1
    chmod -R 755 $1
}

createDataDir "$WORKING_DIR/activemq-data"
createDataDir "$WORKING_DIR/filedata"
createDataDir "$WORKING_DIR/log"

