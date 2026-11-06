package br.com.projeto.mqtt.out;

import com.hivemq.client.mqtt.datatypes.MqttQos;
import com.hivemq.client.mqtt.mqtt3.Mqtt3BlockingClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Mqttout{
    public void outMqtt() {
        // Configurações do Broker (Altere para o seu endereço)
        String brokerHost = "192.168.116.128"; // Exemplo de broker público
        int brokerPort = 1883;
        String topic = "javatopic";

        // Payload usando Text Blocks do Java 17 (perfeito para JSON)
        String payload = """
                { 
                        "sensor": "java temperatura",
                        "valor": 25.5,
                        "status": "OK"
                }
                """;

        // 1. Criar o cliente MQTT com um ID único
        Mqtt3BlockingClient client = Mqtt3Client.builder()
                .identifier(UUID.randomUUID().toString())
                .serverHost(brokerHost)
                .serverPort(brokerPort)
                .buildBlocking();

        try {
            System.out.println("Conectando ao broker: " + brokerHost);
            // 2. Conectar ao Broker
            client.connect();
            System.out.println("Conectado com sucesso!");

            System.out.println("Enviando payload para o tópico: " + topic);
            // 3. Publicar o Payload
            client.publishWith()
                    .topic(topic)
                    .qos(MqttQos.AT_LEAST_ONCE) // QoS 1
                    .payload(payload.getBytes(StandardCharsets.UTF_8))
                    .send();

            System.out.println("Mensagem enviada!");

        } catch (Exception e) {
            System.err.println("Erro na comunicação MQTT: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 4. Desconectar com segurança
            if (client.getState().isConnected()) {
                client.disconnect();
                System.out.println("Desconectado do broker.");
            }
        }
    }
}
