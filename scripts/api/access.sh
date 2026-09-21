#!/bin/sh

curl \
  --location "localhost:8080/$1" \
  --request "GET" \
  --header "Accept: Application/Json" \
