#####################
#!/bin/sh
#####################

echo "mvn package lunjar-bps-bizsupport ..."

cd ..
mvn clean package -Dmaven.test.skip=true  -P release

echo ant package
cd build
ant


