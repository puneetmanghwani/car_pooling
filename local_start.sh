#!/bin/sh
#We compulsorily need the parameters in server.port, and profile,loglevel
if [ $# -ne 4 ]
then
       echo "usage: ./$0 <server_port> <profile>  <logLevel> <instance_id>"
       exit 1
fi




server_port=$1
profile=$2
logLevel=$3
instanceID=$4






sh ./gradlew bootJar





# Logging_dir is for localhost. 

sizeOfLogFile="1024 MB"
rollOverFiles=1000
logging_dir=/var/log/javaapps/car_pooling/$instanceID

mkdir -p $logging_dir
. /etc/profile

infoLogFileName="car_pooling-all.log"
warnLogFileName="car_pooling-warn.log"
kafkaLogFileName="car_pooling-kafka.log"



nohup java -Dspring.cloud.config.uri=http://dev1.xswift.biz:8085 -Dspring.cloud.config.username=root -Dspring.cloud.config.password=s3cr3t  -Dspring.profiles.active=$profile  -Dlog.level=$logLevel -Dmax.file.size="$sizeOfLogFile" -Dlog.info.file.name="$logging_dir/$infoLogFileName" -Dlog.warn.file.name="$logging_dir/$warnLogFileName" -Dlog.kafka.file.name="$logging_dir/$kafkaLogFileName" -Dlog.info.file.pattern="$logging_dir/%d{yyyy-MM}/car_pooling-all-%d{yyyy-MM-dd}-%03i.log.gz" -Dlog.warn.file.pattern="$logging_dir/%d{yyyy-MM}/car_pooling-warn-%d{yyyy-MM-dd}-%03i.log.gz" -Dlog.kafka.file.pattern="$logging_dir/%d{yyyy-MM}/car_pooling-kafka-%d{yyyy-MM-dd}-%03i.log.gz" -Drollover.files=$rollOverFiles -Dserver.port=$server_port  -Xmx1024m -jar ./build/libs/car_pooling.jar  > $logging_dir/out.txt 2>$logging_dir/err.txt  &
echo $! > $logging_dir/pid.txt

if [ ! -f $logging_dir/$infoLogFileName ]
	then 
        echo "Waiting for file to get created"
        sleep 5
else
	echo "Log file present, displaying"
fi
sleep 5
timeout 30 tail -f $logging_dir/$infoLogFileName
exit 0
#Todo add condition for server up - also needs work in java. 
