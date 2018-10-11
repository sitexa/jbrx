#!/usr/bin/env bash

stop-service.sh
stop-msg.sh
./stop-admin
stop-api.sh

zkServer.sh stop
