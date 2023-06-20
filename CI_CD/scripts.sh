#!/bin/bash
export app_v=$1
# docker compose -p jp01 up -d
docker swarm init
docker service create --name svc01 techwithnc/betterhrapp:1.0
docker image pull techwithnc/betterhrapp:$app_v
docker service update --image techwithnc/betterhrapp:$app_v svc01