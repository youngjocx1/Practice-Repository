FROM gitpod/workspace-full:latest

COPY nginx.conf /etc/nginx/nginx.conf

RUN sudo apt-get install -y nodejs
RUN sudo apt-get install -y yarn
RUN sudo apt-get update