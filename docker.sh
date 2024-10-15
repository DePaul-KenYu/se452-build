docker build . -t se452-build -f ./Dockerfile

# 8181 is defined inside the container
# 8003 is defined outside the container
docker run -p 8003:8181 -d se452-build