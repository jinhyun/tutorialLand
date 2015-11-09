package tutorialLand.cooking.service;

import com.querydsl.core.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tutorialLand.cooking.Application;

import org.junit.runner.RunWith;
import org.junit.*;
import tutorialLand.cooking.domain.*;
import tutorialLand.cooking.dto.CookDTO;
import tutorialLand.cooking.repository.ChefRepository;
import tutorialLand.cooking.repository.CookRepository;
import tutorialLand.cooking.repository.MaterialRepository;
import tutorialLand.cooking.servie.CookService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CookServiceTest {
    @Autowired private ChefRepository chefRepository;
    @Autowired private CookRepository cookRepository;
    @Autowired private MaterialRepository materialRepository;
    @Autowired private CookService cookService;

    @Test
    public void makeCook_relationSave() {
        // when
        long spaghettiUid = createCreamSpaghettiData();

        // then
        Cook cook = cookService.searchCookMaterialChef(spaghettiUid);
        assertThat(cook.getName(), is("크림스파게티"));
        assertThat(cook.getChef().getName(), is("정쉐프"));
        assertThat(cook.getCookMaterials().size(), is(3));
    }

    @Test
    public void compareSelect_join_notJoin() {
        // given
        long ramenUid = createRamenData();
        long spaghettiUid = createCreamSpaghettiData();

        // when
        Cook notJoinCook = cookService.searchCookChefNotJoin(spaghettiUid);
        Cook joinCook = cookService.searchCookChefJoin(spaghettiUid);

        // then
        assertThat(notJoinCook.getChef().getName(), notNullValue());
        assertThat(joinCook.getUid(), is(2L));

//        -- LazyException
//        assertThat(joinCook.getChef().getName(), notNullValue());
    }

    @Test
    public void compareFindOne_jpa_querydsl() {
        long ramenUid = createRamenData();

        // when
        Cook jpaCook = cookService.findOne(ramenUid);
        Cook queryDslCook = cookService.searchOnlyCook(ramenUid);

        assertThat(jpaCook.getUid(), is(queryDslCook.getUid()));
        assertThat(jpaCook.getName(), is(queryDslCook.getName()));

//        -- LazyException
//        assertThat(jpaCook.getCookMaterials().size(), is(queryDslCook.getCookMaterials().size()));
//        assertThat(jpaCook.getChef().getName(), is(queryDslCook.getChef().getName()));
    }

    @Test
    public void searchCookList() {
        // given
        long ramenUid = createRamenData();
        long creamSpaghettiUid = createCreamSpaghettiData();
        long salmonSushiUid = createSalmonSushiData();

        // when
        List <Cook> cookList = cookService.searchCookList(2L);
        List <String> cookNameList = new ArrayList<String>();
        for (Cook cook : cookList){
            cookNameList.add(cook.getName());
        }

        // then
        assertThat(cookList.size(), is(2));
        assertThat(cookNameList, contains("크림스파게티", "연어초밥"));
    }

    @Test
    public void searchCookDTO() {
        // given
        long ramenUid = createRamenData();
        long creamSpaghettiUid = createCreamSpaghettiData();
        long salmonSushiUid = createSalmonSushiData();

        // when
        CookDTO cookDTO = cookService.searchCookDTO(salmonSushiUid);

        // then
        assertThat(cookDTO, instanceOf(CookDTO.class));
        assertThat(cookDTO.getCookName(), is("연어초밥"));
        assertThat(cookDTO.getChefName(), is("정쉐프"));
    }

    // TODO: Tuple
    @Test
    public void searchCookTuple() {
        // given
        long ramenUid = createRamenData();
        long creamSpaghettiUid = createCreamSpaghettiData();
        long salmonSushiUid = createSalmonSushiData();

        // when
        List<Tuple> cookTuple = cookService.searchCookTuple(salmonSushiUid);
    }

    @Transactional
    public long createRamenData() {
        long hwangChefUid = createChefData("황쉐프");
        long noodleUid = createMaterialData("면");

        List <Long> ramenMaterialUids = new ArrayList<Long>();
        ramenMaterialUids.add(noodleUid);

        return cookService.makeCook("라면", hwangChefUid, ramenMaterialUids);
    }

    @Transactional
    public long createCreamSpaghettiData() {
        long jungChefUid = createChefData("정쉐프");
        long creamSourceUid = createMaterialData("크림소스");
        long noodleUid = createMaterialData("면");
        long onionUid = createMaterialData("양파");

        List <Long> spaghettiMaterialUids = new ArrayList<Long>();
        spaghettiMaterialUids.add(creamSourceUid);
        spaghettiMaterialUids.add(noodleUid);
        spaghettiMaterialUids.add(onionUid);

        return cookService.makeCook("크림스파게티", jungChefUid, spaghettiMaterialUids);
    }

    @Transactional
    public long createSalmonSushiData() {
        long jungChefUid = 2L;
        long salmonUid = createMaterialData("연어");
        long riceUid = createMaterialData("밥");
        long vinegarUid = createMaterialData("식초");

        List <Long> salmonSushiMaterialUids = new ArrayList<Long>();
        salmonSushiMaterialUids.add(salmonUid);
        salmonSushiMaterialUids.add(riceUid);
        salmonSushiMaterialUids.add(vinegarUid);

        return cookService.makeCook("연어초밥", jungChefUid, salmonSushiMaterialUids);
    }

    @Transactional
    private long createChefData(String name) {
        Chef chef = new Chef();
        chef.setName(name);
        chefRepository.save(chef);
        return chef.getUid();
    }

    @Transactional
    private long createMaterialData(String name) {
        Material material = new Material();
        material.setName(name);
        materialRepository.save(material);
        return material.getUid();
    }
}
