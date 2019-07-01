#!/bin/bash
export PATH=${HOME}/mongodb/app/mongodb-linux-x86_64-2.6.6/bin:$PATH
ROUTE_PORT=38200
MASTERIP=20.26.37.176
MONGO_PORT=35010
MongoDB="mongo admin --port $ROUTE_PORT"
$MongoDB<<EOF
db.runCommand({addshard:"repset/$MASTERIP:$MONGO_PORT"})
db.runCommand({enablesharding:"test"})
exit
EOF
