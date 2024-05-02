package ed.exampleProvider.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic generateTopic() {
        // Algunas configuraciones para KAFKA
        Map<String, String> configurations = new HashMap<>();
        configurations.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); //Tratamiento de borrado de mensajes: "delete"(Borra el mensaje), "compact"(mantiene el mas actual).
        configurations.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); //tiempo de retencion de mensaje (por defecto -1).
        configurations.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); //Tamanio maximo que puede contener cada segmento dentro un topic.

        return TopicBuilder
                .name("exampleTopic1")
                .configs(configurations)
                .build();
    }

}
