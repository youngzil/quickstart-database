#!/bin/sh
dir=$(dirname "$0")
# java -cp "$dir/h2-1.4.197.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Console "$@"
java -cp "$dir/h2-1.4.197.jar:$H2DRIVERS:$CLASSPATH" org.h2.tools.Server -tcpAllowOthers -webAllowOthers -webPort 8082"$@"