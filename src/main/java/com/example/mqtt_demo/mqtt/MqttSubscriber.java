package com.example.mqtt_demo.mqtt;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

@Service
public class MqttSubscriber {

    @ServiceActivator(inputChannel = "mqttInputChannel")
    public void receive(String message) {

        System.out.println("=================================");
        System.out.println("📩 MQTT SUBSCRIBER TRIGGERED");
        System.out.println("Received Message: " + message);
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("Time: " + java.time.LocalDateTime.now());
        System.out.println("=================================");
    }
}