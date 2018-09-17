#!/bin/bash

zkServer.sh start

source ./cp-api.sh

java -cp /home/xnpeng/IdeaProjects/rxgj/rxgj-api/target/classes:$cp_api com.www.mall.ApiApp & echo $! > api.pid
