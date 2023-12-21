FROM azul/zulu-openjdk-alpine:21-latest

# 维护者信息
MAINTAINER xgblack

## 将后端项目的 Jar 文件，复制到镜像中
#COPY ./target/cool-code-server.jar app.jar

# 添加jar包到容器中
ADD ./target/cool-code-server.jar /home/app.jar

# 设置环境变量-运行时也可传参进来
## 设置 TZ 时区
ENV TZ=Asia/Shanghai
## 设置 JAVA_OPTS 环境变量，可通过 docker run -e "JAVA_OPTS=" 进行覆盖
ENV JAVA_OPTS="-Xms512m -Xmx512m"
## 设置 RUN_OPTS 运行参数
ENV RUN_OPTS="--spring.profiles.active=prod"

## 暴露后端项目的 8990 端口
EXPOSE 8990

## 启动后端项目
CMD java ${JAVA_OPTS} -jar app.jar ${RUN_OPTS}
# 以exec格式的CMD指令 -- 可实现优雅停止容器服务
# "sh", "-c" : 可通过exec模式执行shell  =》 获得环境变量
CMD ["sh", "-c", "echo \"****** 运行命令：java ${JAVA_OPTS} -jar /home/app.jar ${RUN_OPTS}\"   &   java ${JAVA_OPTS} -jar /home/app.jar ${RUN_OPTS}"]

## JAVA_OPTS RUN_OPTS 如不需要更改，则无需指定
## docker run -d -p 8990:8990 -e JAVA_OPTS="-Xms256m -Xmx256m" -e RUN_OPTS="--spring.profiles.active=dev" --name coolcode coolcode