#!/usr/bin/env bash

start-service.sh
sleep 5

start-msg.sh
sleep 5

start-api.sh
sleep 5

./start-admin
sleep 5
