package ed.exampleProvider.provider.model.dto;

import ed.exampleProvider.provider.model.entity.Activity;

public record ActivityResponse(String description, Double amount) {
    public static ActivityResponse create(Activity activity) {
        return new ActivityResponse(activity.getDescription(), activity.getAmount());
    }
}
