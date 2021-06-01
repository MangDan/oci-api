FROM node:lts-alpine as build-stage
# /app 디렉토리를 WORKDIR 로 설정
WORKDIR /app
# 현재 Dockerfile 있는 경로의 모든 파일을 /app 에 복사
COPY package*.json ./
# npm install 을 실행
RUN npm install --production
RUN npm install --save vue-chartjs
COPY . .
RUN npm run build

#production
FROM nginx:alpine as production-stage
#COPY ./.nginx/nginx.conf /etc/nginx/nginx.conf
## Remove default nginx index page
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-stage /app/dist /usr/share/nginx/html
RUN rm /etc/nginx/conf.d/default.conf
COPY docker/nginx/nginx.conf /etc/nginx/conf.d
EXPOSE 8080
CMD [ "nginx", "-g", "daemon off;" ]

# #가상 머신에 오픈할 포트
# EXPOSE 8080

# #컨테이너에서 실행될 명령을 지정
# CMD [ "http-server", "dist" ]
# # CMD npm run serve


# install simple http server for serving static content
# RUN npm install -g http-server

# # make the 'app' folder the current working directory
# WORKDIR /app

# # copy both 'package.json' and 'package-lock.json' (if available)
# COPY package*.json ./

# # install project dependencies
# RUN npm install

# # copy project files and folders to the current working directory (i.e. 'app' folder)
# COPY . .

# # build app for production with minification
# RUN npm run build

# # Test Expose 
# EXPOSE 8080

# CMD [ "http-server", "dist" ]