#!/usr/bin/env bash

if [ $# == 0 ]; then
    home="/home/xnpeng/IdeaProjects/rxgj/rxgj-admin"
elif [$# == 1 ];then
    home=$1
else
    echo "usage:./start-admin 'root directory' like /home/xnpeng/IdeaProjects/rxgj/rxgj-admin"
    exit 1
fi

echo $home

java_lib=`find $JAVA_HOME/jre/lib -name *.jar|xargs|sed "s/ /:/g"`
jar_lib=`find $home/lib -name *.jar|xargs|sed "s/ /:/g"`
cp=$java_lib:$jar_lib:$home/target/classes

echo $cp

java -cp $cp com.www.mall.AdminApp & echo $! > admin.pid



