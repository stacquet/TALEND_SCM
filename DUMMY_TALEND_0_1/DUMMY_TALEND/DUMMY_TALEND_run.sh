#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Xms256M -Xmx1024M -cp classpath.jar: hism.dummy_talend_0_1.DUMMY_TALEND --context=Default "$@" 