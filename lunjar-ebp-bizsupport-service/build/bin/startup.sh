#####################
#!/bin/sh
#####################
echo "startup lunjar-ebp-bizsupport"

java -Xms128m -Xmx128m -Xmn32m -XX:+UseG1GC -XX:NewRatio=2 -Djava.ext.dirs=../lib -classpath ../conf com.lunjar.ebp.bizsupport.bin.Main&
