docker-compose stop
docker-compose down
docker system prune -f
docker image prune -f
docker rmi -f $(docker images -a -q)
rm -rf ~/goinfre/database
