#!/usr/bin/env bash
#使用说明，用来提示输入参数
usage() {
	echo "Usage: sh 执行脚本.sh [port|mount|base|modules|rm|rmiNoneTag|stop_base|stop_modules]"
	exit 1
}

#开启所需端口
port(){
	firewall-cmd --add-port=88/tcp --permanent
	firewall-cmd --add-port=8000/tcp --permanent
	firewall-cmd --add-port=8848/tcp --permanent
	firewall-cmd --add-port=8858/tcp --permanent
	firewall-cmd --add-port=3306/tcp --permanent
	firewall-cmd --add-port=3379/tcp --permanent
	firewall-cmd --add-port=7002/tcp --permanent
	firewall-cmd --add-port=9411/tcp --permanent
	service firewalld restart
}

##放置挂载文件
mount(){
	if test ! -f "/docker/nginx/api/nginx.conf" ;then
		mkdir -p /docker/nginx/api
		cp nginx/api/nginx.conf /docker/nginx/api/nginx.conf
	fi
	if test ! -f "/docker/nginx/web/nginx.conf" ;then
		mkdir -p /docker/nginx/web
		cp nginx/web/nginx.conf /docker/nginx/web/nginx.conf
		cp -r nginx/web/html /docker/nginx/web/html
	fi
	if test ! -f "/docker/nacos/init.d/custom.properties" ;then
		mkdir -p /docker/nacos/init.d
		cp nacos/init.d/custom.properties /docker/nacos/init.d/custom.properties
	fi
}

#启动基础模块
base(){
	docker-compose up -d nacos sentinel web-nginx queen-nginx queen-redis
}

#启动程序模块
modules(){
	docker-compose up -d queen-gateway1 queen-gateway2 queen-auth1 queen-auth2 queen-user queen-desk queen-system1 queen-system2 queen-log
}

#关闭base模块
stop_base(){
	docker-compose stop nacos sentinel web-nginx queen-nginx queen-redis
}


#关闭modules模块
stop_modules(){
	docker-compose stop queen-gateway1 queen-gateway2 queen-auth1 queen-auth2 queen-user queen-desk queen-system1 queen-system2 queen-log
}


#删除所有模块
rm(){
	docker-compose rm
}

#删除Tag为空的镜像
rmiNoneTag(){
	docker images|grep none|awk '{print $3}'|xargs docker rmi -f
}

#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
"port")
	port
;;
"mount")
	mount
;;
"base")
	base
;;
"modules")
	modules
;;
"rm")
	rm
;;
"rmiNoneTag")
	rmiNoneTag
;;
"stop_base")
	stop_base
;;
"stop_modules")
	stop_modules
;;
*)
	usage
;;
esac
