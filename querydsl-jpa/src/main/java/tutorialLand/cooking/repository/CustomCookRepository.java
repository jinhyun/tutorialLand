package tutorialLand.cooking.repository;

import com.querydsl.core.Tuple;
import tutorialLand.cooking.domain.Cook;
import tutorialLand.cooking.dto.CookDTO;

import java.util.List;

public interface CustomCookRepository {
    Cook searchOnlyCook(long cookUid);

    Cook searchCookChefJoin(long cookUid);

    Cook searchCookChefNotJoin(long cookUid);

    List<Cook> searchCookList(long chefUid);

    Cook searchCookMaterialChef(long cookUid);

    CookDTO searchCookDTO(long cookUid);

    List<Tuple> searchCookTuple(long cookUid);
}
