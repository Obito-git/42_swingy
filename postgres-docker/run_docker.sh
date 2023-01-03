mkdir -p ~/goinfre/database
docker-compose up -d
docker exec -it postgres sh -c "chmod -R 777 /var/lib/postgresql/data/"

