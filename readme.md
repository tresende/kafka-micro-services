docker exec -i -t e4a56868e71f /bin/bash  && cd /opt/kafka/bin

kafka-topics.sh --create --zookeeper zookeeper:2181 --replication-factor 1 --partitions 1 --topic LOJA_NOVOPEDIDO
kafka-topics.sh --list --zookeeper zookeeper:2181

kafka-topics.sh --alter --zookeeper zookeeper:2181 --topic ECOMMERCE_NEW_ORDERS --partitions 3


kafka-console-producer.sh --broker-list localhost:9092 --topic NEW_ORDERS
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVOPEDIDO