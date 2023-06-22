#!/bin/bash
export appversion=$1
sudo docker logout
sudo docker compose -p jp01 up -d
