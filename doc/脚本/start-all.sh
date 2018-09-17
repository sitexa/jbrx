#!/bin/bash

./start-service.sh
sleep 5

./start-api.sh
sleep 5

./start-admin.sh
sleep 5

./start-message.sh

cd