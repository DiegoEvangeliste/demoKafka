package ed.exampleConsumer.consumer.controller;

import ed.exampleConsumer.consumer.model.dto.ActivityRequest;
import ed.exampleConsumer.consumer.model.entity.Activity;
import ed.exampleConsumer.consumer.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityRepository activityRepository;

    @GetMapping("/{idActivity}")
    public ResponseEntity<ActivityRequest> findById(@PathVariable Long id) {
        Optional<Activity> optional = activityRepository.findById(id);

        return (optional.isEmpty()) ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(ActivityRequest.create(optional.get()));
    }

}
