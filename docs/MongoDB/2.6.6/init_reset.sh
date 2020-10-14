#!/bin/bash
export PATH=${HOME}/mongodb/app/mongodb-linux-x86_64-2.6.6/bin:$PATH
PORT=35010
MongoDB="mongo admin --port $PORT"
$MongoDB<<EOF
var config={
                "_id":"repset",
                "members":[
                        {_id:0,host:"20.26.37.176:35010"},
                        {_id:1,host:"20.26.37.177:35011"},
                        {_id:2,host:"20.26.37.178:35012"}

]
        }
rs.initiate(config)
exit
EOF
