package com.example.mqtt_demo.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MqttPublisher {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.topic}")
    private String topic;

    public void publish(String message) throws Exception {

        System.out.println("=================================");
        System.out.println("🚀 MQTT PUBLISHER START");
        System.out.println("Broker: " + broker);
        System.out.println("Topic: " + topic);
        System.out.println("Message: " + message);

        MqttClient client =
                new MqttClient(broker, MqttClient.generateClientId());

        System.out.println("🔌 Connecting to broker...");
        client.connect();
        System.out.println("✅ Connected");

        MqttMessage mqttMessage = new MqttMessage(message.getBytes());

        System.out.println("📤 Publishing message...");
        client.publish(topic, mqttMessage);
        System.out.println("✅ Message published");

        client.disconnect();
        System.out.println("🔌 Disconnected from broker");

        System.out.println("=================================");
    }
}