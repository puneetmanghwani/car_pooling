#!/bin/sh
#Do validations, there should be at least 3 arguments

#We need web port numer, class, and log level


if [ $# -lt 3 ]
then
       echo "usage: ./$0 <server_port> <fully qualifed class name> <desired loglevel> "
       exit 1
fi



server_port=$1
class_name=$2
loglevel=$3

url="http://localhost:$server_port/actuator/loggers/$class_name"
body="{\"configuredLevel\":\"$loglevel\",\"effectiveLevel\":\"$loglevel\"}"


curl --location --request POST "$url" --header 'Content-Type: application/json' --data-raw "$body"

echo "The log level is now:"
curl --location --request GET "http://localhost:$server_port/actuator/loggers/$class_name"
echo ""
