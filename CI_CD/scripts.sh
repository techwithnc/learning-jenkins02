#!/bin/bash
export appversion=$1
docker logout
docker compose -p jp01 up -d
