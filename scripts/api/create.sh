#!/bin/sh

curl \
  --location "localhost:8080/url-mapping" \
  --request "POST" \
  --header "Content-Type: Application/Json" \
  --header "Accept: Application/Json" \
  --data "{
    \"longUrl\": \"$1\"
  }"
