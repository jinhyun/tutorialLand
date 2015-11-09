package tutorialLand.cooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tutorialLand.cooking.domain.Material;

@Repository
public interface MaterialRepository extends JpaRepository <Material, Long>, CustomMaterialRepository {
}
