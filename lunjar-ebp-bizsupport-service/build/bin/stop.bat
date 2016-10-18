@echo off
echo stop ancun-bps-bizsupport
 
java -Djava.ext.dirs=../lib -classpath ../conf com.ancun.bps.bizsupport.bin.Main stop
