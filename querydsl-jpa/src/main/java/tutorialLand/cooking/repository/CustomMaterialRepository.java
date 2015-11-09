package tutorialLand.cooking.repository;

import tutorialLand.cooking.domain.Material;
import java.util.List;

public interface CustomMaterialRepository {
    List <Material> searchMaterials(long cookUid);
}
