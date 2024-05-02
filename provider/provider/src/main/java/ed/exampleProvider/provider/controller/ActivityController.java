package ed.exampleProvider.provider.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ed.exampleProvider.provider.model.dto.ActivityRequest;
import ed.exampleProvider.provider.model.dto.ActivityResponse;
import ed.exampleProvider.provider.model.entity.Activity;
import ed.exampleProvider.provider.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public ResponseEntity<ActivityResponse> save(@RequestBody ActivityRequest activityRequest) {
        Activity activity = Activity.builder()
                .description(activityRequest.description())
                .amount(activityRequest.amount())
                .build();
        Optional<Activity> activityOptional = Optional.of(activityRepository.save(activity));

        ActivityResponse activityResponse = ActivityResponse.create(activityOptional.get());

        submitEntityForDatabaseUpdate(activityResponse);

        return new ResponseEntity<>(activityResponse, HttpStatus.CREATED);
    }

    private void submitEntityForDatabaseUpdate(ActivityResponse activityResponse) {
        ObjectMapper objectMapper = new ObjectMapper();
        String activityResponseJson;
        try {
            activityResponseJson = objectMapper.writeValueAsString(activityResponse);
        } catch (JsonProcessingException e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            return;
        }

        kafkaTemplate.send("exampleTopic1", activityResponseJson); // Enviar el JSON a Kafka
    }

}
