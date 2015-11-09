package tutorialLand.cooking.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import tutorialLand.cooking.domain.*;
import tutorialLand.cooking.dto.CookDTO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Basically, the implementation class of your custom interface should start with the name of your repository interface (JPARepository)
 * ending with 'Impl' keyword.
 * http://stackoverflow.com/questions/20777785/org-springframework-data-mapping-propertyreferenceexception-no-property-catch-f
 *
 * 이유에 대해서는 설명이 안돼있네
 * default로 무조건 하나는 있어야하나
 */
@Repository("cookRepositoryImpl")
public class CookRepositoryImpl implements CustomCookRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Cook searchOnlyCook(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;

        Cook cook = queryFactory.selectFrom(qCook)
                .where(qCook.uid.eq(cookUid))
                .fetchOne();

        return cook;
    }

    @Override
    public Cook searchCookChefJoin(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;

        Cook cook = queryFactory.selectFrom(qCook)
                .leftJoin(qCook.chef)
                .where(qCook.uid.eq(cookUid))
                .fetchOne();

        return cook;
    }

    @Override
    public Cook searchCookChefNotJoin(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;

        Cook cook = queryFactory.selectFrom(qCook)
                .where(qCook.uid.eq(cookUid))
                .fetchOne();

        cook.getChef().getUid();

        return cook;
    }

    @Override
    public List<Cook> searchCookList(long chefUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;
        QCookMaterial qCookMaterial = QCookMaterial.cookMaterial;

        List<Cook> cookList = queryFactory.selectFrom(qCook)
                .where(qCook.chef.uid.eq(chefUid))
                .fetch();

        return cookList;
    }

    @Override
    public Cook searchCookMaterialChef(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCookMaterial qCookMaterial = QCookMaterial.cookMaterial;
        QMaterial qMaterial = QMaterial.material;

        QCook qCook = QCook.cook;
        Cook cook = queryFactory.selectFrom(qCook)
                .where(qCook.uid.eq(cookUid))
                .fetchOne();

        // not select
        // cook.getChef();
        // cook.getCookMaterials();

        // select from chef / cookMaterial
        cook.getChef().getUid();
        cook.getCookMaterials().size();

        return cook;
    }

    @Override
    public CookDTO searchCookDTO(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;
        QChef qChef = QChef.chef;

        CookDTO cookDTO = queryFactory.selectFrom(qCook)
                .select(Projections.bean(CookDTO.class,
                        qCook.uid.as("cookUid"),
                        qCook.name.as("cookName"),
                        qChef.uid.as("chefUid"),
                        qChef.name.as("chefName")))
                .innerJoin(qCook.chef, qChef)
                .where(qCook.uid.eq(cookUid))
                .fetchOne();

        return cookDTO;
    }

    @Override
    public List<Tuple> searchCookTuple(long cookUid) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QCook qCook = QCook.cook;
        QCookMaterial qCookMaterial = QCookMaterial.cookMaterial;
        QMaterial qMaterial = QMaterial.material;

        List <Tuple> cookData = queryFactory.selectFrom(qMaterial)
                .select(qCook.name, qMaterial.name)
                .innerJoin(qMaterial.cookMaterials, qCookMaterial)
                .innerJoin(qCookMaterial.cook, qCook)
                .where(qCookMaterial.cook.uid.eq(cookUid))
                .fetch();

        for (Tuple row : cookData) {
            System.out.println(row.get(qCook.name));
            System.out.println(row.get(qMaterial.name));
        }

        return cookData;
    }
}
