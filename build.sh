echo `pwd`
cd "$(dirname "$0")"
sh ./gradlew deploy
return $?
