package ed.exampleConsumer.consumer.repository;

import ed.exampleConsumer.consumer.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
