package br.com.thiagoresende.loja;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class FraudDetectorService {
    public static void main(String[] args) throws InterruptedException {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties());
        consumer.subscribe(Collections.singletonList("ECOMMERCE_NEW_ORDERS"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            if (!records.isEmpty()) {
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("-----------------------------------------");
                    System.out.println("Processing new order, checking for fraud");
                    System.out.println(record.key());
                    System.out.println(record.value());
                    System.out.println(record.partition());
                    System.out.println(record.offset());
                    Thread.sleep(1000);
                    System.out.println("\n");
                    System.out.println("********* Order Processed *********");
                    System.out.println("-----------------------------------------");
                }
            }
        }
    }

    private static Properties properties() {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, FraudDetectorService.class.getSimpleName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1");
        return properties;
    }
}
