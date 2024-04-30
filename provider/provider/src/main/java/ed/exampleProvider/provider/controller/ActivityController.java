package ed.exampleProvider.provider.controller;

import ed.exampleProvider.provider.model.dto.ActivityResponse;
import ed.exampleProvider.provider.model.entity.Activity;
import ed.exampleProvider.provider.repository.ActivityRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<ActivityResponse> save(@RequestBody ActivityResponse activityResponse) {
        Activity activity = Activity.builder()
                .description(activityResponse.description())
                .amount(activityResponse.amount()).build();
        Optional<Activity> optional = Optional.of(activityRepository.save(activity));

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
