FROM nginx:latest
COPY ./nginx/nginx.conf .
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]