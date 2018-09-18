#!/usr/bin/env bash

if [ $# -ne 4 ]; then
    echo 'usage:./start-all.sh "service-dir" "msg-dir" "api-dir" "admin-dir"'
    exit 1
fi

./start-service.sh $1
sleep 5

./start-msg.sh $2
sleep 5

./start-api.sh $3
sleep 5

./start-admin.sh $4
sleep 5
