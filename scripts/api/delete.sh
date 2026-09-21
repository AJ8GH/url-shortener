#!/bin/sh

curl \
  --location "localhost:8080/url-mapping/$1" \
  --request "DELETE" \
  --header "Accept: Application/Json" \
