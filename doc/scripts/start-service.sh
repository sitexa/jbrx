#!/usr/bin/env bash

zkServer.sh start

if [ $# == 0 ]; then
    home="/home/xnpeng/IdeaProjects/rxgj/rxgj-impl"
elif [$# == 1 ];then
    home=$1
else
    echo "usage:./start-impl 'root directory' like /home/xnpeng/IdeaProjects/rxgj/rxgj-impl"
    exit 1
fi

echo $home

java_lib=`find $JAVA_HOME/jre/lib -name *.jar|xargs|sed "s/ /:/g"`
jar_lib=`find $home/lib -name *.jar|xargs|sed "s/ /:/g"`
cp=$java_lib:$jar_lib:$home/target/classes

java -cp $cp com.www.mall.service.UserService & echo $! > service.pid
