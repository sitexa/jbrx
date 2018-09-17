#!/bin/bash

cat admin.pid | xargs kill -9
cat api.pid | xargs kill -9
cat service.pid | xargs kill -9
cat message.pid | xargs kill -9
