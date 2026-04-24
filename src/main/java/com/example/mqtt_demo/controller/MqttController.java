package com.example.mqtt_demo.controller;

import com.example.mqtt_demo.mqtt.MqttPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    private MqttPublisher publisher;

    @PostMapping("/send")
    public String send(@RequestParam String msg) throws Exception {

        System.out.println("=================================");
        System.out.println("🌐 API HIT: /mqtt/send");
        System.out.println("Incoming message: " + msg);

        publisher.publish(msg);

        System.out.println("✅ Message forwarded to MQTT");
        System.out.println("=================================");

        return "Sent: " + msg;
    }
}