docker exec -i -t <aasdsada> /bin/bash 
cd /opt/kafka/bin

kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic LOJA_NOVOPEDIDO
kafka-topics.sh --list --zookeeper zookeeper:2181

kafka-console-producer.sh --broker-list localhost:9092 --topic LOJA_NOVOPEDIDO
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVOPEDIDO