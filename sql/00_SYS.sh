#!/bin/bash
SCRDIR=$(realpath $(dirname $0))
CURDIR=$(pwd)
cd $SCRDIR
echo "################################################"
echo DEFINE APP_USER=$APP_USER > $ORACLE_HOME/env.txt
echo DEFINE APP_USER_PASSWORD=$APP_USER_PASSWORD >> $ORACLE_HOME/env.txt
echo "################################################"
cd $CURDIR
