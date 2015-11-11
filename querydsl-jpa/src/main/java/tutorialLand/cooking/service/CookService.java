package tutorialLand.cooking.service;

import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorialLand.cooking.domain.Chef;
import tutorialLand.cooking.domain.Cook;
import tutorialLand.cooking.domain.CookMaterial;
import tutorialLand.cooking.domain.Material;
import tutorialLand.cooking.dto.CookDTO;
import tutorialLand.cooking.repository.ChefRepository;
import tutorialLand.cooking.repository.CookRepository;
import tutorialLand.cooking.repository.CustomCookRepository;
import tutorialLand.cooking.repository.MaterialRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CookService {
    @Autowired
    private CookRepository cookRepository;

    @Autowired
    @Qualifier("cookRepositoryJpqlImpl")
    private CustomCookRepository customCookRepositoryJpql;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ChefRepository chefRepository;

    public long makeCook(String cookName, long chefUid, List<Long> materialUids) {
        // 엔티티 조회
        Chef chef = chefRepository.findOne(chefUid);

        List<Material> materials = new ArrayList<Material>();
        for (long materialUid : materialUids) {
            materials.add(materialRepository.findOne(materialUid));
        }

        // 요리재료 생성
        List<CookMaterial> cookMaterials = CookMaterial.createCookMaterials(materials);
        // 요리 생성
        Cook cook = Cook.createCook(chef, cookMaterials, cookName);

        // 요리 저장
        cookRepository.save(cook);
        return cook.getUid();
    }

    public Cook findOne(long cookUid) {
        return cookRepository.findOne(cookUid);
    }

    public Cook searchOnlyCook(long cookUid) {
        return cookRepository.searchOnlyCook(cookUid);
    }

    public Cook searchCookChefJoin(long cookUid) {
        return cookRepository.searchCookChefJoin(cookUid);
    }

    public Cook searchCookChefNotJoin(long cookUid) {
        return cookRepository.searchCookChefNotJoin(cookUid);
    }

    public List<Cook> searchCookList(long chefUid) {
        return cookRepository.searchCookList(chefUid);
    }

    public Cook searchCookMaterialChef(long cookUid) {
        Cook cook = cookRepository.searchCookMaterialChef(cookUid);

        return cook;
    }

    public CookDTO searchCookDTO(long cookUid) {
        return cookRepository.searchCookDTO(cookUid);
    }

    public List<Tuple> searchCookTuple(long cookUid) {
        return cookRepository.searchCookTuple(cookUid);
    }
}
