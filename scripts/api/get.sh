#!/bin/sh

curl \
  --location "localhost:8080/url-mapping/$1" \
  --request "GET" \
  --header "Accept: Application/Json" \
