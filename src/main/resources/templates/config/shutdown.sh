#!/bin/bash

PROJECTNAME=[(${projectName})]

pid=`ps -ef |grep $PROJECTNAME |grep -v "grep" |awk '{print $2}' `

if [ $pid ]; then
    echo "$PROJECTNAME is  running  and pid=$pid"

    kill -9 $pid

    if [(${LEFT_BRACKET})][(${LEFT_BRACKET})] $? -eq 0 [(${RIGHT_BRACKET})][(${RIGHT_BRACKET})];then

       echo "sucess to stop $PROJECTNAME "

    else

       echo "fail to stop $PROJECTNAME "

     fi

fi