package tutorialLand.cooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tutorialLand.cooking.domain.Cook;

public interface CookRepository extends JpaRepository <Cook, Long>, CustomCookRepository {

}
