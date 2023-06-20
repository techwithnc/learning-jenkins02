#!/bin/bash
echo "export app-v=$1"
docker compose -p jp01 up -d
