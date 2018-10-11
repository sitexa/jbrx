#!/usr/bin/env bash

cat service.pid | xargs kill -9

cat /tmp/zookeeper/zookeeper_server.pid |xargs kill -9

