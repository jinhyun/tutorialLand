package tutorialLand.cooking.repository;

import com.querydsl.core.Tuple;
import org.springframework.stereotype.Repository;
import tutorialLand.cooking.domain.Cook;
import tutorialLand.cooking.dto.CookDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("cookRepositoryJpqlImpl")
public class CookRepositoryJpqlImpl implements CustomCookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cook searchOnlyCook(long cookUid) {
        return null;
    }

    @Override
    public Cook searchCookChefJoin(long cookUid) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT c ");
        sb.append("FROM Cook c ");
        sb.append("WHERE c.uid = :cookUid ");

        Query query = em.createQuery(sb.toString());
        query.setParameter("cookUid", cookUid);

        Cook cook = (Cook) query.getSingleResult();
        return cook;
    }

    @Override
    public Cook searchCookChefNotJoin(long cookUid) {
        return null;
    }

    @Override
    public List<Cook> searchCookList(long cookUid) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT c ");
        sb.append("FROM Cook c ");
        sb.append("LEFT JOIN c.cookMaterials cm ");
        sb.append("WHERE c.uid = :cookUid ");

        Query query = em.createQuery(sb.toString());
        query.setParameter("cookUid", cookUid);

        List<Cook> cooks = query.getResultList();
        return cooks;
    }

    @Override
    public Cook searchCookMaterialChef(long cookUid) {
        return null;
    }

    @Override
    public CookDTO searchCookDTO(long cookUid) {
        return null;
    }

    @Override
    public List<Tuple> searchCookTuple(long cookUid) {
        return null;
    }
}
