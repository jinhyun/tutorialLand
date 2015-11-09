package tutorialLand.cooking.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import tutorialLand.cooking.domain.Material;
import tutorialLand.cooking.domain.QCookMaterial;
import tutorialLand.cooking.domain.QMaterial;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MaterialRepositoryImpl implements CustomMaterialRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Material> searchMaterials(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QMaterial qMaterial = QMaterial.material;
        QCookMaterial qCookMaterial = QCookMaterial.cookMaterial;

        List<Material> materials = queryFactory.selectFrom(qMaterial)
                .leftJoin(qMaterial.cookMaterials, qCookMaterial)
                .where(qCookMaterial.cook.uid.eq(cookUid))
                .fetch();

        return materials;
    }
}
