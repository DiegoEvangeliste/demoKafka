package ed.exampleProvider.provider.repository;

import ed.exampleProvider.provider.model.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
