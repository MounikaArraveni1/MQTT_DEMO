package com.example.mqtt_demo.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.messaging.MessageChannel;

@Configuration
public class MqttConfig {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.topic}")
    private String topic;

    @Bean
    public MqttPahoClientFactory clientFactory() {

        System.out.println("⚙️ Creating MQTT Client Factory");
        System.out.println("Broker URL: " + broker);

        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{broker});

        factory.setConnectionOptions(options);

        System.out.println("✅ MQTT Client Factory configured");

        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        System.out.println("📡 Creating MQTT Input Channel");
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {

        System.out.println("📥 Setting up MQTT Subscriber...");
        System.out.println("Subscribing to topic: " + topic);

        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        "spring-client", clientFactory(), topic);

        adapter.setOutputChannel(mqttInputChannel());

        System.out.println("✅ MQTT Subscriber initialized");

        return adapter;
    }
}