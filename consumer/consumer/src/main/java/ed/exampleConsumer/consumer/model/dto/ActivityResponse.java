package ed.exampleConsumer.consumer.model.dto;

import ed.exampleConsumer.consumer.model.entity.Activity;

public record ActivityResponse(String description, Double amount) {
    public static ActivityResponse create(Activity activity) {
        return new ActivityResponse(activity.getDescription(), activity.getAmount());
    }
}
