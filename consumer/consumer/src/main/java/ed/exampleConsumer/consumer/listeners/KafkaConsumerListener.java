package ed.exampleConsumer.consumer.listeners;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ed.exampleConsumer.consumer.model.dto.ActivityResponse;
import ed.exampleConsumer.consumer.model.entity.Activity;
import ed.exampleConsumer.consumer.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaConsumerListener {

    private final ActivityRepository activityRepository;

    @KafkaListener(topics = "exampleTopic1", groupId = "Ejemplo_1") //groupId nos sirve para crear grupo de consumidores, esta propiedad es OBLIGATORIA
    public void listener(String activityResponseJson) {
        log.info("Mensaje recibido: " + activityResponseJson);

        ObjectMapper objectMapper = new ObjectMapper();
        ActivityResponse activityResponse;
        try {
            activityResponse = objectMapper.readValue(activityResponseJson, ActivityResponse.class);
            log.info("Entidad mapeada: " + activityResponse);

            saveActivityResponse(activityResponse);
        } catch (JsonProcessingException e) {
            log.error("Error al deserializar el JSON: " + e.getMessage());
        }
    }

    private void saveActivityResponse(ActivityResponse activityResponse) {
        Activity activity = Activity.builder()
                .description(activityResponse.description())
                .amount(activityResponse.amount())
                .build();

        activityRepository.saveAndFlush(activity);
    }
}
