#!/usr/bin/env bash

if [ $# -ne 1 ]; then
    echo "usage:./start-admin.sh 'root directory' like /home/xnpeng/IdeaProjects/rxgj/rxgj-admin"
    exit 1
fi

java_lib=`find $JAVA_HOME/jre/lib -name *.jar|xargs|sed "s/ /:/g"`
jar_lib=`find $1/lib -name *.jar|xargs|sed "s/ /:/g"`
cp=$java_lib:$jar_lib

java -cp $cp:$1/target/classes com.www.mall.AdminApp & echo $! > admin.pid



