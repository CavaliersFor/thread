@echo off
echo mvn package lunjar-ebp-bizsupport...

cd ..
call mvn clean package -Dmaven.test.skip=true

echo ant package
cd build
call ant

call cmd

