package ed.exampleConsumer.consumer.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaConsumerListener {
    @KafkaListener(topics = {"exampleTopic1"}, groupId = "Ejemplo_1") //groupId nos sirve para crear grupo de consumidores, esta propiedad es OBLIGATORIA
    public void listener(String message) {
        log.info("Mensaje recibido: " + message);
    }
}
