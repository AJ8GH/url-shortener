#!/bin/sh

n=$2
if [ -z "$n" ]; then
  n=1
fi

for _ in $(seq 0 "$n")
do
  curl \
    --location "localhost:8080/url-mapping" \
    --request "POST" \
    --header "Content-Type: Application/Json" \
    --header "Accept: Application/Json" \
    --data "{
      \"longUrl\": \"$1\"
    }"
done
