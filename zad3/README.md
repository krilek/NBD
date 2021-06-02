docker run --network=nbd-network -v ${PWD}:/test --name nbd3 -d mongo:latest
docker exec -it nbd3 bash
mongoimport --file /test/cwiczenia2.json --db nbd --host nbd3 --jsonArray -c people
mongo --host nbd3 nbd /test/zapytanie_8.js > /test/wyniki_8.json