package tutorialLand.cooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tutorialLand.cooking.domain.Chef;

@Repository
public interface ChefRepository extends JpaRepository <Chef, Long> {
}
