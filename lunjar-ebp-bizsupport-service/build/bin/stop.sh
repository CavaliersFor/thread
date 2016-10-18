#####################
#!/bin/sh
#####################

appName=ancun-bps-bizsupport

pid=`ps -ef|grep "com.ancun.bps.bizsupport"|gawk '$0 !~/grep/ {print $2}' |tr -s '\n' ' '`

if [ -n "$pid" ]; then
        echo "$appName pid is [$pid]"        
        kill $pid
        echo "$appName is stopped"
else
   echo "$appName is not running"
fi