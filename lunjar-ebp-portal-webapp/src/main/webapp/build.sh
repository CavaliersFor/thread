#!/usr/bin
# edit on 2015-05-09

# get arguments
type=$1

# define arguments
# dev tomcat path
# dev_tomcat_on='/Users/haw/taobao/TAE_Cloud_SDK/tae-dev/bin/startup.sh'
# dev_tomcat_off='/Users/haw/taobao/TAE_Cloud_SDK/tae-dev/bin/shutdown.sh'
# root=/Users/haw/taobao/TAE_Cloud_SDK/tae-dev/webapps/ROOT
# E:\DevTools\apache-tomcat-7.0.56
# dev_tomcat_on='/usr/local/opt/tomcat/libexec/bin/startup.sh'
# dev_tomcat_off='/usr/local/opt/tomcat/libexec/bin/shutdown.sh'
# root=/usr/local/opt/tomcat/libexec/webapps/ROOT
dev_tomcat_on='E:\\DevTools\\apache-tomcat-8.5.4\\bin\\startup.sh'
dev_tomcat_off='E:\\DevTools\\apache-tomcat-8.5.4\\bin\\shutdown.sh'
root='E:\\DevTools\\apache-tomcat-8.5.4\\webapps\\ROOT'

psid=0
killpid() {
	tomcatps=`ps -ef | grep $1 | grep java`

	if [ -n '$tomcatps' ]; then
		psid=`echo $tomcatps | awk '{print $2}'`
	else
		psid=0
	fi
	echo $psid
	kill -9 $psid
}

# run tasks (dev, test, dist, service)
if [ "$type" = "-d" ]; then
	# build dev, run dev service 
	echo '正在启动开发服务....'

	killpid tomcat

	rm -rf ~/.m2/repository/com/lunjar	

	# cd ..
	cd ../../../

	# 编译java文件
	mvn clean package -Dmaven.test.skip=true

	#
	rm -rf $root
	mkdir $root
	#cp -rf conf/* $root/WEB-INF/classes
	cp -rf target/lunjar-ebp-portal-webapp-1.0.0/* $root

	cd E:\\DevTools\\apache-tomcat-8.5.4\\bin
	sh $dev_tomcat_on

	# 启动 webpack 开发服务
	# node webpack.config.dev.js

elif [ "$type" = "-s" ]; then

	rm -rf .\\dist
	webpack -p --config webpack.config.js

	cp -rf WEB-INF .\\dist
	cp -rf assets\\images .\\dist\\assets
	cp favicon.ico ./dist

	rm -rf .\\dist\\WEB-INF\\prototype
	gulp inject

# elif [ "$type" = "-s" ]; then
elif [ "$type" = "-dev" ]; then
	node webpack.config.dev.js
	
else
	echo 'please input the right cmd!!!'
fi
