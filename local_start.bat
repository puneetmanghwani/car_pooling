@echo off
echo %CD%

pushd %~dp0


setlocal enabledelayedexpansion

set argCount=0
for %%x in (%*) do (
   set /A argCount+=1
   set "argVec[!argCount!]=%%~x"
)

::echo Number of processed arguments: %argCount%

if %argCount% NEQ 3 (
echo Need exactly 3 arguments: "<server_port> <profile> <log_level>"
exit /b 1
)






if not exist logs\NUL (
echo "Making folder logs as it doesn't exist"
mkdir logs
)

call gradlew.bat deploy --warning-mode all

set server_port=%argVec[1]%
echo %server_port%
set profile=%argVec[2]%
echo %profile%
set log_level=%argVec[3]%
echo %log_level%


set instanceId=1
set logging_dir=logs\%instanceId%
set infoLogFileName="car_pooling-all.log"
set warnLogFileName="car_pooling-warn.log"

if not exist %logging_dir%\NUL (
echo "Making folder %logging_dir% as it doesn't exist"
mkdir  %logging_dir%
)



call java11 -Dspring.cloud.config.uri=http://dev1.xswift.biz:8085 -Dspring.profiles.active=%profile% -Dlog.level=%log_level% -Dserver.port=%server_port% -Dlog.info.file.name="%logging_dir%/%infoLogFileName%" -Dlog.warn.file.name="%logging_dir%/%warnLogFileName%" -Dlog.info.file.pattern=logs/%logging_dir%/$${date:yyyy-MM}/listener-info-%%%%d{yyyy-MM-dd}-%%%%03i.log.gz Dlog.warn.file.pattern=logs/%logging_dir%/$${date:yyyy-MM}/listener-warn-%%%%d{yyyy-MM-dd}-%%%%03i.log.gz -Dconsole.pattern="%%%%d{yyyy-MM-dd HH:mm:ss.SSS} %%%%-5level --- [%%%%t]  %%%%logger{36}         : %%%%msg%%%%n" -Xmx1024m -jar build\libs\car_pooling.jar ^>%logging_dir%\out.txt 2^>%logging_dir%\err.txt
echo Starting application...

endlocal 
popd
