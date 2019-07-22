# redis-project
redis学习使用


其中redis地址密码与数据库账号密码需要写你自己的

rides使用docker部署的部分操作

redis部署操作教程


1.创建本地redis配合文件

daemonize NO


protected-mode no


requirepass 123456


2.运行容器

docker run -d --privileged=true -p 6379:6379 -v /home/chenmt/redis/redis.conf:/etc/redis/redis.conf -v /docker/redis/data:/home/chenmt/data --name myredis docker.io/redis:latest redis-server /etc/redis/redis.conf --appendonly yes
#
--privileged=true：容器内的root拥有真正root权限，否则容器内root只是外部普通用户权限
 
-v /docker/redis/redis.conf:/etc/redis/redis.conf：映射配置文件
 
-v /docker/redis/data:/data：映射数据目录
 
redis-server /etc/redis/redis.conf：指定配置文件启动redis-server进程
 
--appendonly yes：开启数据持久化
 
myredis  :容器名称

3.进入容器内部

docker exec -ti myredis redis-cli -h localhost -p 6379



docker操作


1.删除容器

docker rm 容器id  来删除一个终止状态的容器；
若要删除一个运行中的容器，需要加-f参数。
