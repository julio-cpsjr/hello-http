package br.com.projeto.infraestrutura.mqtt.in;


import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3Client;
import com.hivemq.client.mqtt.datatypes.MqttQos;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Mqttin {
    // Configurações do Broker (Apontando para a porta que o Docker expôs)
    String brokerHost = "localhost";
    int brokerPort = 1883;

    // O tópico deve ser exatamente o mesmo configurado no seu 'mqtt out' do Node-RED
    String topic = "sensores/dados";

    // 1. Criar o cliente MQTT Assíncrono com ID único
    Mqtt3AsyncClient client = Mqtt3Client.builder()
            .identifier("java-subscriber-" + UUID.randomUUID())
            .serverHost(brokerHost)
            .serverPort(brokerPort)
            .buildAsync();

        System.out.println("Iniciando conexão com o Broker MQTT em " + brokerHost + ":" + brokerPort);

    // 2. Conectar ao Broker de forma assíncrona
        client.connect()
                .whenComplete((connAck, throwable) -> {
        if (throwable != null) {
            System.err.println("Falha ao conectar ao Broker: " + throwable.getMessage());
            return;
        }

        System.out.println("Conectado com sucesso ao Broker MQTT!");

        // 3. Se inscrever (Subscribe) no tópico e definir o que fazer ao receber a mensagem
        client.subscribeWith()
                .topicFilter(topic)
                .qos(MqttQos.AT_LEAST_ONCE)
                .callback(publish -> {
                    // Captura o payload em bytes e converte para String UTF-8
                    String payload = new String(publish.getPayloadAsBytes(), StandardCharsets.UTF_8);

                    System.out.println("\n--- Nova Mensagem Recebida ---");
                    System.out.println("Tópico: " + publish.getTopic());
                    System.out.println("Payload: \n" + payload);

                    // [DICA] É EXATAMENTE AQUI que no futuro você chamará o seu Producer do Kafka
                    // Exemplo: kafkaProducer.send(new ProducerRecord<>("topico-kafka", payload));
                })
                .send()
                .whenComplete((subAck, subThrowable) -> {
                    if (subThrowable != null) {
                        System.err.println("Erro ao se inscrever no tópico: " + subThrowable.getMessage());
                    } else {
                        System.out.println("Inscrito com sucesso no tópico: [" + topic + "]. Aguardando dados...");
                    }
                });
    });

    // Como o cliente roda em threads separadas (assíncrono), precisamos segurar a função main viva
    // Para que o programa não feche imediatamente após iniciar.
        try {
        Thread.currentThread().join();
    } catch (InterruptedException e) {
        System.err.println("Aplicação encerrada abruptamente.");
    }
}
