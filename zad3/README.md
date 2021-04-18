docker run --network=nbd-network --name nbd3 -d mongo:latest
docker run -it --network nbd-network -v ${PWD}:/test mongo mongo --host nbd3 nbd3-client
mongoimport --file /test/cwiczenia2.json --db nbd --host nbd3 --jsonArray -c people
mongo --host nbd3 nbd /test/zapytanie_8.js > /test/wyniki_8.json