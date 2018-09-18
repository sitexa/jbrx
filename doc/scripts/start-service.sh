#!/usr/bin/env bash

if [ $# -ne 1 ]; then
    echo "usage:./start-service.sh 'root directory' like /home/xnpeng/IdeaProjects/rxgj/rxgj-service"
    exit 1
fi

zkServer.sh start

java_lib=`find $JAVA_HOME/jre/lib -name *.jar|xargs|sed "s/ /:/g"`
jar_lib=`find $1/lib -name *.jar|xargs|sed "s/ /:/g"`
cp=$java_lib:$jar_lib

java -cp $cp:$1/target/classes com.www.mall.service.UserService & echo $! > service.pid
