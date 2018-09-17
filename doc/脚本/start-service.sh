#!/bin/bash

zkServer.sh start

source ./cp-service.sh

java -cp /home/xnpeng/IdeaProjects/rxgj/rxgj-service/target/classes:$cp_service com.www.mall.service.UserService & echo $! > service.pid &

