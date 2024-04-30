package ed.exampleConsumer.consumer.model.dto;

import ed.exampleConsumer.consumer.model.entity.Activity;

public record ActivityRequest(String description, Double amount) {
    public static ActivityRequest create(Activity activity) {
        return new ActivityRequest(activity.getDescription(), activity.getAmount());
    }
}
