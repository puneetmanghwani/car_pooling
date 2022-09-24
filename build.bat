echo %CD%

pushd %~dp0
call gradlew.bat deploy --warning-mode all

IF %ERRORLEVEL% NEQ 0 ( 
exit /b errorlevel 
)
echo Built successfully
popd
