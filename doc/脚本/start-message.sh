#!/bin/bash

zkServer.sh start

source ./cp-message.sh

java -cp /home/xnpeng/IdeaProjects/rxgj/rxgj-massege-service/target/classes:$cp_message com.www.mall.service.MessageService & echo $! > message.pid



