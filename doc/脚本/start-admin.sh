#!/bin/bash

zkServer.sh start

source ./cp-admin.sh

java -cp /home/xnpeng/IdeaProjects/rxgj/rxgj-admin/target/classes:$cp_admin com.www.mall.AdminApp & echo $! > admin.pid



